package com.levelzero.creature.hero;

public class HeroFactory {

    public static Hero createHero(HeroType type, String name) {
        HeroBuilder builder = new HeroBuilder().setName(name);
        switch (type) {
            case WARRIOR:
                return builder
                        .setMaxHp(250)
                        .setGold(10)
                        .setDamage(10)
                        .setDefense(2)
                        .setInventorySpace(10)
                        .setBonus(5)
                        .buildWarrior();

            case MAGE:
                return builder
                        .setMaxHp(150)
                        .setGold(50)
                        .setDamage(5)
                        .setDefense(0)
                        .setInventorySpace(20)
                        .setBonus(15)
                        .buildMage();

            case PALADIN:
                return builder
                        .setMaxHp(200)
                        .setGold(20)
                        .setDamage(7)
                        .setDefense(5)
                        .setInventorySpace(15)
                        .buildPaladin();

            default:
                throw new IllegalArgumentException("Invalid hero class type: " + type);
        }
    }
}