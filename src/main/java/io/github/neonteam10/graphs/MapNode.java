package io.github.neonteam10.graphs;

import io.github.neonteam10.map.Building;

import java.util.ArrayList;
import java.util.List;

/**
 * These represent the physical map locations of the buildings and roads.
 */
public class MapNode {
    Building building;
    List<MapNode> neighbours;
    boolean road;
    BuildingNode buildingNode;

    public MapNode(Building building) {
        this.building = building;
        this.neighbours = new ArrayList<>();
        this.road = building.getRoad();
        this.buildingNode = null;

    }

    public boolean addBuildingNode(BuildingNode node) {
        if (buildingNode == null) {
            buildingNode = node;
            return true;
        }
        return false;
    }

    public BuildingNode getBuildingNode() {
        return buildingNode;
    }

    public boolean addNeighbour(MapNode neighbour) {
        return this.neighbours.add(neighbour);
    }

    public boolean addNeighbours(List<MapNode> neighbours) {
        return this.neighbours.addAll(neighbours);
    }

    public List<MapNode> getNeighbours() {
        return neighbours;
    }

    public int getX(){
        return this.building.getX();
    }

    public int getY(){
        return this.building.getY();
    }

    public int getWidth(){
        return this.building.getPrefab().getWidth();
    }

    public int getHeight(){
        return this.building.getPrefab().getHeight();
    }

    public boolean isRoad() {
        return road;
    }

    @Override
    public String toString() {
        return building.getPrefab().getName() + " at " + building.getX() + " " + building.getY();
    }

    public int getDistance(MapNode targetNode) {
        if (targetNode.buildingNode == null || buildingNode == null) {
            return Integer.MAX_VALUE;
        }
        return buildingNode.connections.get(targetNode.buildingNode);
    }
}
