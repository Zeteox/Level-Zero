package com.levelzero.item.weapon;

import com.levelzero.models.Weapon;
import com.levelzero.models.Shield;
import com.levelzero.models.Sword;
import com.levelzero.models.Staff;

// Factory to create different weapons in the game
public class WeaponFactory {
    
    // Available weapon types
    public enum WeaponType {
        SHIELD,
        SWORD,
        STAFF
    }
    
    // Creates a weapon based on the specified type
    public static Weapon createWeapon(WeaponType type, String name, int stat, int price) {
        if (type == null) {
            throw new IllegalArgumentException("Weapon type cannot be null");
        }
        
        switch (type) {
            case SHIELD:
                return new Shield(name, stat, price);
            case SWORD:
                return new Sword(name, stat, price);
            case STAFF:
                return new Staff(name, stat, price);
            default:
                throw new IllegalArgumentException("Unknown weapon type: " + type);
        }
    }
    
    // Creates a shield with the specified attributes
    public static Shield createShield(String name, int defence, int price) {
        return new Shield(name, defence, price);
    }
    
    // Creates a sword with the specified attributes
    public static Sword createSword(String name, int damage, int price) {
        return new Sword(name, damage, price);
    }
    
    // Creates a magic staff with the specified attributes
    public static Staff createStaff(String name, int damage, int price) {
        return new Staff(name, damage, price);
    }
}
