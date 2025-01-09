package io.github.neonteam10.Achievements;

import io.github.neonteam10.map.GameMap;

public class GettingStartedAchievement extends GameAchievement {
    private GameMap gameMap;
    //Achievement for placing a building
    public GettingStartedAchievement(GameMap gameMap) {
        super("Getting Started!","Place a building!");
        this.gameMap = gameMap;
    }
    @Override
    protected boolean getUnlockStatus(){
        return gameMap.getTotalBuildingCount() >= 1;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
    public void setGameMap(GameMap gameMap) { this.gameMap = gameMap;}
}