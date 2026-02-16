package com.levelzero.village.building;

public class Hostel implements Building {
    private final int price;

    public Hostel() {
        this.price = this.getRandomPrice();
    }

    public int getPrice() {
        return price;
    }

    private int getRandomPrice() {
        /**
         * This method generates a random price for the hostel building. The price is a random integer between 10 and 20.
         */
        return (int) (Math.random() * 10) + 10;
    }

//    public void heal(LivingCreature target) {
//        target.healHp(target.getMaxHp());
//    }
}