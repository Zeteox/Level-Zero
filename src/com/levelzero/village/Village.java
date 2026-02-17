package com.levelzero.village;

import com.levelzero.village.building.*;

import java.util.ArrayList;

/**
 * The Village class represents a village in the game.
 * The village generate its buildings based on his level, and it provides methods to add, remove, and retrieve buildings from the village.
 */
public class Village {
    private final String name;
    private ArrayList<Building> buildings;
    private final int level;

    /**
     * Constructor for the Village class. It initializes the name of the village and generates the buildings based on his level.
     * @param name  the name of the village
     * @param level the level of the village
     */
    public Village(String name, int level) {
        this.name = name;
        this.buildings = new ArrayList<>();
        this.level = level;
        this.generateVillage(level);
    }

    /**
     * Method to generate the buildings of the village based on his level. It creates a merchant, a hostel, and a mine for the village.
     * @param level the level of the village
     */
    private void generateVillage(int level) {
        this.addBuilding(BuildingFactory.createBuilding(BuildingType.MERCHANT, level));
        this.addBuilding(BuildingFactory.createBuilding(BuildingType.HOSTEL,level));
        this.addBuilding(BuildingFactory.createBuilding(BuildingType.MINE,level));
    }

    /**
     * Getter method for the name of the village.
     * @return the name of the village
     */
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    /**
     * Method to get the number of buildings in the village.
     * @return the number of buildings in the village
     */
    public int getBuildingNumber() {
        return this.buildings.size();
    }

    /**
     * Method to add a building to the village. It checks if the buildings list is null before adding the building.
     * @param building the building to be added to the village
     */
    public void addBuilding(Building building) {
        if (buildings == null) {
            buildings = new ArrayList<>();
        }
        buildings.add(building);
    }

    /**
     * Method to remove a building from the village. It checks if the buildings list is null before removing the building.
     * @param building - the building to be removed
     */
    public void removeBuilding(Building building) {
        if (buildings != null) {
            buildings.remove(building);
        }
    }

    /**
     * Method to get the list of buildings in the village.
     * @return the list of buildings in the village
     */
    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    /**
     * Method to get the list of hostel buildings in the village.
     * @return the list of hostel buildings in the village
     */
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

    /**
     * Method to get the list of merchant buildings in the village.
     * @return the list of merchant buildings in the village
     */
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

    /**
     * Method to get the list of mine buildings in the village.
     * @return the list of mine buildings in the village
     */
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
