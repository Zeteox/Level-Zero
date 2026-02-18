package com.levelzero.item;

/**
 * An abstract base class for items in the game. 
 * This class provides common properties and methods for all items, such as name and price.
 */
public abstract class AbstractItem implements Item {
    
    protected final String name;
    protected final int price;
    
    /**
     * Creates a new AbstractItem with the specified name and price.
     * @param name the name of the item 
     * @param price the price of the item
     */
    public AbstractItem(String name, int price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Item price cannot be negative");
        }
        
        this.name = name;
        this.price = price;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getPrice() {
        return price;
    }
    
    @Override
    public abstract String getDescription();
}
