package com.levelzero.village.building;

public class BuildingFactory {
    public static Building createBuilding(BuildingType type, int level) {
        return switch (type) {
            case HOSTEL -> new Hostel();
            case MERCHANT -> new Merchant(level);
            case MINE -> new Mine(level);
            default -> throw new IllegalArgumentException("Invalid building type");
        };
    }
}
