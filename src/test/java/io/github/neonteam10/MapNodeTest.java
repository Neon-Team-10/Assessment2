package io.github.neonteam10;

import org.junit.jupiter.api.Test;

import io.github.neonteam10.graphs.MapNode;
import io.github.neonteam10.map.Building;

public class MapNodeTest {

    @Test
    public void oneBuilding() {
        Building building1 = new Building(null, 0, 0);
        MapNode mapNode1 = new MapNode(building1);

    }
}
