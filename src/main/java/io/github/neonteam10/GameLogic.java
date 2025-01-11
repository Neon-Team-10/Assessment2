package io.github.neonteam10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.MathUtils;

import io.github.neonteam10.Achievements.AchievementController;
import io.github.neonteam10.map.BuildingPrefab;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.ui.UiStage;

/**
 * A class which manages the gameplay logic, including the remaining game time, placing buildings, and calculating
 * satisfaction and score.
 */
public class GameLogic {
    private static final float TOTAL_GAME_TIME = 0.2f * 60.0f;
    private static final float BUILDING_TIME = 20.0f;

    private GameMap gameMap;
    private int maximumAllowedBuildings;
    private int selectedPrefabIndex = -1;

    // Timers.
    private float remainingTime;
    private float nextBuildingTime;
    private boolean gameOver;
    private boolean paused;
    private boolean started;

    // Satisfaction.
    private float satisfaction;
    private float newBuildingSatisfaction;
    private int previousBuildingCount;
    private int previousStudentCount;
    private int studentCount;

    GameScreen screen;

    // Events.
    private GameEvent currentEvent;
    private float nextEventProbability;
    private float checkEventTimer;
    private float eventDurationTimer;
    AchievementController achievementController;

    public GameLogic(GameScreen screen) {
        this.screen = screen;
        remainingTime = TOTAL_GAME_TIME;
        nextBuildingTime = 0.0f;
        currentEvent = GameEvent.NONE;
        paused = true;
        started = false;
        studentCount = 0;
        maximumAllowedBuildings = 1;
    }

    public void setMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * Finds a building prefab given its name.
     *
     * @param name the prefab name
     * @return a {@link BuildingPrefab}
     */
    public BuildingPrefab findPrefab(String name) {
        for (var prefab : gameMap.getAvailablePrefabs()) {
            if (prefab.getName().equals(name)) {
                return prefab;
            }
        }
        return null;
    }

    /**
     * Continuously updates the student satisfaction.
     *
     * @param deltaTime the time between the last call of this method
     */
    private void updateSatisfaction(float deltaTime) {
        // Get all building types.
        var accommodationPrefab = findPrefab("Accommodation");
        var canteenPrefab = findPrefab("Canteen");
        var studyPrefab = findPrefab("Study");

        // Work out the number of students based on how many accommodation buildings there are.
        studentCount = gameMap.getBuildingCount(accommodationPrefab) * 25;

        // Store satisfaction to add for new buildings. 50 score per new building
        int newBuildingCount = gameMap.getTotalBuildingCount() - previousBuildingCount;
        if (newBuildingCount > 0) {
            newBuildingSatisfaction = 50.0f * newBuildingCount;
        } else {
            newBuildingSatisfaction = 0.0f;
        }
        previousBuildingCount = gameMap.getTotalBuildingCount();
        satisfaction += newBuildingSatisfaction;

        // Store satisfaction to add for new students. 10 score per new student
        int newStudentCount = studentCount - previousStudentCount;
        float newStudentSatisfaction = 0.0f;
        if (newStudentCount > 0) {
            newStudentSatisfaction = 10.0f * newStudentCount;
        }
        previousStudentCount = studentCount;
        satisfaction += newStudentSatisfaction;

        // Decrease satisfaction if there isn't enough canteen or study buildings for all the students. Each canteen
        // can support 100 students and each study building can support 75 students. Use exponential formulas so a
        // deficit can not just be offset by placing lots of recreation buildings.
        var canteenDeficit = studentCount - gameMap.getBuildingCount(canteenPrefab) * 100;
        var studyDeficit = studentCount - gameMap.getBuildingCount(studyPrefab) * 75;
        if (canteenDeficit > 0) {
            satisfaction -= ((float) Math.pow(1.3f, canteenDeficit/10)/10);
        }
        if (studyDeficit > 0) {
            float factor = currentEvent == GameEvent.STRIKE ? 2.0f : 1f;
            satisfaction -= ((float) Math.pow(1.3f, studyDeficit/10)/10) * factor;
        }

        // Decay satisfaction based on a rate determined by the amount of recreation buildings.
        //float gainRate = getCurrentGainRate();

        float Canteenbonus = gameMap.buildingGraph.getBonus("Canteen");
        float Accommodationbonus = gameMap.buildingGraph.getBonus("Accommodation");

        float totalBonus = (Canteenbonus+Accommodationbonus)/100;
        satisfaction += totalBonus;

        // Handle rain and roses events.
        if (currentEvent == GameEvent.RAIN) {
            satisfaction -= 2f * deltaTime;
        } else if (currentEvent == GameEvent.ROSES) {
            satisfaction += 2f * deltaTime;
        }

        // Clamp satisfaction above 0.
        satisfaction = MathUtils.clamp(satisfaction, 0.0f, Integer.MAX_VALUE);
    }

