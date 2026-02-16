package com.levelzero.item;

// Base class for all items, providing common functionality
public abstract class AbstractItem implements Item {
    
    protected final String name;
    protected final int price;
    
    // Constructor with parameter validation
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
    
    // Returns the item name
    @Override
    public String getName() {
        return name;
    }
    
    // Returns the item price
    @Override
    public int getPrice() {
        return price;
    }
    
    // Returns a description of the item
    @Override
    public abstract String getDescription();
}
