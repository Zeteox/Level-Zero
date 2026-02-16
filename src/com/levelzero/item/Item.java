package com.levelzero.item;

// Defines the contract for all items in the game
public interface Item {
    
    // Returns the item name
    String getName();
    
    // Returns the item price
    int getPrice();
    
    // Returns a description of the item
    String getDescription();
}
