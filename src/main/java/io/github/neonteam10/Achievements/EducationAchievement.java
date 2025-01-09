package io.github.neonteam10.Achievements;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.GameLogic;


public class EducationAchievement extends GameAchievement {

    private GameMap gameMap;
    private final GameLogic gameLogic;

    public EducationAchievement(GameMap gamemap, GameLogic gamelogic) {
        super("Knowledge!","Place five educational buildings.");
        this.gameMap= gamemap;
        this.gameLogic = gamelogic;

    }
    @Override
    protected boolean getUnlockStatus(){
        return gameMap.getBuildingCount(gameLogic.findPrefab("Study")) >= 5;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
    public void setGameMap(GameMap gameMap) { this.gameMap = gameMap;}
}
