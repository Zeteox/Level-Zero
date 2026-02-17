package com.levelzero.creature.heal;

import com.levelzero.creature.LivingCreature;

public class SimpleHealStrategy implements HealStrategy {

    @Override
    public void healHp(LivingCreature target, int heal) {
        int newHp = target.getHp() + heal;
        if (newHp > target.getMaxHp()) {
            newHp = target.getMaxHp();
        }
        target.setHp(newHp);
    }
}
