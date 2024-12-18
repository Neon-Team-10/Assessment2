package io.github.neonteam10.map;

/**
 * A class which represents a building placed on the map.
 */
public class Building {
    private final BuildingPrefab prefab;
    private final int x;
    private final int y;
    private final boolean road;

    public Building(BuildingPrefab prefab, int x, int y) {
        this.prefab = prefab;
        this.x = x;
        this.y = y;
        this.road = false;
        //System.out.println("Building " + prefab.getName() + " at " + x + ", " + y);
    }

    public Building(BuildingPrefab prefab, int x, int y, boolean road) {
        this.prefab = prefab;
        this.x = x;
        this.y = y;
        this.road = road;
        //System.out.println("Building " + prefab.getName() + " at " + x + ", " + y);
    }

    /**
     * @return the prefab this building was constructed from
     */
    public BuildingPrefab getPrefab() {
        return prefab;
    }

    /**
     * @return the x position of the building in tiles on the game map
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y position of the building in tiles on the game map
     */
    public int getY() {
        return y;
    }
    /**
    * @return the boolean representing whether this building is a road or not
    */
    public boolean getRoad() {
        return road;
    }
}