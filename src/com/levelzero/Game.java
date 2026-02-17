package com.levelzero;

import com.levelzero.creature.hero.Hero;
import com.levelzero.creature.hero.HeroFactory;
import com.levelzero.creature.hero.HeroType;
import com.levelzero.village.Village;

public class Game {
    private Hero hero;
    private Village village;

    public Game() {
    }

    public void start(String heroName, HeroType heroType) {
        if (heroName == null || heroName.trim().isEmpty()) {
            throw new IllegalArgumentException("Hero name cannot be null or empty");
        }
        if (heroType == null) {
            throw new IllegalArgumentException("Hero type cannot be null");
        }

        this.hero = createHero(heroName, heroType);
        this.village = createVillage("Village", 1);
    }

    private Hero createHero(String name, HeroType type) {
        return HeroFactory.createHero(type, name);
    }

    private Village createVillage(String name, int level) {
        return new Village(name, level);
    }

    public Hero getHero() {
        return hero;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public boolean isInitialized() {
        return hero != null && village != null;
    }
}


