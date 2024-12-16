package io.github.neonteam10;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameScreenTest {

    @Test
    public void InitialTest() {
        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

    // Load tiled map.
    assetManager.load("maps/map.tmx", TiledMap.class);

    CursorManager cursorManager = new CursorManager(assetManager);

    // Create all of our screens.
    GameScreen gameScreen = new GameScreen(assetManager, cursorManager);
    }

}
