package io.github.neonteam10.graphs;

import io.github.neonteam10.map.Building;

import java.util.ArrayList;
import java.util.List;

/**
 * This graph is used to show the relationship between buildings (do they have a road connecting them?).
 */
public class BuildingGraph {
    List<BuildingNode> nodes;

    public BuildingGraph() {
        nodes = new ArrayList<>();
    }

    public boolean addNode(BuildingNode node) {
        return nodes.add(node);
    }

    @Override
    public String toString() {
        String out = "BuildingGraph: \n";
        for (BuildingNode node : nodes) {
            out += node + " : {";
            for (BuildingNode node2 : node.connections.keySet()) {
                out += node2 + ":" + node.connections.get(node2) + ", ";
            }
            out += "}\n";
        }
        return out;
    }
}
