package com.levelzero.item.potion;

import com.levelzero.item.AbstractItem;
import com.levelzero.creature.LivingCreature;

// Represents a consumable potion with effect strategy
public class Potion extends AbstractItem {
    
    private final PotionEffect effect;
    
    // Constructor with effect strategy
    public Potion(String name, PotionEffect effect, int price) {
        super(name, price);
        
        if (effect == null) {
            throw new IllegalArgumentException("Effect cannot be null");
        }
        
        this.effect = effect;
    }
    
    // Apply potion effect to target
    public void use(LivingCreature target) {
        if (target != null) {
            effect.apply(target);
        }
    }
    
    @Override
    public String getDescription() {
        return effect.getDescription();
    }
    
    @Override
    public String toString() {
        return String.format("%s (Potion) - %s - Price: %d", name, effect.getDescription(), price);
    }
}
