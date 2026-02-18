package com.levelzero.creature.heal;

import com.levelzero.creature.LivingCreature;

public class SimpleHealStrategy implements HealStrategy {

    /**
     * Heals the target creature by the specified amount.
     * The creature's HP will not exceed its maximum HP.
     * @param target the creature to heal
     * @param heal the amount of HP to restore
     */
    @Override
    public void healHp(LivingCreature target, int heal) {
        int newHp = target.getHp() + heal;
        if (newHp > target.getMaxHp()) {
            newHp = target.getMaxHp();
        }
        target.setHp(newHp);
    }
}
