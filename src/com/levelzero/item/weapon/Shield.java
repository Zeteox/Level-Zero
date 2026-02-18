package com.levelzero.item.weapon;

import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;
import com.levelzero.creature.defense.SimpleDefenseStrategy;

/**
 * Represents a shield weapon that provides defense but does not deal damage.
 * The shield can have a defense value and may use a defense strategy to enhance its defensive capabilities.
 */
public class Shield extends Weapon {
    
    private int defence;
    
    /**
     * Creates a new Shield with the specified name, defense value, price, attack strategy, and defense strategy.
     * @param name the name of the shield
     * @param defence the defense value of the shield
     * @param price the price of the shield
     * @param attackStrategy the attack strategy of the shield
     * @param defenseStrategy the defense strategy of the shield
     */
    public Shield(String name, int defence, int price, AttackStrategy attackStrategy, DefenseStrategy defenseStrategy) {
        super(name, "Shield", price, attackStrategy, defenseStrategy != null ? defenseStrategy : new SimpleDefenseStrategy());
        
        if (defence < 0) {
            throw new IllegalArgumentException("Defence cannot be negative");
        }
        
        this.defence = defence;
    }
    
    @Override
    public int getDamage() {
        return 0;
    }
    
    @Override
    public int getDefense() {
        return defence;
    }
    
    @Override
    public String getStats() {
        return String.format("%s (Shield) - Defence: %d, Price: %d", name, defence, price);
    }
}
