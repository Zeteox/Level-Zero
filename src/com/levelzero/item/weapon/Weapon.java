package com.levelzero.item.weapon;

// Abstract class representing a weapon in the game
public abstract class Weapon {
    
    // Basic weapon attributes
    protected String name;
    protected String type;
    protected int price;
    
    // Weapon constructor with parameter validation
    public Weapon(String name, String type, int price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Weapon name cannot be empty");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Weapon type cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        
        this.name = name;
        this.type = type;
        this.price = price;
    }
    
    // Returns the weapon statistics as a formatted string
    public String getStats() {
        return String.format("Weapon: %s, Type: %s, Price: %d, Damage: %d, Defence: %d",
                name, type, price, getDamage(), getDefense());
    }
    
    // Returns the weapon damage
    public abstract int getDamage();
    
    // Returns the weapon defence
    public abstract int getDefense();
    
    // Returns the weapon name
    public String getName() {
        return name;
    }
    
    // Returns the weapon type
    public String getType() {
        return type;
    }
    
    // Returns the weapon price
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return getStats();
    }
}
