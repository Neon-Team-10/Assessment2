package io.github.neonteam10.Achievements;
import com.badlogic.gdx.maps.tiled.TiledMap;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.GameLogic;


public class DiverseAchievement extends GameAchievement {

    private final GameMap gameMap;
    private final GameLogic gameLogic;

    public DiverseAchievement (GameLogic gamelogic){
        super("Diverse","EnterPathHere","Place every type of building.");
        TiledMap tiledMap = new TiledMap();
        this.gameMap= new GameMap(tiledMap);
        this.gameLogic = gamelogic;
    }

    @Override
    protected boolean getUnlockStatus(){
        return gameMap.getBuildingCount(gameLogic.findPrefab("Accommodation")) >= 1 && gameMap.getBuildingCount(gameLogic.findPrefab("Education")) >= 1 && gameMap.getBuildingCount(gameLogic.findPrefab("Recreation")) >= 1;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
}