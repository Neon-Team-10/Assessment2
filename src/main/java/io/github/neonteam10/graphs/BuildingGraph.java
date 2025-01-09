package io.github.neonteam10.graphs;


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

    /*
     * Gives a flat score bonus based on how close the canteens are to other facilities.
     * For every accommodation building within radius 5 from a canteen, you get a bonus of 5
     * For every study building within radius 15 from a canteen, you get a bonus of 10
     */
    public float canteenBonus() {
        float bonus = 0.0f;
        for (BuildingNode canteenNode : nodes) {
            if (canteenNode.building.isCanteen()) {
                for (BuildingNode neighbour : canteenNode.connections.keySet()) {
                    if (neighbour.building.isAccommodation()) {
                        int distanceToAccommodation = canteenNode.connections.get(neighbour);
                        if (Math.abs(distanceToAccommodation) < 5){
                            bonus += 0.5;
                        }
                    }
                    if (neighbour.building.isStudy()) {
                        int distanceToStudy = canteenNode.connections.get(neighbour);
                        if (Math.abs(distanceToStudy) < 15) {
                            bonus += 1;
                        }
                    }
                }
            }
        }
        return bonus;
    }

    /*
     * Gives a flat score bonus based on how close the accommodation are to other facilities.
     * For every accommodation building within radius 5 from a study building, you get a bonus of 5
     * For every accommodation building within radius 15 from a recreation building, you get a bonus of 10
     */
    public float accommodationBonus() {
        float bonus = 0.0f;
        for (BuildingNode accommNode : nodes) {
            if (accommNode.building.isAccommodation()) {
                for (BuildingNode neighbour : accommNode.connections.keySet()) {
                    if (neighbour.building.isStudy()) {
                        int distanceToAccommodation = accommNode.connections.get(neighbour);
                        if (distanceToAccommodation < 5) bonus += 5;
                    }
                    if (neighbour.building.isRecreation()) {
                        int distanceToStudy = accommNode.connections.get(neighbour);
                        if (distanceToStudy < 15) bonus += 10;
                    }
                }
            }
        }
        return bonus;
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
