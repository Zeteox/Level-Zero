package com.levelzero.item;

// Factory to create different sized potions in the game
public class PotionFactory {
    
    // Available potion sizes
    public enum PotionSize {
        SMALL,
        MEDIUM,
        LARGE
    }
    
    // Default values for each potion size
    private static final int SMALL_HEALTH_POTION = 25;
    private static final int SMALL_HEALTH_POTION_PRICE = 10;
    
    private static final int MEDIUM_HEALTH_POTION = 50;
    private static final int MEDIUM_HEALTH_POTION_PRICE = 25;
    
    private static final int LARGE_HEALTH_POTION = 100;
    private static final int LARGE_HEALTH_POTION_PRICE = 50;
    
    // Creates a potion based on the specified size
    public static Potion createPotion(PotionSize size) {
        if (size == null) {
            throw new IllegalArgumentException("Potion size cannot be null");
        }
        
        switch (size) {
            case SMALL:
                return new Potion("Small Health Potion", SMALL_HEALTH_POTION, SMALL_HEALTH_POTION_PRICE);
            case MEDIUM:
                return new Potion("Medium Health Potion", MEDIUM_HEALTH_POTION, MEDIUM_HEALTH_POTION_PRICE);
            case LARGE:
                return new Potion("Large Health Potion", LARGE_HEALTH_POTION, LARGE_HEALTH_POTION_PRICE);
            default:
                throw new IllegalArgumentException("Unknown potion size: " + size);
        }
    }
    
    // Creates a small potion (25 HP, 10 gold)
    public static Potion createSmallPotion() {
        return new Potion("Small Health Potion", SMALL_HEALTH_POTION, SMALL_HEALTH_POTION_PRICE);
    }
    
    // Creates a medium potion (50 HP, 25 gold)
    public static Potion createMediumPotion() {
        return new Potion("Medium Health Potion", MEDIUM_HEALTH_POTION, MEDIUM_HEALTH_POTION_PRICE);
    }
    
    // Creates a large potion (100 HP, 50 gold)
    public static Potion createLargePotion() {
        return new Potion("Large Health Potion", LARGE_HEALTH_POTION, LARGE_HEALTH_POTION_PRICE);
    }
    
    // Creates a custom potion with specified attributes
    public static Potion createCustomPotion(String name, int hp, int price) {
        return new Potion(name, hp, price);
    }
}
