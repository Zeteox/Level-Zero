package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;

public interface AttackStrategy {
    String attack(LivingCreature attacker, LivingCreature target);
}
