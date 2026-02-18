package com.levelzero.item.potion;

/**
 * A factory class for creating different types of potions in the game. 
 * This class provides methods to create small, medium and large health potions,
 */
public class PotionFactory {
    
    /**
     * Creates a small health potion that heal 25 health points and costs 10 gold.
     * @return a new small health potion
     */
    public static Potion createSmallPotion() {
        return new PotionBuilder()
            .withName("Small Health Potion")
            .healing(25)
            .withPrice(10)
            .build();
    }
    
    /**
     * Creates a medium health potion that heal 50 health points and costs 25 gold.
     * @return a new medium health potion
     */
    public static Potion createMediumPotion() {
        return new PotionBuilder()
            .withName("Medium Health Potion")
            .healing(50)
            .withPrice(25)
            .build();
    }
    
    /**
     * Creates a large health potion that heal 100 health points and costs 50 gold.
     * @return a new large health potion
     */
    public static Potion createLargePotion() {
        return new PotionBuilder()
            .withName("Large Health Potion")
            .healing(100)
            .withPrice(50)
            .build();
    }
    
    public static PotionBuilder custom() {
        return new PotionBuilder();
    }
}
