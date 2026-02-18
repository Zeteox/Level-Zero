package com.levelzero.item.weapon;

import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.attack.SimpleAttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;

/**
 * A class representing a sword weapon in the game. 
 * Swords are physical weapons that deal physical damage and provide no defense.
 */
public class Sword extends Weapon {
    
    private int damage;
    
   /**
    * Constructs a new Sword with the specified name, damage, price, attack strategy and defense strategy. 
    * If the strategy is null the default is set to SimpleAttackStrategy.
    * @param name the name of the sword.
    * @param damage the damage of the sword. 
    * @param price the price of the sword.
    * @param attackStrategy the attack strategy of the sword.
    * @param defenseStrategy the defense strategy of the sword.
    */
    public Sword(String name, int damage, int price, AttackStrategy attackStrategy, DefenseStrategy defenseStrategy) {
        super(name, "Sword", price, attackStrategy != null ? attackStrategy : new SimpleAttackStrategy(), defenseStrategy);
        
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
        return String.format("%s (Sword) - Damage: %d, Price: %d", name, damage, price);
    }
}
