package com.levelzero.item.weapon;

import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.attack.SimpleAttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;

// Represents a sword, an offensive weapon dealing physical damage
public class Sword extends Weapon {
    
    // The physical damage dealt by the sword
    private int damage;
    
    // Sword constructor with strategies
    public Sword(String name, int damage, int price, AttackStrategy attackStrategy, DefenseStrategy defenseStrategy) {
        super(name, "Sword", price, attackStrategy != null ? attackStrategy : new SimpleAttackStrategy(), defenseStrategy);
        
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        
        this.damage = damage;
    }
    
    // Returns the sword damage
    @Override
    public int getDamage() {
        return damage;
    }
    
    // Swords provide no defence
    @Override
    public int getDefense() {
        return 0;
    }
    
    // Returns the sword statistics
    @Override
    public String getStats() {
        return String.format("%s (Sword) - Damage: %d, Price: %d", name, damage, price);
    }
}
