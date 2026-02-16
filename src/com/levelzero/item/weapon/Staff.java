package com.levelzero.item.weapon;

// Represents a magic staff dealing magic damage
public class Staff extends Weapon {
    
    // The magic damage dealt by the staff
    private int damage;
    
    // Staff constructor with damage validation
    public Staff(String name, int damage, int price) {
        super(name, "Staff", price);
        
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        
        this.damage = damage;
    }
    
    // Returns the staff magic damage
    @Override
    public int getDamage() {
        return damage;
    }
    
    // Staves provide no defence
    @Override
    public int getDefense() {
        return 0;
    }
    
    // Returns the staff statistics
    @Override
    public String getStats() {
        return String.format("%s (Staff) - Magic Damage: %d, Price: %d", name, damage, price);
    }
}
