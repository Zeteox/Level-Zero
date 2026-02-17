package com.levelzero.creature.heal;

import com.levelzero.creature.LivingCreature;

public interface HealStrategy {
    void healHp(LivingCreature target, int heal);
}
