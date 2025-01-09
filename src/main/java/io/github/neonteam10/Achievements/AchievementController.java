package io.github.neonteam10.Achievements;

import io.github.neonteam10.GameLogic;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.ui.UiStage;

import java.util.ArrayList;
import java.util.List;

public class AchievementController {

    private final List<GameAchievement> achievementList;
    private final GameLogic gameLogic;
    private GameMap gameMap;
    private final UiStage uiStage;


    public AchievementController(GameLogic gameLogic, GameMap gameMap, UiStage uiStage) {
        this.gameLogic = gameLogic;
        this.gameMap = gameMap;
        this.achievementList = new ArrayList<>();
        this.uiStage = uiStage;
        achievementList.add(new AccommodationAchievement(this.gameMap,this.gameLogic));
        achievementList.add(new DiverseAchievement(this.gameMap,this.gameLogic));
        achievementList.add(new EducationAchievement(this.gameMap,this.gameLogic));
        achievementList.add(new FresherAchievement(this.gameMap, this.gameLogic));
        achievementList.add(new GettingStartedAchievement(this.gameMap));
        achievementList.add(new RecreationAchievement(this.gameLogic, this.gameMap));
        achievementList.add(new ScoreAchievement(this.gameMap,this.gameLogic));
    }
    public void AchievementsUnlocked() {
        if (gameMap != null) {
            for (GameAchievement achievement : achievementList) {
                if (!achievement.getUnlocked() && achievement.getUnlockStatus()) {
                    achievement.Unlock();
                    uiStage.activateAchievement(achievement.name, achievement.description);
                }
            }
        }
    }
    public void setGameMap(GameMap gameMap) {
        if (this.gameMap != gameMap) {
            for (GameAchievement achievement : achievementList) {
                achievement.setGameMap(gameMap);
            }

        }

    }

}
