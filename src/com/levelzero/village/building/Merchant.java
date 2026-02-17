package com.levelzero.village.building;

import com.levelzero.item.potion.Potion;
import com.levelzero.item.potion.PotionFactory;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.item.weapon.WeaponFactory;

import java.util.ArrayList;

public class Merchant implements Building {
    /**
     * The MerchantBuilding class represents a building where players can buy and sell items such as weapons and potions.
     */

    ArrayList<Weapon> weaponStock;
    ArrayList<Potion> potionStock;

    public Merchant(int level) {
        this.weaponStock = new ArrayList<>();
        this.potionStock = new ArrayList<>();
        this.generateStock(level);
    }

    private void generateStock(int level) {
        for (int i = 0; i < 3; i++) {
            this.weaponStock.add(WeaponFactory.createWeapon(WeaponFactory.WeaponType.SWORD, "sword", 3*level, 5*level));
            this.weaponStock.add(WeaponFactory.createWeapon(WeaponFactory.WeaponType.STAFF, "staff", 3*level, 5*level));
            this.weaponStock.add(WeaponFactory.createWeapon(WeaponFactory.WeaponType.SHIELD, "shield", level, 5*level));
        }

        for (int i = 0; i < 3; i++) {
            this.potionStock.add(PotionFactory.createSmallPotion());
            this.potionStock.add(PotionFactory.createMediumPotion());
            this.potionStock.add(PotionFactory.createLargePotion());
        }
    }

    public ArrayList<Weapon> getWeapons() {
        return weaponStock;
    }

    public void addWeapon(Weapon weapon) {
        this.weaponStock.add(weapon);
    }

    public void removeWeapon(Weapon weapon) {
        this.weaponStock.remove(weapon);
    }

    public void showWeapons() {
        System.out.println("Weapons available for sale:");
        for (Weapon weapon : weaponStock) {
            System.out.println(weapon.getName());
        }
    }

    public ArrayList<Potion> getPotions() {
        return potionStock;
    }

    public void addPotion(Potion potion) {
        this.potionStock.add(potion);
    }

    public void removePotion(Potion potion) {
        this.potionStock.remove(potion);
    }

    public void showPotions() {
        System.out.println("Potions available for sale:");
        for (Potion potion : potionStock) {
            System.out.println(potion.getName());
        }
    }
}
