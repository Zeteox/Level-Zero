package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;

public interface AttackStrategy {
    boolean attack(LivingCreature attacker, LivingCreature target);
}
