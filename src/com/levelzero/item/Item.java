package com.levelzero.item;

/**
 * An interface representing an item in the game. 
 * This can be implemented by various types of items, such as weapons, armor and potions.
 */
public interface Item {
    
    String getName();
    
    int getPrice();
    
    String getDescription();
}
