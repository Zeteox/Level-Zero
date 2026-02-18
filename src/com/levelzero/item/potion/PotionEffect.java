package com.levelzero.item.potion;

import com.levelzero.creature.LivingCreature;

public interface PotionEffect {
    
    void apply(LivingCreature target);
    
    String getDescription();
}
