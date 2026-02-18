package com.levelzero.village.building;

import com.levelzero.item.potion.Potion;
import com.levelzero.item.potion.PotionFactory;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.item.weapon.WeaponFactory;

import java.util.ArrayList;

/**
 * The Merchant class represents a building in the village that sells weapons and potions.
 * It implements the Building interface and has a stock of weapons and potions that can be bought by the player.
 */
public class Merchant implements Building {

    ArrayList<Weapon> weaponStock;
    ArrayList<Potion> potionStock;

    /**
     * Constructor for the Merchant class. It initializes the weapon and potion stock.
     * @param level the level of the merchant, which determines the quality of the weapons in the stock
     */
    public Merchant(int level) {
        this.weaponStock = new ArrayList<>();
        this.potionStock = new ArrayList<>();
        this.generateStock(level);
    }

    /**
     * Method to generate the stock of weapons and potions for the merchant. The quality of the weapons is determined by the level of the merchant.
     * @param level the level of the merchant, which determines the quality of the weapons in the stock
     */
    private void generateStock(int level) {
        this.weaponStock.add(WeaponFactory.createWeapon(WeaponFactory.WeaponType.SWORD, "sword", 3*level, 5*level));
        this.weaponStock.add(WeaponFactory.createWeapon(WeaponFactory.WeaponType.STAFF, "staff", 3*level, 5*level));
        this.weaponStock.add(WeaponFactory.createWeapon(WeaponFactory.WeaponType.SHIELD, "shield", level, 5*level));

        this.potionStock.add(PotionFactory.createSmallPotion());
        this.potionStock.add(PotionFactory.createMediumPotion());
        this.potionStock.add(PotionFactory.createLargePotion());
    }

    /**
     * Getters and setters for the weapon and potion stock, as well as methods to show the available weapons and potions for sale.
     * @return the weapon stock of the merchant
     */
    public ArrayList<Weapon> getWeapons() {
        return weaponStock;
    }

    /**
     * Method to add a weapon to the merchant's stock.
     * @param weapon
     */
    public void addWeapon(Weapon weapon) {
        this.weaponStock.add(weapon);
    }

    /**
     * Method to remove a weapon from the merchant's stock.
     * @param weapon
     */
    public void removeWeapon(Weapon weapon) {
        this.weaponStock.remove(weapon);
    }

    /**
     * Getter method for the potion stock of the merchant.
     * @return the potion stock of the merchant
     */
    public ArrayList<Potion> getPotions() {
        return potionStock;
    }

    /**
    * Method to add a potion to the merchant's stock.
    * @param potion
    */
    public void addPotion(Potion potion) {
        this.potionStock.add(potion);
    }

    /**
     * Method to remove a potion from the merchant's stock.
     * @param potion
     */
    public void removePotion(Potion potion) {
        this.potionStock.remove(potion);
    }
}
