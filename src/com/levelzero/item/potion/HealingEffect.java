package com.levelzero.item.potion;

import com.levelzero.creature.LivingCreature;

/**
 * A implementation of PotionEffect that heals the target creature by a specified amount.
 */
public class HealingEffect implements PotionEffect {
    
    private final int healAmount;
    
    /**
     * Constructs a new HealingEffect with the specified heal amount.
     * @param healAmount the amount of HP to heal
     */
    public HealingEffect(int healAmount) {
        if (healAmount < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }
        this.healAmount = healAmount;
    }
    
    @Override
    public void apply(LivingCreature target) {
        target.healHp(healAmount);
    }
    
    @Override
    public String getDescription() {
        return String.format("Restores %d HP", healAmount);
    }
    
    public int getHealAmount() {
        return healAmount;
    }
}
