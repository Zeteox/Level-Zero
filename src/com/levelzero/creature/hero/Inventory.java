package com.levelzero.creature.hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.levelzero.item.Item;
import com.levelzero.item.potion.Potion;
import com.levelzero.item.weapon.Weapon;

// Manages player inventory with limited space for potions and weapons
public class Inventory {
    
    // Maximum number of items the inventory can hold
    private final int capacity;
    // List of items stored in the inventory
    private final ArrayList<Item> items;
    
    // Creates an inventory with specified capacity   
    public Inventory(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }
    
    // Adds an item to the inventory if space is available
    public String addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        
        if (items.size() >= capacity) {
            return "Inventory full. Cannot add " + item.getName();
        }
        
        items.add(item);
        return item.getName() + " added to inventory";
    }
    
    // Adds a potion to the inventory if space is available
    public String addPotion(Potion potion) {
        return addItem(potion);
    }
    
    // Adds a weapon to the inventory if space is available
    public String addWeapon(Weapon weapon) {
        return addItem(weapon);
    }
    
    // Removes an item from the inventory 
    public String removeItem(Item item) {
        if (item == null) {
            return "Invalid item";
        }
        
        if (items.remove(item)) {
            return item.getName() + " removed from inventory";
        }
        
        return item.getName() + " not found in inventory";
    }
    
    // Removes a potion from the inventory 
    public String removePotion(Potion potion) {
        return removeItem(potion);
    }
    
    // Removes a weapon from the inventory 
    public String removeWeapon(Weapon weapon) {
        return removeItem(weapon);
    }
    
    // Returns the current number of items in the inventory
    public int getCurrentSize() {
        return items.size();
    }
    
    // Returns the number of available slots in the inventory 
    public int getAvailableSpace() {
        return capacity - items.size();
    }
    
    // Returns an immutable list of all potions in the inventory
    public List<Potion> getPotions() {
        List<Potion> potions = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Potion) {
                potions.add((Potion) item);
            }
        }
        return Collections.unmodifiableList(potions);
    }
    
    // Returns an immutable list of all weapons in the inventory
    public List<Weapon> getWeapons() {
        List<Weapon> weapons = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Weapon) {
                weapons.add((Weapon) item);
            }
        }
        return Collections.unmodifiableList(weapons);
    }
    
    // Returns an immutable list of all items in the inventory
    public List<Item> getAllItems() {
        return Collections.unmodifiableList(items);
    }
    
    // Returns the maximum capacity of the inventory
    public int getCapacity() {
        return capacity;
    }
    
    // Returns a formatted string representation of the inventory contents
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("INVENTORY (").append(getCurrentSize()).append("/").append(capacity).append(")\n");
        
        if (items.isEmpty()) {
            sb.append("Empty inventory\n");
        } else {
            for (Item item : items) {
                sb.append("  - ").append(item.getDescription()).append(" - Price: ").append(item.getPrice()).append("\n");
            }
        }
        
        return sb.toString();
    }
}
