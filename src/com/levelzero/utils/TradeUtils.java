package com.levelzero.utils;

import com.levelzero.creature.hero.Hero;
import com.levelzero.item.potion.Potion;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.village.building.Merchant;

/**
 * TradeUtils is a utility class that provides methods for buying and selling items between the hero and the merchant.
 * It uses the singleton pattern to ensure that only one instance of TradeUtils exists throughout the application.
 */
public class TradeUtils {
    private static TradeUtils instance;

    private TradeUtils() {}

    /**
     * Returns the single instance of TradeUtils. If the instance does not exist, it creates a new one.
     * @return instance of TradeUtils
     */
    public static TradeUtils getInstance() {
        if (instance == null) {
            instance = new TradeUtils();
        }
        return instance;
    }

    /**
     * Implements the logic to buy a weapon from the merchant.
     */
    public boolean buyItem(Hero hero, Merchant merchant, Weapon weapon) {
        if (hero.getGold() - weapon.getPrice() < 0) {
            return false;
        }
        merchant.removeWeapon(weapon);
        hero.getInventory().addWeapon(weapon);
        hero.setGold(hero.getGold() - weapon.getPrice());
        return true;
    }

    /**
     * Implement the logic to buy a potion from the merchant
     */
    public boolean buyItem(Hero hero, Merchant merchant, Potion potion) {

        if (hero.getGold() - potion.getPrice() < 0) {
            return false;
        }
        merchant.removePotion(potion);
        hero.getInventory().addPotion(potion);
        hero.setGold(hero.getGold() - potion.getPrice());
        return true;
    }

    /**
     * Implement the logic to sell a weapon to the merchant
     */
    public void sellItem(Hero hero, Merchant merchant, Weapon weapon) {
        hero.getInventory().removeWeapon(weapon);
        merchant.addWeapon(weapon);
        hero.setGold(hero.getGold() + weapon.getPrice());
    }

    /**
     * Implement the logic to sell a potion to the merchant
     */
    public void sellItem(Hero hero, Merchant merchant, Potion potion) {

        hero.getInventory().removePotion(potion);
        merchant.addPotion(potion);
        hero.setGold(hero.getGold() + potion.getPrice());
    }
}
