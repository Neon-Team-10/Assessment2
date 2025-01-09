package io.github.neonteam10.headless;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import io.github.neonteam10.map.BuildingPrefab;

public class BuildingTestHeadless extends AbstractHeadlessTest{

    public TiledMap map() {
        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        // Load tiled map.
        assetManager.load("maps/map.tmx", TiledMap.class);
        assetManager.finishLoading();

        return assetManager.get("maps/map.tmx", TiledMap.class);
    }

    @Test
    public void generateAllPrefabs() {
    // Create building types for each prefab layer in the map.
    ArrayList<BuildingPrefab> availablePrefabs = new ArrayList<>();
    for (var layer : map().getLayers()) {
        if (layer.getName().startsWith("Prefab: ")) {
            // Extract prefab name, e.g. Accomodation.
            var prefabName = layer.getName().substring("Prefab: ".length());
            availablePrefabs.add(new BuildingPrefab(prefabName, (TiledMapTileLayer) layer));
        }
    }
    assertTrue(availablePrefabs.size() == 5);
    for (BuildingPrefab buildingPrefab : availablePrefabs) {
        System.out.println(buildingPrefab.getName());
    }
}
    @Test
    public void roadPrefabTest() {
        BuildingPrefab prefab = null;
        for (var layer : map().getLayers()) {
            if (layer.getName().startsWith("Prefab: Road")) {
                // Extract prefab name, e.g. Accomodation.
                var prefabName = layer.getName().substring("Prefab: ".length());
                prefab = new BuildingPrefab(prefabName, (TiledMapTileLayer) layer);
            }
        }
        assertEquals("Road",prefab.getName());
    }
    @Test
    public void accomodationPrefabTest() {
        BuildingPrefab prefab = null;
        for (var layer : map().getLayers()) {
            if (layer.getName().startsWith("Prefab: Accommodation")) {
                // Extract prefab name, e.g. Accomodation.
                var prefabName = layer.getName().substring("Prefab: ".length());
                prefab = new BuildingPrefab(prefabName, (TiledMapTileLayer) layer);
            }
        }
        assertEquals("Accommodation",prefab.getName());
    }
    @Test
    public void canteenPrefabTest() {
        BuildingPrefab prefab = null;
        for (var layer : map().getLayers()) {
            if (layer.getName().startsWith("Prefab: Canteen")) {
                // Extract prefab name, e.g. Accomodation.
                var prefabName = layer.getName().substring("Prefab: ".length());
                prefab = new BuildingPrefab(prefabName, (TiledMapTileLayer) layer);
            }
        }
        assertEquals("Canteen",prefab.getName());
    }
    @Test
    public void studyPrefabTest() {
        BuildingPrefab prefab = null;
        for (var layer : map().getLayers()) {
            if (layer.getName().startsWith("Prefab: Study")) {
                // Extract prefab name, e.g. Accomodation.
                var prefabName = layer.getName().substring("Prefab: ".length());
                prefab = new BuildingPrefab(prefabName, (TiledMapTileLayer) layer);
            }
        }
        assertEquals("Study",prefab.getName());
    }
    @Test
    public void recreationPrefabTest() {
        BuildingPrefab prefab = null;
        for (var layer : map().getLayers()) {
            if (layer.getName().startsWith("Prefab: Recreation")) {
                // Extract prefab name, e.g. Accomodation.
                var prefabName = layer.getName().substring("Prefab: ".length());
                prefab = new BuildingPrefab(prefabName, (TiledMapTileLayer) layer);
            }
        }
        assertEquals("Recreation",prefab.getName());
    }


    @Test
    public void accommodationBuilding() {
        BuildingPrefab prefab = null;
        for (var layer : map().getLayers()) {
            if (layer.getName().startsWith("Prefab: Accommodation")) {
                // Extract prefab name, e.g. Accomodation.
                var prefabName = layer.getName().substring("Prefab: ".length());
                prefab = new BuildingPrefab(prefabName, (TiledMapTileLayer) layer);
            }
        }
        assertEquals("Accommodation",prefab.getName());
    }
}
