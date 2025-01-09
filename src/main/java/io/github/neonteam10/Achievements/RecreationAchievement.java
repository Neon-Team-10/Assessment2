package io.github.neonteam10.Achievements;
import com.badlogic.gdx.maps.tiled.TiledMap;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.GameLogic;


public class RecreationAchievement extends GameAchievement {

    private GameMap gameMap;
    private final GameLogic gameLogic;

    public RecreationAchievement (GameLogic gamelogic, GameMap gameMap) {
        super("Fun!","Place five Recreation buildings.");
        this.gameMap= gameMap;
        this.gameLogic = gamelogic;
    }

    @Override
    protected boolean getUnlockStatus(){
        return gameMap.getBuildingCount(gameLogic.findPrefab("Recreation")) >= 5;
    }

    @Override
    protected void DisplayAchievement(){
        //Show achievement
    }
    public void setGameMap(GameMap gameMap) { this.gameMap = gameMap;}

}