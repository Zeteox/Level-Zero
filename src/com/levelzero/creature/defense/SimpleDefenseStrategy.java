package com.levelzero.creature.defense;

import com.levelzero.creature.LivingCreature;

public class SimpleDefenseStrategy implements DefenseStrategy {

    @Override
    public int defend(int damage, LivingCreature target) {
        if (damage <= target.getDefense()) {
            return 0;
        }
        return damage - target.getDefense();
    }
}
