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

    /**
     * Overrides the equip method to ensure that Paladins can only equip Swords and Shields.
     * If an invalid weapon type is attempted to be equipped, a message is returned indicating the failure.
     * @param weapon the weapon to equip
     * @return a boolean
     */
    @Override
    public boolean equip(Weapon weapon) {
        if (!(weapon instanceof Shield) && !(weapon instanceof Sword)) {
            return false;
        }
        return super.equip(weapon);
    }
}
