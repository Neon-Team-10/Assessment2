package io.github.neonteam10.graphs;

import io.github.neonteam10.map.Building;

import java.util.ArrayList;
import java.util.List;

/**
 * This graph is purely for literal connections between map nodes (A building can only be connected to roads in this graph)
 */
public class MapGraph {
    List<MapNode> nodes;
    BuildingGraph buildingGraph;
    public MapGraph() {
        nodes = new ArrayList<>();
        buildingGraph = new BuildingGraph();
    }

    public void addBuilding(Building building) {
        MapNode newNode = new MapNode(building);
        nodes.add(newNode);
        addAllNodeNeighbours(newNode);
        System.out.println(newNode.getX() + " " + newNode.getY() + ": " + newNode.getNeighbours());
        if (!building.getRoad()) {
            BuildingNode buildingNode = new BuildingNode(building);
            buildingGraph.addNode(buildingNode);
            recalculateBuildingGraphConnections();
        }
    }

    public void addAllNodeNeighbours(MapNode node) {
        for (MapNode n : nodes) {
            if (connected(node, n)) {
                System.out.println(node + " connected to " + n);
                node.addNeighbour(n);
                n.addNeighbour(node);

            }
        }
    }

    /**
     * Finds out whether two buildings, including roads are connected.
     * @param node1 The node to find if node 2 is connected to
     * @param node2 The node to find if connected to node 1
     * @return A boolean representing whether the two nodes are connected.
     */
    private boolean connected(MapNode node1, MapNode node2) {
        if (node1 == node2) {
            return false;
        }
        if (!(node1.isRoad() || node2.isRoad())) {
            return false;
        }
        else if (node1.isRoad() && node2.isRoad()) {
            int horizontalDiff = Math.abs(node1.getX() - node2.getX());
            int verticalDiff = Math.abs(node1.getY() - node2.getY());
            return (horizontalDiff <= 1 && verticalDiff == 0) || (verticalDiff <= 1 && horizontalDiff == 0);
        }
        else if (node1.isRoad()) {
            return node2.getY() - 1 == node1.getY() && node2.getX() <= node1.getX() && node2.getX() + node2.getWidth() >= node1.getX();
        }
        else {
            return node1.getY() - 1 == node2.getY() && node1.getX() <= node2.getX() && node1.getX() + node1.getWidth() >= node2.getX();
        }
    }

    public void recalculateBuildingGraphConnections() {

    }
}
