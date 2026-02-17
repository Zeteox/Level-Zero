package com.levelzero.creature.hero;

public class HeroFactory {

    public static Hero createHero(HeroType type, String name) {
        HeroBuilder builder = new HeroBuilder().setName(name);
        switch (type) {
            case WARRIOR:
                return builder
                        .setMaxHp(150)
                        .setGold(10)
                        .setDamage(20)
                        .setDefense(5)
                        .setInventorySpace(10)
                        .setBonus(5)
                        .buildWarrior();

            case MAGE:
                return builder
                        .setMaxHp(80)
                        .setGold(50)
                        .setDamage(5)
                        .setDefense(2)
                        .setInventorySpace(20)
                        .setBonus(15)
                        .buildMage();

            case PALADIN:
                return builder
                        .setMaxHp(120)
                        .setGold(20)
                        .setDamage(15)
                        .setDefense(10)
                        .setInventorySpace(15)
                        .buildPaladin();

            default:
                throw new IllegalArgumentException("Invalid hero class type: " + type);
        }
    }
}