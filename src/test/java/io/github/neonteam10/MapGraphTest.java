package io.github.neonteam10;

import org.junit.jupiter.api.Test;

import io.github.neonteam10.graphs.BuildingNode;
import io.github.neonteam10.graphs.MapGraph;
import io.github.neonteam10.graphs.MapNode;
import io.github.neonteam10.map.Building;

public class MapGraphTest {

    @Test
    public void oneBuilding() {
        MapGraph graph = new MapGraph();
        Building building1 = new Building(null, 0, 0);
        //graph.addBuilding(building1);
    }
    @Test
    public void oneRoad() {
        MapGraph graph = new MapGraph();
        Building road1 = new Building(null, 0, 0,true);
        graph.addBuilding(road1);
    }
    @Test
    public void oneBuildingOneRoad() {
        MapGraph graph = new MapGraph();
        Building building1 = new Building(null, 0, 0);
        Building road1 = new Building(null, 1, 1,true);

        //graph.addBuilding(building1);
        graph.addBuilding(road1);
    }

    @Test
    public void oneBuildingOneRoadConnected() {
        MapGraph graph = new MapGraph();
        Building building1 = new Building(null, 0, 0);
        Building road1 = new Building(null, 1, 1,true);
        
        //graph.addBuilding(building1);
        graph.addBuilding(road1);
    }
}
