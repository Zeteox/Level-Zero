package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;

public interface AttackStrategy {
    void attack(LivingCreature attacker, LivingCreature target);
}
