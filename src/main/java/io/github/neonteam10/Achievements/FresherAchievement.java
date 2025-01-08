package io.github.neonteam10.Achievements;
import io.github.neonteam10.GameLogic;

public class FresherAchievement extends GameAchievement{
    private final GameLogic gameLogic;
    public FresherAchievement(GameLogic gamelogic){
        super("Fresher","Complete your first year in UniSim (Play for a minute)");
        this.gameLogic = gamelogic;
    }

    @Override
    protected boolean getUnlockStatus() {
        return (5.0f * 60.0f) - gameLogic.getRemainingTime() >= 60.0f;
    }

    @Override
    protected void DisplayAchievement() {
        //TODO: Display Achievement In UI pop up
    }
}
