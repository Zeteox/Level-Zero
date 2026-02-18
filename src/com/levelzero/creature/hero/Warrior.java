package com.levelzero.creature.hero;

import com.levelzero.creature.attack.SimpleAttackStrategy;
import com.levelzero.creature.defense.SimpleDefenseStrategy;
import com.levelzero.creature.heal.SimpleHealStrategy;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.item.weapon.Sword;

public class Warrior extends Hero implements HaveBonus {
    private final int damageBoost;

    public Warrior(String name, int maxHp, int gold, int damage, int defense, int space, int damageBoost) {
        super(name, maxHp, gold, damage, defense,
                new SimpleAttackStrategy(), new SimpleHealStrategy(), new SimpleDefenseStrategy(),
                space);
        this.damageBoost = damageBoost;
    }

    @Override
    public int getBonus() {
        return damageBoost;
    }

    @Override
    public int getDamage() {
        return super.getDamage() + damageBoost;
    }

    /**
     * Overrides the equip method to ensure that Warriors can only equip Swords.
     * If a non-Sword weapon is attempted to be equipped, a message is returned indicating the failure.
     * Otherwise, it calls the superclass's equip method to handle the equipping process.
     * @param weapon the weapon to equip
     * @return a boolean
     */
    @Override
    public boolean equip(Weapon weapon) {
        if (!(weapon instanceof Sword)) {
            return false;
        }
        return super.equip(weapon);
    }
}
