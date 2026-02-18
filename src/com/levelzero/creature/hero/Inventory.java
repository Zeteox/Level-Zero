package com.levelzero.creature.hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Optional;
import com.levelzero.item.Item;
import com.levelzero.item.potion.Potion;
import com.levelzero.item.weapon.Weapon;

/**
 * Represents the inventory of a hero, which can hold items such as potions and weapons.
 * The inventory has a maximum capacity and provides methods to add, remove and retrieve items.
 */
public class Inventory implements Iterable<Item> {
    
    private final int capacity;
    private final ArrayList<Item> items;
    
    /**
     * Constructs a new Inventory with a certain capacity.
     * @param capacity the maximum number of items the inventory can hold
     */
    public Inventory(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }
    
    /**
     * Adds an item to the inventory if there is space available.
     * @param item the item to add
     * @return a boolan
     */
    public boolean addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        
        if (items.size() >= capacity) {
            return false;
        }
        
        items.add(item);
        return true;
    }
    
    public boolean addPotion(Potion potion) {
        return addItem(potion);
    }
    
    public boolean addWeapon(Weapon weapon) {
        return addItem(weapon);
    }
    
    /**
     * Removes an item from the inventory.
     * @param item the item to remove
     * @return a boolean
     */
    public boolean removeItem(Item item) {
        if (item == null) {
            return false;
        }
        return items.remove(item);
    }
    
    public boolean removePotion(Potion potion) {
        return removeItem(potion);
    }
    
    public boolean removeWeapon(Weapon weapon) {
        return removeItem(weapon);
    }
    
    public Optional<Item> getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return Optional.empty();
        }
        return Optional.of(items.get(index));
    }
    
    public Optional<Item> getItemByName(String name) {
        if (name == null) {
            return Optional.empty();
        }
        return items.stream()
            .filter(item -> item.getName().equalsIgnoreCase(name))
            .findFirst();
    }
    
    /**
     * Finds items in the inventory that match a given predicate.
     * @param predicate the condition to match items against
     * @return
     */
    public List<Item> findItems(java.util.function.Predicate<Item> predicate) {
        return items.stream()
            .filter(predicate)
            .toList();
    }
    
    public int getCurrentSize() {
        return items.size();
    }
    
    public int getAvailableSpace() {
        return capacity - items.size();
    }
    
    public List<Potion> getPotions() {
        List<Potion> potions = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Potion) {
                potions.add((Potion) item);
            }
        }
        return Collections.unmodifiableList(potions);
    }
    
    public List<Weapon> getWeapons() {
        List<Weapon> weapons = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Weapon) {
                weapons.add((Weapon) item);
            }
        }
        return Collections.unmodifiableList(weapons);
    }
    
    public List<Item> getAllItems() {
        return Collections.unmodifiableList(items);
    }
    
    public int getCapacity() {
        return capacity;
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }
    
    @Override
    public Iterator<Item> iterator() {
        return Collections.unmodifiableList(items).iterator();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("INVENTORY (").append(getCurrentSize()).append("/").append(capacity).append(")\n");
        
        if (items.isEmpty()) {
            sb.append("Empty inventory\n");
        } else {
            int index = 0;
            for (Item item : items) {
                sb.append(index).append(". ").append(item.getDescription()).append(" - Price: ").append(item.getPrice()).append("\n");
                index++;
            }
        }
        
        return sb.toString();
    }
}
