package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;

public class SimpleAttackStrategy implements AttackStrategy {
    @Override
    public void attack(LivingCreature attacker, LivingCreature target) {
        target.removeHp(attacker.getDamage());
    }
}
