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

    @Override
    public String getStats() {
        String weaponName = (getMainHand() != null) ? getMainHand().getName() : "None";

        return "Name: " + getName() +
                "\nClass: Warrior" +
                "\nHP: " + getHp() + "/" + getMaxHp() +
                "\nGold: " + getGold() +
                "\nDamage: " + getDamage() +
                "\nDefense: " + getDefense() +
                "\nDamage Boost:  +" + this.damageBoost +
                "\nMain Hand: " + weaponName;
    }

    @Override
    public void equip(Weapon weapon) {
        if (!(weapon instanceof Sword)) {
            throw new IllegalArgumentException("Warriors can only equip Swords.");
        }
        super.equip(weapon);
    }
}
