package io.github.neonteam10.Achievements;

public abstract class GameAchievement {
    public String name;
    public String achievementIcon;
    public String description;
    private boolean unlocked = false;

    public GameAchievement(String name, String achievementIcon, String description)
    {
        this.name = name;
        this.achievementIcon = achievementIcon;
        this.description = description;
    }

    private void Unlock ()
    {
        if (!unlocked && getUnlockStatus()) {
            this.unlocked = true;
            DisplayAchievement();
        }
    }

    protected abstract boolean getUnlockStatus();

    protected abstract void DisplayAchievement();




}
