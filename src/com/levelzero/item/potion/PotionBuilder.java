package com.levelzero.item.potion;

// Builder pattern for fluent Potion construction
public class PotionBuilder {
    
    private String name;
    private PotionEffect effect;
    private int price;
    
    public PotionBuilder withName(String name) {
        this.name = name;
        return this;
    }
    
    public PotionBuilder withEffect(PotionEffect effect) {
        this.effect = effect;
        return this;
    }
    
    public PotionBuilder withPrice(int price) {
        this.price = price;
        return this;
    }
    
    public PotionBuilder healing(int healAmount) {
        this.effect = new HealingEffect(healAmount);
        return this;
    }
    
    public Potion build() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Potion name is required");
        }
        if (effect == null) {
            throw new IllegalStateException("Potion effect is required");
        }
        if (price < 0) {
            throw new IllegalStateException("Price cannot be negative");
        }
        return new Potion(name, effect, price);
    }
}
