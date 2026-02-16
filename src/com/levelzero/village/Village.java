package com.levelzero.village;

import com.levelzero.village.building.Building;
import com.levelzero.village.building.Hostel;
import com.levelzero.village.building.Merchant;

import java.util.ArrayList;

public class Village {
    /**
     * The Village class represents a village in the game. It contains a hostel, a merchant and a mine.
     */
    private final String name;
    private int BuildingNumber;
    private ArrayList<Building> buildings;

    public Village(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBuildingNumber() {
        return BuildingNumber;
    }

    public void addBuilding(Building building) {
        if (buildings == null) {
            buildings = new ArrayList<>();
        }
        buildings.add(building);
        BuildingNumber++;
    }

    public void removeBuilding(Building building) {
        if (buildings != null && buildings.remove(building)) {
            BuildingNumber--;
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

//    public ArrayList<Building> getMineBuildings() {
//        ArrayList<Building> mineBuildings = new ArrayList<>();
//        if (buildings != null) {
//            for (Building building : buildings) {
//                if (building instanceof Mine) {
//                    mineBuildings.add(building);
//                }
//            }
//        }
//        return mineBuildings;
//    }
}
