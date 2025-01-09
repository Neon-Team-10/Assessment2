package io.github.neonteam10.Achievements;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.GameLogic;


public class AccommodationAchievement extends GameAchievement {

    private final GameMap gameMap;
    private final GameLogic gameLogic;

    public AccommodationAchievement (GameMap gamemap, GameLogic gamelogic){
        super("House Party!","Place five Accommodation buildings.");
        this.gameMap= gamemap;
        this.gameLogic = gamelogic;
    }

    @Override
    protected boolean getUnlockStatus(){
        return gameMap.getBuildingCount(gameLogic.findPrefab("Accommodation")) >= 5;
    }

    @Override
    protected void DisplayAchievement(){

    }
}
