package com.levelzero.village.building;

import com.levelzero.creature.LivingCreature;

/**
 * The Hostel class represents a building in the village that can heal. It implements the Building interface and has a price associated with it.
 */
public class Hostel implements Building {
    private final int price;

    /**
     * Constructor for the Hostel class. It initializes the price of the hostel with a random method.
     */
    public Hostel() {
        this.price = this.getRandomPrice();
    }

    /**
     * Getter method for the price of the hostel.
     * @return the price of the hostel
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method to get the random price.
     * @return the price of the hostel, which is a random integer between 10 and 20
     */
    private int getRandomPrice() {
        return (int) (Math.random() * 10) + 10;
    }

    /**
     * Method to heal a living creature. It heals the target creature to its maximum HP.
     * @param target the living creature to be healed
     */
    public void heal(LivingCreature target) {
        target.healHp(target.getMaxHp());
    }
}