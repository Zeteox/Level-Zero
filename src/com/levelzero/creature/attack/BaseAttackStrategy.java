package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;
import com.levelzero.creature.hero.HaveDodgeChance;

public abstract class BaseAttackStrategy implements AttackStrategy {
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
    protected abstract void applyEffect(LivingCreature attacker, LivingCreature target);
}
