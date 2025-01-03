package io.github.neonteam10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.github.neonteam10.graphs.BuildingNode;
import io.github.neonteam10.graphs.MapNode;
import io.github.neonteam10.map.Building;

public class MapNodeTest {

    @Test
    public void oneBuilding() {
        Building building1 = new Building(null, 0, 0);
        MapNode mapNode1 = new MapNode(building1);
        BuildingNode node1 = new BuildingNode(building1);

        mapNode1.addBuildingNode(node1);
        assertEquals(node1, mapNode1.getBuildingNode());

    }
    @Test
    public void oneRoad() {
        Building road1 = new Building(null, 0, 0,true);
        MapNode mapNode1 = new MapNode(road1);
        BuildingNode node1 = new BuildingNode(road1);

        mapNode1.addBuildingNode(node1);
        assertTrue(mapNode1.isRoad());
    }
    @Test
    public void oneBuildingOneRoadnotConnected() {
        Building building1 = new Building(null, 0, 0);
        Building road1 = new Building(null, 0, 0,true);

        MapNode mapNode1 = new MapNode(building1);
        MapNode mapNode2 = new MapNode(road1);

        assertEquals(Integer.MAX_VALUE,mapNode1.getDistance(mapNode2));
    }
    @Test
    public void oneBuildingOneRoadNoBuildingNodes() {
        Building building1 = new Building(null, 0, 0);
        Building road1 = new Building(null, 0, 0,true);

        MapNode mapNode1 = new MapNode(building1);
        MapNode mapNode2 = new MapNode(road1);

        mapNode1.addNeighbour(mapNode2);

        assertEquals(Integer.MAX_VALUE,mapNode1.getDistance(mapNode2));
    }    
    @Test
    public void oneBuildingOneRoadmissingBuildingNode12() {
        Building building1 = new Building(null, 0, 0);
        Building road1 = new Building(null, 0, 0,true);

        MapNode mapNode1 = new MapNode(building1);
        MapNode mapNode2 = new MapNode(road1);

        mapNode2.addBuildingNode(new BuildingNode(road1));

        mapNode1.addNeighbour(mapNode2);

        assertEquals(Integer.MAX_VALUE,mapNode1.getDistance(mapNode2));
    }
    @Test
    public void oneBuildingOneRoadmissingBuildingNode2() {
        Building building1 = new Building(null, 0, 0);
        Building road1 = new Building(null, 0, 0,true);

        MapNode mapNode1 = new MapNode(building1);
        MapNode mapNode2 = new MapNode(road1);

        mapNode1.addBuildingNode(new BuildingNode(building1));

        mapNode1.addNeighbour(mapNode2);

        assertEquals(Integer.MAX_VALUE,mapNode1.getDistance(mapNode2));
    }

    @Test
    public void oneBuildingOneRoadConnected() {
        Building building1 = new Building(null, 0, 0);
        Building road1 = new Building(null, 0, 0,true);

        BuildingNode node1 = new BuildingNode(building1);
        BuildingNode node2 = new BuildingNode(road1);

        MapNode mapNode1 = new MapNode(building1);
        MapNode mapNode2 = new MapNode(road1);

        mapNode1.addBuildingNode(node1);
        mapNode2.addBuildingNode(node2);

        mapNode1.addNeighbour(mapNode2);
        node1.addConnection(node2);

        System.err.println(mapNode1.getDistance(mapNode2));
        //assertNotEquals(Integer.MAX_VALUE, mapNode1.getDistance(mapNode2));
    }
}
