package com.levelzero.village.building;

public class BuildingFactory {
    static Building createBuilding(BuildingType type) {
        return switch (type) {
            case HOSTEL -> new Hostel();
            case MERCHANT -> new Merchant();
            //case MINE -> return new Mine();
            default -> throw new IllegalArgumentException("Invalid building type");
        };
    }
}
