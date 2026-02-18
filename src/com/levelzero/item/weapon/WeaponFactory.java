package com.levelzero.item.weapon;

import com.levelzero.creature.attack.MagicAttackStrategy;
import com.levelzero.creature.attack.SimpleAttackStrategy;
import com.levelzero.creature.defense.SimpleDefenseStrategy;

/**
 * A factory class for creating different types of weapons with their strategies. 
 * This class provides methods to create shields, swords and staffs with default or custom strategies.
 */
public class WeaponFactory {
    
    public enum WeaponType {
        SHIELD,
        SWORD,
        STAFF
    }
    
    /**
     * Creates a weapon of the specified type with the given name, stat and price.
     * @param type the type of weapon to create
     * @param name the name of the weapon
     * @param stat the stat of the weapon
     * @param price the price of the weapon
     * @return
     */
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
    
    public static Shield createShield(String name, int defense, int price) {
        return new Shield(name, defense, price, null, new SimpleDefenseStrategy());
    }
    
    public static Sword createSword(String name, int damage, int price) {
        return new Sword(name, damage, price, new SimpleAttackStrategy(), null);
    }
    
    public static Staff createStaff(String name, int damage, int price) {
        return new Staff(name, damage, price, new MagicAttackStrategy(), null);
    }
}
