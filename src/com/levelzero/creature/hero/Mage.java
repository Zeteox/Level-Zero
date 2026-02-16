package com.levelzero.creature.hero;

import com.levelzero.creature.attack.SimpleAttackStrategy;
import com.levelzero.creature.defense.SimpleDefenseStrategy;
import com.levelzero.creature.heal.SimpleHealStrategy;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.item.weapon.Staff;

public class Mage extends Hero implements CanDodge {
    private final int dodgeChance;

    public Mage(String name, int maxHp, int gold, int damage, int defense, int space, int dodgeChance) {
        super(name, maxHp, gold, damage, defense,
                new SimpleAttackStrategy(), new SimpleHealStrategy(), new SimpleDefenseStrategy(),
                space);
        this.dodgeChance = dodgeChance;
    }

    @Override
    public int getBonus() {
        return dodgeChance;
    }

    @Override
    public String getStats() {
        String staffName = (getMainHand() != null) ? getMainHand().getName() : "None";

        return "Name: " + getName() +
                "\nClass: Mage" +
                "\nHP: " + getHp() + "/" + getMaxHp() +
                "\nGold: " + getGold() +
                "\nMagic Damage: " + getDamage() +
                "\nDefense: " + getDefense() +
                "\nDodge Chance: " + this.dodgeChance + "%" +
                "\nMain Hand: " + staffName;
    }

    @Override
    public String equip(Weapon weapon) {
        if (!(weapon instanceof Staff)) {
            return String.format("%s cannot equip %s. Mages can only equip Staffs.", getName(), weapon.getName());
        }
        return super.equip(weapon);
    }
}
