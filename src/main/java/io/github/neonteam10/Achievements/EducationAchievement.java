package io.github.neonteam10.Achievements;
import com.badlogic.gdx.maps.tiled.TiledMap;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.GameLogic;


public class EducationAchievement extends GameAchievement {

    private final GameMap gameMap;
    private final GameLogic gameLogic;

    public EducationAchievement(){
        super("Knowledge!","EnterPathHere","Place five educational buildings.");
        TiledMap tiledMap = new TiledMap();
        this.gameMap= new GameMap(tiledMap);
        this.gameLogic = new GameLogic();

    }
    @Override
    protected boolean getUnlockStatus(){
            return gameMap.getBuildingCount(gameLogic.findPrefab("Study")) >= 5;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
}
