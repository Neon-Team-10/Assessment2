package io.github.neonteam10.Achievements;
import io.github.neonteam10.GameLogic;

public class ScoreAchievement extends GameAchievement {
    private final GameLogic gameLogic;
    public ScoreAchievement(GameLogic gamelogic){
        super("Happy!","EnterPathHere","Reach maximum satisfaction!");
        this.gameLogic = gamelogic;
    }

    @Override
    protected boolean getUnlockStatus() {
        return gameLogic.getSatisfaction() == 1.0;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
}
