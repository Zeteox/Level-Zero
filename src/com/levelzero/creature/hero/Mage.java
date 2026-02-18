package com.levelzero.creature.hero;

import com.levelzero.creature.attack.SimpleAttackStrategy;
import com.levelzero.creature.defense.SimpleDefenseStrategy;
import com.levelzero.creature.heal.SimpleHealStrategy;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.item.weapon.Staff;

public class Mage extends Hero implements HaveDodgeChance {
    private final int dodgeChance;

    public Mage(String name, int maxHp, int gold, int damage, int defense, int space, int dodgeChance) {
        super(name, maxHp, gold, damage, defense,
                new SimpleAttackStrategy(), new SimpleHealStrategy(), new SimpleDefenseStrategy(),
                space);
        this.dodgeChance = dodgeChance;
    }

    @Override
    public int getDodgeChance() {
        return dodgeChance;
    }

    @Override
    public int getDamage() {
        return (super.getDamage() + 10);
    }

    @Override
    public boolean equip(Weapon weapon) {
        if (!(weapon instanceof Staff)) {
            return false;
        }
        return super.equip(weapon);
    }
}
