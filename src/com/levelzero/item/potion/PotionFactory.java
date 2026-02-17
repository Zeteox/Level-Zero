package com.levelzero.item.potion;

// Factory pattern for predefined potion creation
public class PotionFactory {
    
    public static Potion createSmallPotion() {
        return new PotionBuilder()
            .withName("Small Health Potion")
            .healing(25)
            .withPrice(10)
            .build();
    }
    
    public static Potion createMediumPotion() {
        return new PotionBuilder()
            .withName("Medium Health Potion")
            .healing(50)
            .withPrice(25)
            .build();
    }
    
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
