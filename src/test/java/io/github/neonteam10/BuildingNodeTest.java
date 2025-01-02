package io.github.neonteam10;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import io.github.neonteam10.graphs.BuildingNode;
import io.github.neonteam10.map.Building;
import io.github.neonteam10.map.BuildingPrefab;

public class BuildingNodeTest {

    @Test
    public void oneNode(){
    BuildingPrefab prefab = null;
    /*
    TiledMap map = new TiledMap();
        for (var layer : map.getLayers()) {
            prefab = new BuildingPrefab("accommodation", (TiledMapTileLayer) layer);
        }
    */
    Building building1 = new Building(prefab,1,1);
    BuildingNode node1 = new BuildingNode(building1);
    System.out.println(node1);
    }

    @Test
    public void twoNodes(){ 
    BuildingPrefab prefab = null;
    /*    
    TiledMap map = new TiledMap();
        for (var layer : map.getLayers()) {
            prefab = new BuildingPrefab("accommodation", (TiledMapTileLayer) layer);
        }
    */
    Building building1 = new Building(prefab,1,1);
    BuildingNode node1 = new BuildingNode(building1);
    System.out.println(node1);

    Building building2 = new Building(prefab,1,1);
    BuildingNode node2 = new BuildingNode(building2);
    System.out.println(node2);

    }
}
