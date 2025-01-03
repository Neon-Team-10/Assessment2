package io.github.neonteam10.Achievements;
import com.badlogic.gdx.maps.tiled.TiledMap;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.GameLogic;


public class AccommodationAchievement extends GameAchievement {

    private final GameMap gameMap;
    private final GameLogic gameLogic;

    public AccommodationAchievement (GameLogic gamelogic){
        super("House Party!","EnterPathHere","Place five Accommodation buildings.");
        TiledMap tiledMap = new TiledMap();
        this.gameMap= new GameMap(tiledMap);
        this.gameLogic = gamelogic;
    }

    @Override
    protected boolean getUnlockStatus(){
        return gameMap.getBuildingCount(gameLogic.findPrefab("Accommodation")) >= 5;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
}