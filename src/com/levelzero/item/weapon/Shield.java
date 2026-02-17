package com.levelzero.item.weapon;

import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;
import com.levelzero.creature.defense.SimpleDefenseStrategy;

// Represents a shield providing defensive protection
public class Shield extends Weapon {
    
    // The defence value provided by the shield
    private int defence;
    
    // Shield constructor with strategies
    public Shield(String name, int defence, int price, AttackStrategy attackStrategy, DefenseStrategy defenseStrategy) {
        super(name, "Shield", price, attackStrategy, defenseStrategy != null ? defenseStrategy : new SimpleDefenseStrategy());
        
        if (defence < 0) {
            throw new IllegalArgumentException("Defence cannot be negative");
        }
        
        this.defence = defence;
    }
    
    // Shields deal no damage
    @Override
    public int getDamage() {
        return 0;
    }
    
    // Returns the defence provided by the shield
    @Override
    public int getDefense() {
        return defence;
    }
    
    // Returns the shield statistics
    @Override
    public String getStats() {
        return String.format("%s (Shield) - Defence: %d, Price: %d", name, defence, price);
    }
}
