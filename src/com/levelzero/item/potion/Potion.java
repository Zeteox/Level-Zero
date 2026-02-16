package com.levelzero.item.potion;

import com.levelzero.item.AbstractItem;

// Represents a healing potion
public class Potion extends AbstractItem {
    
    private final int hp; // HP restored
    
    // Constructor with parameter validation
    public Potion(String name, int hp, int price) {
        super(name, price);
        
        if (hp < 0) {
            throw new IllegalArgumentException("HP cannot be negative");
        }
        
        this.hp = hp;
    }
    
    // Returns the HP restored
    public int getHp() {
        return hp;
    }
    
    @Override
    public String getDescription() {
        return String.format("Restores %d HP", hp);
    }
    
    // String representation of the potion
    @Override
    public String toString() {
        return String.format("%s (Potion) - Restores: %d HP, Price: %d", name, hp, price);
    }
}
