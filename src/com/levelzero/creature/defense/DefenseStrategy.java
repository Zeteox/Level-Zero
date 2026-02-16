package com.levelzero.creature.defense;

import com.levelzero.creature.LivingCreature;

public interface DefenseStrategy {
    int defend(int damage, LivingCreature target);
}