    /**
     * Updates the game logic.
     *
     * @param deltaTime the delta time between the last call of update
     */
    public void update(float deltaTime) {
        if (gameOver) {
            return;
        }

        //skip time to end
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            remainingTime = 2;
        }


        // Update timers.
        if (!paused) {
            remainingTime -= deltaTime;
        }
        if (remainingTime < 0.0f) {
            gameOver = true;
        }
        if (started) {
            nextBuildingTime -= deltaTime;
        }
        if (paused) {
            nextBuildingTime += deltaTime;
        }
        if (nextBuildingTime < 0.0f) {
            // User can place another building.
            maximumAllowedBuildings++;
            nextBuildingTime = BUILDING_TIME;
        }

        // Update satisfaction.
        if (!paused && !gameOver) {
            updateSatisfaction(deltaTime);
        }

        //tick achievements
        if (!paused) {
            if (achievementController != null && gameMap != null) {
                achievementController.setGameMap(gameMap);
                achievementController.AchievementsUnlocked();
            }
        }

        if (!paused) {
            // Tick event duration timer.
            if (currentEvent != GameEvent.NONE) {
                eventDurationTimer -= deltaTime;
            }
            if (eventDurationTimer < 0.0f) {
                currentEvent = GameEvent.NONE;
            }
            if (currentEvent != GameEvent.NONE) {
                return;
            }


            // Generate a random number every 2 seconds to see if we should start an event. Bias the random number slightly
            // to prevent events from happening to close to each other.
            nextEventProbability += deltaTime * 0.01f;
            checkEventTimer += deltaTime;
            if (checkEventTimer > 2.0f) {
                checkEventTimer = 0.0f;
                if (Math.min(MathUtils.random() + 0.1f, 1.0f) < nextEventProbability) {
                    nextEventProbability = 0;
                    currentEvent = GameEvent.values()[MathUtils.random(GameEvent.values().length - 1)];
                    eventDurationTimer = MathUtils.random(15.0f, 45.0f);
                }
            }
        }


    }

    /**
     * Sets the selected building placement prefab to the given index.
     *
     * @param prefabIndex the prefab index
     */
    public void setSelectedPrefabIndex(int prefabIndex) {
        if (gameMap != null && prefabIndex < gameMap.getAvailablePrefabs().size()) {
            selectedPrefabIndex = prefabIndex;
        } else {
            selectedPrefabIndex = -1;
        }
    }

    /**
     * @return true if the player is allowed to place another building
     */
    public boolean canPlaceBuilding() {
        return !gameOver && gameMap.getTotalBuildingCount() < maximumAllowedBuildings;
    }

    /**
     * @return the selected prefab if there is one, otherwise null
     */
    public BuildingPrefab getSelectedPrefab() {
        if (gameMap == null || selectedPrefabIndex < 0) {
            return null;
        }
        return gameMap.getAvailablePrefabs().get(selectedPrefabIndex);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public int getSelectedPrefabIndex() {
        return selectedPrefabIndex;
    }

    public float getRemainingTime() {
        return remainingTime;
    }

    public float getNextBuildingTime() {
        return nextBuildingTime;
    }

    public float getSatisfaction() {
        return satisfaction;
    }

    public GameEvent getCurrentEvent() {
        return currentEvent;
    }

    public float getEventDurationTimer() {
        return eventDurationTimer;
    }

    public boolean setPaused(boolean paused) {
        this.paused = paused;
        if (!(started) && !(paused)){
            started = true;
        }
        return this.paused;
    }

    public boolean getPaused() {
        return this.paused;
    }

    public void restart() {
        screen.restart();
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setAchievementController(AchievementController achievementController) {
        this.achievementController = achievementController;
    }

}

