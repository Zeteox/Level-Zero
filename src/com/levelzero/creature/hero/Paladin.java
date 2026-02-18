package com.levelzero.creature.hero;

import com.levelzero.creature.attack.SimpleAttackStrategy;
import com.levelzero.creature.defense.SimpleDefenseStrategy;
import com.levelzero.creature.heal.SimpleHealStrategy;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.item.weapon.Shield;
import com.levelzero.item.weapon.Sword;

public class Paladin extends Hero {

    public Paladin(String name, int maxHp, int gold, int damage, int defense, int space) {
        super(name, maxHp, gold, damage, defense,
                new SimpleAttackStrategy(), new SimpleHealStrategy(), new SimpleDefenseStrategy(),
                space);
    }

    @Override
    public boolean equip(Weapon weapon) {
        if (!(weapon instanceof Shield) && !(weapon instanceof Sword)) {
            return false;
        }
        return super.equip(weapon);
    }
}
