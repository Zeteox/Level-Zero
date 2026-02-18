package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;
import com.levelzero.creature.hero.HaveDodgeChance;

public abstract class BaseAttackStrategy implements AttackStrategy {
    /**
     * Performs an attack from the attacker to the target.
     * Checks if both creatures are alive and handles dodge mechanics.
     * @param attacker the creature performing the attack
     * @param target the creature being attacked
     * @return true if the attack was successful, false if dodged or creatures are dead
     */
    @Override
    public boolean attack(LivingCreature attacker, LivingCreature target) {
        if (!attacker.isAlive() || !target.isAlive()) {
            return false;
        }

        if (target instanceof HaveDodgeChance) {
            int dodgeNumber = (int) (Math.random() * 100) + 1;
            return (dodgeNumber <= ((HaveDodgeChance) target).getDodgeChance());
        }
        applyEffect(attacker, target);
        return true;
    }

    /**
     * Applies the specific attack effect to the target.
     * This method must be implemented by subclasses to define the attack behavior.
     * @param attacker the creature performing the attack
     * @param target the creature being attacked
     */
    protected abstract void applyEffect(LivingCreature attacker, LivingCreature target);
}
