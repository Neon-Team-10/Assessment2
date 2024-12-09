package io.github.neonteam10.Achievements;
import io.github.neonteam10.GameLogic;

public class FresherAchievement extends GameAchievement{
    private final GameLogic gameLogic;
    public FresherAchievement(){
        super("Fresher","EnterPathHere","Complete your first year in UniSim (Play for a minute)");
        this.gameLogic = new GameLogic();
    }

    @Override
    protected boolean getUnlockStatus() {
        return (5.0f * 60.0f) - gameLogic.getRemainingTime() >= 60.0f;
    }

    @Override
    protected void DisplayAchievement() {
        //Display Achievement In UI pop up
    }
}
