package io.github.neonteam10.graphs;

import io.github.neonteam10.map.Building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingNode {
    Building building;
    Map<BuildingNode, Integer> connections;

    public BuildingNode(Building building) {
        this.building = building;
        connections = new HashMap<>();
        connections.put(this, 0);

    }

    public void addConnection(BuildingNode node) {
        connections.put(node, Integer.MAX_VALUE);
    }
}
