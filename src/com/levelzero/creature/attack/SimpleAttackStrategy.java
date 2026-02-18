package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;

public class SimpleAttackStrategy extends BaseAttackStrategy {
    /**
     * Applies physical damage to the target.
     * The target's defense is considered in damage calculation.
     * @param attacker the creature performing the attack
     * @param target the creature being attacked
     */
    @Override
    public void applyEffect(LivingCreature attacker, LivingCreature target) {
        target.removeHp(attacker.getDamage());
    }
}