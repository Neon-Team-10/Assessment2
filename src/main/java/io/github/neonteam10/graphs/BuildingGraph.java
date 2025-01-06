package io.github.neonteam10.graphs;

import io.github.neonteam10.map.Building;

import java.util.ArrayList;
import java.util.List;

/**
 * This graph is used to show the relationship between buildings (do they have a road connecting them?).
 */
public class BuildingGraph {
    final float BEST_DISTANCE = 6f;
    List<BuildingNode> nodes;
    float accommodationQualityScore;

    public BuildingGraph() {
        nodes = new ArrayList<>();
        accommodationQualityScore = 0;
    }

    public boolean addNode(BuildingNode node) {
        return nodes.add(node);
    }


    // Returns the accommodation quality score (some function of distance between accommodation and all other building types
    public void calculateAccommodationQualityScore() {
        float total = 0;
        for (BuildingNode node : nodes) {
            if (node.building.isAccommodation()) {
                int bestDistCanteen = Integer.MAX_VALUE;
                int bestDistRecreation = Integer.MAX_VALUE;
                int bestDistStudy = Integer.MAX_VALUE;
                for (BuildingNode neighbour : node.connections.keySet()) {
                    if (neighbour.building.isCanteen()){
                        bestDistCanteen = Math.min(bestDistCanteen, node.connections.get(neighbour));
                    }
                    else if (neighbour.building.isRecreation()){
                        bestDistRecreation = Math.min(bestDistRecreation, node.connections.get(neighbour));
                    }
                    else if (neighbour.building.isStudy()){
                        bestDistStudy = Math.min(bestDistStudy, node.connections.get(neighbour));
                    }
                }
                total += (BEST_DISTANCE/bestDistCanteen + BEST_DISTANCE/bestDistRecreation + BEST_DISTANCE/bestDistStudy);
            }
        }
        accommodationQualityScore = total;
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
