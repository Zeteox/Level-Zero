package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;


public class MagicAttackStrategy extends BaseAttackStrategy {

    /**
     * Applies magic damage to the target.
     * Magic damage bypasses the target's defense completely.
     * @param attacker the creature performing the attack
     * @param target the creature being attacked
     */
    @Override
    public void applyEffect(LivingCreature attacker, LivingCreature target) {
        target.removeMagicHp(attacker.getDamage());
    }
}
