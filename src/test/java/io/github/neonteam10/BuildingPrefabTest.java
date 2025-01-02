package io.github.neonteam10;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import io.github.neonteam10.map.BuildingPrefab;

public class BuildingPrefabTest {

    @Test
    public void accomodationPrefab() {
        BuildingPrefab prefab = null;
        TiledMap map = new TiledMap();
        for (var layer : map.getLayers()) {
            prefab = new BuildingPrefab("accommodation", (TiledMapTileLayer) layer);
            System.out.println(prefab);
        }
    }
    @Test
    public void roadPrefab() {
        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new TestFileHandleResolver()));

        // Load tiled map.
        assetManager.load("maps/map.tmx", TiledMap.class);

        // tiledMap = assetManager.get("maps/map.tmx", TiledMap.class);
/*
        for (var layer : tiledMap.getLayers()) {
            System.out.println(layer);
        }*/
        /*
        BuildingPrefab prefab = null;
        TiledMap map = new TiledMap();
        
        for (var layer : map.getLayers()) {
            prefab = new BuildingPrefab("road", (TiledMapTileLayer) layer);
        }
        System.out.println(prefab);*/
    }
}
