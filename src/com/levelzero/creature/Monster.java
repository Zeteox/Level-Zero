package com.levelzero.creature;

import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;
import com.levelzero.creature.heal.HealStrategy;
import com.levelzero.item.weapon.Shield;
import com.levelzero.item.weapon.Sword;

public class Monster extends LivingCreature {

    private final Sword sword;
    private final Shield shield;

    public Monster(String name, int maxHp, int gold, int baseDamage, int baseDefense, AttackStrategy attackStrategy, HealStrategy healStrategy, DefenseStrategy defenseStrategy, Sword sword, Shield shield) {
        super(name, maxHp, gold, baseDamage, baseDefense, attackStrategy, healStrategy, defenseStrategy);

        this.sword = sword;
        this.shield = shield;
    }

    @Override
    public int getDamage() {
        return baseDamage + (sword != null ? sword.getDamage() : 0);
    }

    @Override
    public int getDefense() {
        return baseDefense + (shield != null ? shield.getDefense() : 0);
    }
}
