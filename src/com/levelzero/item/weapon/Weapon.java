package com.levelzero.item.weapon;

import com.levelzero.item.AbstractItem;
import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;

/**
 * Abstract base class for all weapons in the game. 
 * It defines common properties and methods for weapons, such as type, attack and defense strategies.
 * Concrete weapon types (Shield, Sword, Staff) will extend this class and implement certain behavior.
 */
public abstract class Weapon extends AbstractItem {
    
    private final String type;
    
    protected AttackStrategy attackStrategy;
    protected DefenseStrategy defenseStrategy;
    
    /**
     * Constructor for creating a weapon with the specified name, type, price, attack strategy and defense strategy.
     * @param name the name of the weapon
     * @param type the type of the weapon ("Shield", "Sword", "Staff")
     * @param price the price of the weapon
     * @param attackStrategy the attack strategy of the weapon
     * @param defenseStrategy the defense strategy of the weapon
     */
    public Weapon(String name, String type, int price, AttackStrategy attackStrategy, DefenseStrategy defenseStrategy) {
        super(name, price);
        
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Weapon type cannot be empty");
        }
        
        this.type = type;
        this.attackStrategy = attackStrategy;
        this.defenseStrategy = defenseStrategy;
    }
    
    public abstract int getDamage();
    
    public abstract int getDefense();
    
    public String getType() {
        return type;
    }
    
    public String getStats() {
        return String.format("Weapon: %s, Type: %s, Price: %d, Damage: %d, Defence: %d",
                name, type, price, getDamage(), getDefense());
    }
    
    @Override
    public String getDescription() {
        return getStats();
    }
    
    @Override
    public String toString() {
        return getStats();
    }
    
    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }
    
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
    
    public DefenseStrategy getDefenseStrategy() {
        return defenseStrategy;
    }
    
    public void setDefenseStrategy(DefenseStrategy defenseStrategy) {
        this.defenseStrategy = defenseStrategy;
    }
}

