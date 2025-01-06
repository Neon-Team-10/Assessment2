package io.github.neonteam10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.github.neonteam10.map.Building;

//import io.github.neonteam10.map.Building;

public class BuildingTest {

    @Test
    public void BuildingLocation() {
        int x = 1;
        int y = 1;
        Building building = new Building(null, x, y);

        assertEquals(building.getX(), x);
        assertEquals(building.getY(), y);
    }
    @Test
    public void Road() {
        int x = 1;
        int y = 1;
        Building building = new Building(null, x, y,true);

        assertTrue(building.getRoad());
    }
    @Test
    public void SpecifiedNotRoad() {
        int x = 1;
        int y = 1;
        Building building = new Building(null, x, y,false);

        assertFalse(building.getRoad());
    }
    @Test
    public void UnspecifiedNotRoad() {
        int x = 1;
        int y = 1;
        Building building = new Building(null, x, y);

        assertFalse(building.getRoad());
    }
}
