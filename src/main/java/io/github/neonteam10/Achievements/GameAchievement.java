package io.github.neonteam10.Achievements;

public abstract class GameAchievement {
    public String name;
    public String achievementIcon;
    public String description;
    private boolean unlocked = false;

    public GameAchievement(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public void Unlock ()
    {
        if (!unlocked && getUnlockStatus()) {
            this.unlocked = true;
            DisplayAchievement();
        }
    }

    public boolean getUnlocked(){
        return unlocked;
    }
    protected abstract boolean getUnlockStatus();

    protected abstract void DisplayAchievement();




}
