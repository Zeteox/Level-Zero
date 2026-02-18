package com.levelzero.creature.attack;

import com.levelzero.creature.LivingCreature;
import com.levelzero.creature.hero.CanDodge;

public class MagicAttackStrategy implements AttackStrategy {

    @Override
    public String attack(LivingCreature attacker, LivingCreature target) {
        if (!attacker.isAlive()) {
            return attacker.getName() + " cannot attack because it is defeated.";
        }
        if (!target.isAlive()) {
            return target.getName() + " is already defeated.";
        }
        if (target instanceof CanDodge) {
            int dodgeNumber = (int) (Math.random() * 100) + 1;
            if (dodgeNumber <= ((CanDodge) target).getDodgeChance()) {
                return target.getName() + " dodged the attack!";
            }
        }
        target.removeMagicHp(attacker.getDamage());
        return attacker.getName() + " attacked " + target.getName() + " for " + (attacker.getDamage()) + " damage.";
    }
}
