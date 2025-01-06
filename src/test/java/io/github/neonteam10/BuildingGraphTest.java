package io.github.neonteam10;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import io.github.neonteam10.graphs.BuildingGraph;
import io.github.neonteam10.graphs.BuildingNode;
import io.github.neonteam10.map.Building;
import io.github.neonteam10.map.BuildingPrefab;

public class BuildingGraphTest {

    @Test
    public void oneBuilding() {
        TiledMap map = new TiledMap();
        BuildingPrefab prefab = null;
        for (var layer : map.getLayers()) {
            prefab = new BuildingPrefab("accommodation", (TiledMapTileLayer) layer);
        }
        Building building = new Building(prefab,1,1);
        BuildingNode buidingNode = new BuildingNode(building);
        BuildingGraph graph = new BuildingGraph();

        graph.addNode(buidingNode);
        System.out.println(graph);
    }

    @Test
    public void twoBuildings() {
        BuildingPrefab prefab = null;
        /*
        TiledMap map = new TiledMap();
        for (var layer : map.getLayers()) {
            prefab = new BuildingPrefab("accommodation", (TiledMapTileLayer) layer);
        }
        */

        Building building1 = new Building(prefab,1,1);
        BuildingNode buildingNode1 = new BuildingNode(building1);
        

        Building building2 = new Building(prefab,1,1);
        BuildingNode buildingNode2 = new BuildingNode(building2);

        BuildingGraph graph = new BuildingGraph();
        graph.addNode(buildingNode1);
        graph.addNode(buildingNode2);

        buildingNode1.addConnection(buildingNode2);
        System.out.println(graph);
    }

}
