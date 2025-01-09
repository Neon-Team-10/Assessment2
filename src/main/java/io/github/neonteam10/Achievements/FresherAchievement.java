package io.github.neonteam10.Achievements;
import com.badlogic.gdx.Game;
import io.github.neonteam10.GameLogic;
import io.github.neonteam10.map.GameMap;

public class FresherAchievement extends GameAchievement{
    private GameMap gameMap;
    private final GameLogic gameLogic;
    public FresherAchievement(GameMap gameMap, GameLogic gamelogic){
        super("Fresher","Complete your first year in UniSim (Play for a minute)");
        this.gameLogic = gamelogic;
        this.gameMap = gameMap;
    }

    @Override
    protected boolean getUnlockStatus() {
        return (5.0f * 60.0f) - gameLogic.getRemainingTime() >= 60.0f;
    }

    @Override
    protected void DisplayAchievement() {
        //TODO: Display Achievement In UI pop up
    }
    public void setGameMap(GameMap gameMap) { this.gameMap = gameMap;}
}
