package com.levelzero.item.weapon;

import com.levelzero.item.AbstractItem;
import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;

// Abstract class representing a weapon in the game
public abstract class Weapon extends AbstractItem {
    
    // Weapon type (e.g., "Sword", "Shield", "Staff")
    private final String type;
    
    // Attack and Defense strategies
    protected AttackStrategy attackStrategy;
    protected DefenseStrategy defenseStrategy;
    
    // Weapon constructor with parameter validation
    public Weapon(String name, String type, int price) {
        super(name, price);
        
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Weapon type cannot be empty");
        }
        
        this.type = type;
        this.attackStrategy = null;
        this.defenseStrategy = null;
    }
    
    // Weapon constructor with strategies
    public Weapon(String name, String type, int price, AttackStrategy attackStrategy, DefenseStrategy defenseStrategy) {
        this(name, type, price);
        this.attackStrategy = attackStrategy;
        this.defenseStrategy = defenseStrategy;
    }
    
    // Returns the weapon damage
    public abstract int getDamage();
    
    // Returns the weapon defence
    public abstract int getDefense();
    
    // Returns the weapon type
    public String getType() {
        return type;
    }
    
    // Returns the weapon statistics as a formatted string
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
    
    // Attack Strategy getters and setters
    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }
    
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
    
    // Defense Strategy getters and setters
    public DefenseStrategy getDefenseStrategy() {
        return defenseStrategy;
    }
    
    public void setDefenseStrategy(DefenseStrategy defenseStrategy) {
        this.defenseStrategy = defenseStrategy;
    }
}

