package com.levelzero.village;

import com.levelzero.village.building.Building;

public class VillageBuilder {
    /**
     * The VillageBuilder class is a builder for the Village class. It allows us to create a village and add buildings to it.
     */
    private final Village village;

    public VillageBuilder(String name) {
        this.village = new Village(name);
    }

    public VillageBuilder addBuilding(Building building) {
        this.village.addBuilding(building);
        return this;
    }

    public Village build() {
        return this.village;
    }
}
