package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;
import com.levelzero.creature.hero.HaveDodgeChance;



public class MagicAttackStrategy extends BaseAttackStrategy {

    @Override
    public void applyEffect(LivingCreature attacker, LivingCreature target) {
        target.removeMagicHp(attacker.getDamage());
    }
}
