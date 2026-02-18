package com.levelzero.utils;

import com.levelzero.creature.hero.Hero;
import com.levelzero.item.potion.Potion;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.village.building.Merchant;

public class TradeUtils {
    private static TradeUtils instance;

    private TradeUtils() {}

    public static TradeUtils getInstance() {
        if (instance == null) {
            instance = new TradeUtils();
        }
        return instance;
    }

    public boolean buyItem(Hero hero, Merchant merchant, Weapon weapon) {
        /**
         * Implement the logic to buy a weapon from the merchant
         */
        if (hero.getGold() - weapon.getPrice() < 0) {
            return false;
        }
        merchant.removeWeapon(weapon);
        hero.getInventory().addWeapon(weapon);
        hero.setGold(hero.getGold() - weapon.getPrice());
        return true;
    }

    public boolean buyItem(Hero hero, Merchant merchant, Potion potion) {
        /**
         * Implement the logic to buy a potion from the merchant
         */
        if (hero.getGold() - potion.getPrice() < 0) {
            return false;
        }
        merchant.removePotion(potion);
        hero.getInventory().addPotion(potion);
        hero.setGold(hero.getGold() - potion.getPrice());
        return true;
    }

    public void sellItem(Hero hero, Merchant merchant, Weapon weapon) {
        /**
         * Implement the logic to sell a weapon to the merchant
         */
        hero.getInventory().removeWeapon(weapon);
        merchant.addWeapon(weapon);
        hero.setGold(hero.getGold() + weapon.getPrice());
    }
    public void sellItem(Hero hero, Merchant merchant, Potion potion) {
        /**
         * Implement the logic to sell a potion to the merchant
         */
        hero.getInventory().removePotion(potion);
        merchant.addPotion(potion);
        hero.setGold(hero.getGold() + potion.getPrice());
    }
}
