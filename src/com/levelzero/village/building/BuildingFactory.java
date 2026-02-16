package com.levelzero.village.building;

public class BuildingFactory {
    static Building createBuilding(BuildingType type, int level) {
        return switch (type) {
            case HOSTEL -> new Hostel();
            case MERCHANT -> new Merchant();
            case MINE -> new Mine(level);
            default -> throw new IllegalArgumentException("Invalid building type");
        };
    }
}
