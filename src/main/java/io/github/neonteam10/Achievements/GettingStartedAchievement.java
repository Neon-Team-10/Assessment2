package io.github.neonteam10.Achievements;

import com.badlogic.gdx.maps.tiled.TiledMap;
import io.github.neonteam10.map.GameMap;

public class GettingStartedAchievement extends GameAchievement {
    private final GameMap gameMap;
    //Achievement for placing a building
    public GettingStartedAchievement(){
        super("Getting Started!","EnterPathHere","Place a building!");
        TiledMap tiledMap = new TiledMap();
        this.gameMap= new GameMap(tiledMap);
    }
    @Override
    protected boolean getUnlockStatus(){
        return gameMap.getTotalBuildingCount() >= 1;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
}
