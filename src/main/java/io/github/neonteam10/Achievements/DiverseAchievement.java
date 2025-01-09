package io.github.neonteam10.Achievements;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.GameLogic;


public class DiverseAchievement extends GameAchievement {

    private final GameMap gameMap;
    private final GameLogic gameLogic;

    public DiverseAchievement (GameMap gamemap, GameLogic gamelogic){
        super("Diverse","Place every type of building.");
        this.gameMap= gamemap;
        this.gameLogic = gamelogic;
    }

    @Override
    protected boolean getUnlockStatus(){
        return gameMap.getBuildingCount(gameLogic.findPrefab("Accommodation")) >= 1 && gameMap.getBuildingCount(gameLogic.findPrefab("Education")) >= 1 && gameMap.getBuildingCount(gameLogic.findPrefab("Recreation")) >= 1;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
}