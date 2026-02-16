package com.levelzero.item.potion;

import com.levelzero.creature.LivingCreature;

// Healing effect strategy implementation
public class HealingEffect implements PotionEffect {
    
    private final int healAmount;
    
    public HealingEffect(int healAmount) {
        if (healAmount < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }
        this.healAmount = healAmount;
    }
    
    @Override
    public void apply(LivingCreature target) {
        // TODO  Effect will be implemented when LivingCreature is implemented. 
    }
    
    @Override
    public String getDescription() {
        return String.format("Restores %d HP", healAmount);
    }
    
    public int getHealAmount() {
        return healAmount;
    }
}
