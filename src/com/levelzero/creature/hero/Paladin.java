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
    public int getBonus() {
        return 0;
    }

    @Override
    public String getStats() {
        String mainHandName = (getMainHand() != null) ? getMainHand().getName() : "None";
        String offHandName = (getOffHand() != null) ? getOffHand().getName() : "None";

        return "Name: " + getName() +
                "\nClass: Paladin" +
                "\nHP: " + getHp() + "/" + getMaxHp() +
                "\nGold: " + getGold() +
                "\nDamage: " + getDamage() +
                "\nDefense: " + getDefense() +
                "\nMain Hand: " + mainHandName +
                "\nOff Hand: " + offHandName;
    }

    @Override
    public String equip(Weapon weapon) {
        if (!(weapon instanceof Shield) && !(weapon instanceof Sword)) {
            return String.format("%s cannot equip %s. Paladins can only equip Swords and Shields.", getName(), weapon.getName());
        }
        return super.equip(weapon);
    }
}
