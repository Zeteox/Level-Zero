package com.levelzero.creature.defense;

import com.levelzero.creature.LivingCreature;

public class SimpleDefenseStrategy implements DefenseStrategy {

    /**
     * Calculates damage after subtracting the target's defense value.
     * @param damage the incoming damage amount
     * @param target the creature defending
     * @return the actual damage taken (0 if defense blocks all damage)
     */
    @Override
    public int defend(int damage, LivingCreature target) {
        if (damage <= target.getDefense()) {
            return 0;
        }
        return damage - target.getDefense();
    }
}
