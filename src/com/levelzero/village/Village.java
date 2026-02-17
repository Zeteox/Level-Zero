package com.levelzero.village;

import com.levelzero.village.building.*;

import java.util.ArrayList;

public class Village {
    /**
     * The Village class represents a village in the game. It contains a hostel, a merchant and a mine.
     */
    private final String name;
    private ArrayList<Building> buildings;
    private final int level;

    public Village(String name, int level) {
        this.name = name;
        this.buildings = new ArrayList<>();
        this.level = level;
        this.generateVillage(level);
    }

    private void generateVillage(int level) {
        this.addBuilding(BuildingFactory.createBuilding(BuildingType.MERCHANT, level));
        this.addBuilding(BuildingFactory.createBuilding(BuildingType.HOSTEL,level));
        this.addBuilding(BuildingFactory.createBuilding(BuildingType.MINE,level));
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getBuildingNumber() {
        return this.buildings.size();
    }

    public void addBuilding(Building building) {
        if (buildings == null) {
            buildings = new ArrayList<>();
        }
        buildings.add(building);
    }

    public void removeBuilding(Building building) {
        if (buildings != null) {
            buildings.remove(building);
        }
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Building> getHostelBuildings() {
        ArrayList<Building> hostelBuildings = new ArrayList<>();
        if (buildings != null) {
            for (Building building : buildings) {
                if (building instanceof Hostel) {
                    hostelBuildings.add(building);
                }
            }
        }
        return hostelBuildings;
    }

    public ArrayList<Building> getMerchantBuildings() {
        ArrayList<Building> merchantBuildings = new ArrayList<>();
        if (buildings != null) {
            for (Building building : buildings) {
                if (building instanceof Merchant) {
                    merchantBuildings.add(building);
                }
            }
        }
        return merchantBuildings;
    }

    public ArrayList<Building> getMineBuildings() {
        ArrayList<Building> mineBuildings = new ArrayList<>();
        if (buildings != null) {
            for (Building building : buildings) {
                if (building instanceof Mine) {
                    mineBuildings.add(building);
                }
            }
        }
        return mineBuildings;
    }
}
