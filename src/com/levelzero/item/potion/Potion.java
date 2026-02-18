package com.levelzero.item.potion;

import com.levelzero.item.AbstractItem;
import com.levelzero.creature.LivingCreature;

/**
 * Represents a potion item that can be used by the player.
 * A potion has a name, an effect and a price.
 * The effect is defined by the PotionEffect interface, allowing for different types of potions for scalability.
 */
public class Potion extends AbstractItem {
    
    private final PotionEffect effect;
    
    /**
     * Constructs a new Potion with the specified name, effect and price.
     * @param name the name of the potion
     * @param effect the effect of the potion 
     * @param price the price of the potion
     */
    public Potion(String name, PotionEffect effect, int price) {
        super(name, price);
        
        if (effect == null) {
            throw new IllegalArgumentException("Effect cannot be null");
        }
        
        this.effect = effect;
    }
    
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
