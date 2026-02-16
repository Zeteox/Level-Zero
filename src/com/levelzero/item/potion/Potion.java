package com.levelzero.item.potion;

// Represents a healing potion
public class Potion {
    
    private String name; // Potion name
    private int hp; // HP restored
    private int price; // Purchase price
    
    // Constructor with parameter validation
    public Potion(String name, int hp, int price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Potion name cannot be empty");
        }
        if (hp < 0) {
            throw new IllegalArgumentException("HP cannot be negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        
        this.name = name;
        this.hp = hp;
        this.price = price;
    }
    
    // Returns the potion name
    public String getName() {
        return name;
    }
    
    // Returns the HP restored
    public int getHp() {
        return hp;
    }
    
    // Returns the potion price
    public int getPrice() {
        return price;
    }
    
    // String representation of the potion
    @Override
    public String toString() {
        return String.format("%s (Potion) - Restores: %d HP, Price: %d", name, hp, price);
    }
}
