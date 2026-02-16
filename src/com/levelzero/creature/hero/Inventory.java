package com.levelzero.creature.hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.levelzero.item.Potion;
import com.levelzero.item.weapon.Weapon;


// Manages player inventory with limited space for potions and weapons
public class Inventory {
    
    // Maximum number of items the inventory can hold
    private int space;
    // List of potions stored in the inventory
    private ArrayList<Potion> potions;
    // List of weapons stored in the inventory
    private ArrayList<Weapon> weapons;
    
    
    // Creates an inventory with specified capacity   
    public Inventory(int space) {
        if (space < 0) {
            throw new IllegalArgumentException("Space cannot be negative");
        }
        
        this.space = space;
        this.potions = new ArrayList<>();
        this.weapons = new ArrayList<>();
    }
    
    // Adds a potion to the inventory if space is available
    public String addPotion(Potion potion) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }
        
        if (getCurrentSize() >= space) {
            return "Inventory full. Cannot add " + potion.getName();
        }
        
        potions.add(potion);
        return potion.getName() + " added to inventory";
    }
    
    
    // Removes a potion from the inventory 
    public String removePotion(Potion potion) {
        if (potion == null) {
            return "Invalid potion";
        }
        
        if (potions.remove(potion)) {
            return potion.getName() + " removed from inventory";
        }
        
        return potion.getName() + " not found in inventory";
    }
    
    // Adds a weapon to the inventory if space is available
    public String addWeapon(Weapon weapon) {
        if (weapon == null) {
            throw new IllegalArgumentException("Weapon cannot be null");
        }
        
        if (getCurrentSize() >= space) {
            return "Inventory full. Cannot add " + weapon.getName();
        }
        
        weapons.add(weapon);
        return weapon.getName() + " added to inventory";
    }
    
    
    // Removes a weapon from the inventory 
    public String removeWeapon(Weapon weapon) {
        if (weapon == null) {
            return "Invalid weapon";
        }
        
        if (weapons.remove(weapon)) {
            return weapon.getName() + " removed from inventory";
        }
        
        return weapon.getName() + " not found in inventory";
    }
    
    
    // Returns the current number of items in the inventory
    public int getCurrentSize() {
        return potions.size() + weapons.size();
    }
    
    
    // Returns the number of available slots in the inventory 
    public int getAvailableSpace() {
        return space - getCurrentSize();
    }
    
    // Returns an immutable list of all potions in the inventory
    public List<Potion> getPotions() {
        return Collections.unmodifiableList(potions);
    }
    
    
    // Returns an immutable list of all weapons in the inventory
    public List<Weapon> getWeapons() {
        return Collections.unmodifiableList(weapons);
    }
    
    
    // Returns the maximum capacity of the inventory
    public int getSpace() {
        return space;
    }
    
    
    // Returns a formatted string representation of the inventory contents
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("INVENTORY (").append(getCurrentSize()).append("/").append(space).append(")\n");
        
        if (weapons.isEmpty() && potions.isEmpty()) {
            sb.append("Empty inventory\n");
        } else {
            if (!weapons.isEmpty()) {
                sb.append("WEAPONS:\n");
                for (Weapon weapon : weapons) {
                    sb.append("  - ").append(weapon.getStats()).append("\n");
                }
            }
            
            if (!potions.isEmpty()) {
                sb.append("POTIONS:\n");
                for (Potion potion : potions) {
                    sb.append("  - ").append(potion.toString()).append("\n");
                }
            }
        }
        
        return sb.toString();
    }
}
