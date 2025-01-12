package io.github.neonteam10.Achievements;
import io.github.neonteam10.GameLogic;
import io.github.neonteam10.map.GameMap;

public class ScoreAchievement extends GameAchievement {
    private GameMap gameMap;
    private final GameLogic gameLogic;
    public ScoreAchievement(GameMap gameMap, GameLogic gamelogic){
        super("Happy!","Reach high satisfaction!");
        this.gameLogic = gamelogic;
        this.gameMap = gameMap;
    }

    @Override
    protected boolean getUnlockStatus() {
        return gameLogic.getSatisfaction() >= 10000;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
    public void setGameMap(GameMap gameMap) { this.gameMap = gameMap;}
}