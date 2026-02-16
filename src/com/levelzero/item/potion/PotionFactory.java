package com.levelzero.item.potion;

public class PotionFactory {

    public enum PotionSize {
        SMALL,
        MEDIUM,
        LARGE
    }   
    
    // Small potion configuration
    private static final int SMALL_HP_AMOUNT = 25;
    private static final int SMALL_PRICE = 10;
    private static final String SMALL_NAME = "Small Health Potion";
    
    // Medium potion configuration
    private static final int MEDIUM_HP_AMOUNT = 50;
    private static final int MEDIUM_PRICE = 25;
    private static final String MEDIUM_NAME = "Medium Health Potion";
    
    // Large potion configuration
    private static final int LARGE_HP_AMOUNT = 100;
    private static final int LARGE_PRICE = 50;
    private static final String LARGE_NAME = "Large Health Potion";
    
    public static Potion createPotion(PotionSize size) {
        if (size == null) {
            throw new IllegalArgumentException("Potion size cannot be null");
        }
        
        switch (size) {
            case SMALL:
                return createSmallPotion();
            case MEDIUM:
                return createMediumPotion();
            case LARGE:
                return createLargePotion();
            default:
                throw new IllegalArgumentException("Unknown potion size: " + size);
        }
    }
    
    // Creates a small potion (25 HP, 10 gold)
    public static Potion createSmallPotion() {
        return new Potion(SMALL_NAME, SMALL_HP_AMOUNT, SMALL_PRICE);
    }
    
    // Creates a medium potion (50 HP, 25 gold)
    public static Potion createMediumPotion() {
        return new Potion(MEDIUM_NAME, MEDIUM_HP_AMOUNT, MEDIUM_PRICE);
    }
    
    // Creates a large potion (100 HP, 50 gold)
    public static Potion createLargePotion() {
        return new Potion(LARGE_NAME, LARGE_HP_AMOUNT, LARGE_PRICE);
    }
    
    // Creates a custom unique potion
    public static Potion createCustomPotion(String name, int hp, int price) {
        return new Potion(name, hp, price);
    }
}
