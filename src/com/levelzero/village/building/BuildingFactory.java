package com.levelzero.village.building;

/**
 * Factory class to create buildings based on type and a level.
 */
public class BuildingFactory {
    /**
     * Creates a building based on the specified type and level.
     * @param type  The type of building to create.
     * @param level The level of the building (used for certain types).
     * @return A new instance of the specified building type.
     */
    public static Building createBuilding(BuildingType type, int level) {
        return switch (type) {
            case HOSTEL -> new Hostel();
            case MERCHANT -> new Merchant(level);
            case MINE -> new Mine(level);
            default -> throw new IllegalArgumentException("Invalid building type");
        };
    }
}
