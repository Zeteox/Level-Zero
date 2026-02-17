package com.levelzero.item.weapon;

import com.levelzero.creature.attack.MagicAttackStrategy;
import com.levelzero.creature.attack.SimpleAttackStrategy;
import com.levelzero.creature.defense.SimpleDefenseStrategy;

// Factory to create different weapons in the game
public class WeaponFactory {
    
    // Available weapon types
    public enum WeaponType {
        SHIELD,
        SWORD,
        STAFF
    }
    
    // Creates a weapon with strategies based on the specified type
    public static Weapon createWeapon(WeaponType type, String name, int stat, int price) {
        if (type == null) {
            throw new IllegalArgumentException("Weapon type cannot be null");
        }

        switch (type) {
            case SHIELD:
                return createShield(name, stat, price);
            case SWORD:
                return createSword(name, stat, price);
            case STAFF:
                return createStaff(name, stat, price);
            default:
                throw new IllegalArgumentException("Unknown weapon type: " + type);
        }
    }
    
    // Creates a shield with strategies
    public static Shield createShield(String name, int defense, int price) {
        return new Shield(name, defense, price, null, new SimpleDefenseStrategy());
    }
    
    // Creates a sword with strategies
    public static Sword createSword(String name, int damage, int price) {
        return new Sword(name, damage, price, new SimpleAttackStrategy(), null);
    }
    
    // Creates a magic staff with strategies
    public static Staff createStaff(String name, int damage, int price) {
        return new Staff(name, damage, price, new MagicAttackStrategy(), null);
    }
}
