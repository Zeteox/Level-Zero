package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;
import com.levelzero.creature.hero.HaveDodgeChance;

public class SimpleAttackStrategy extends BaseAttackStrategy {
    @Override
    public void applyEffect(LivingCreature attacker, LivingCreature target) {
        target.removeHp(attacker.getDamage());
    }
}