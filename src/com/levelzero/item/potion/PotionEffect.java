package com.levelzero.item.potion;

import com.levelzero.creature.LivingCreature;

// Defines potion effect 
public interface PotionEffect {
    
    // Applies the effect to a target
    void apply(LivingCreature target);
    
    // Returns effect description
    String getDescription();
}
