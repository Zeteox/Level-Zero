package com.levelzero.item.weapon;

import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.attack.MagicAttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;

/**
* A class representing a staff weapon in the game. 
* Staves are magical weapons that deal magic damage and provide no defense.
*/
public class Staff extends Weapon {
    
    private int damage;

    /**
     * Constructs a new Staff with the specified name, damage, price, attack strategy and defense strategy. 
     * If the strategy is null the default is set to MagicAttackStrategy. 
     * @param name the name of the Staff.
     * @param damage the damage of the Staff.
     * @param price the price of the Staff.
     * @param attackStrategy the attack strategy of the Staff.
     * @param defenseStrategy the defense strategy of the Staff.
     */
    public Staff(String name, int damage, int price, AttackStrategy attackStrategy, DefenseStrategy defenseStrategy) {
        super(name, "Staff", price, attackStrategy != null ? attackStrategy : new MagicAttackStrategy(), defenseStrategy);
        
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        
        this.damage = damage;
    }
    
    @Override
    public int getDamage() {
        return damage;
    }
    
    @Override
    public int getDefense() {
        return 0;
    }
    
    @Override
    public String getStats() {
        return String.format("%s (Staff) - Magic Damage: %d, Price: %d", name, damage, price);
    }
}
