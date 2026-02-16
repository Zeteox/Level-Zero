package com.levelzero.creature.hero;

import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;
import com.levelzero.creature.heal.HealStrategy;
import com.levelzero.creature.LivingCreature;
import com.levelzero.item.Potion;
import com.levelzero.item.weapon.Shield;
import com.levelzero.item.weapon.Weapon;

public abstract class Hero extends LivingCreature {
    protected Inventory inventory;
    protected Weapon mainHand;
    protected Weapon offHand;

    public Hero(String name, int maxHp, int gold, int damage, int defence, AttackStrategy attackStrategy, HealStrategy healStrategy, DefenseStrategy defenseStrategy, int space) {
        super(name, maxHp, gold, damage, defence, attackStrategy, healStrategy, defenseStrategy);
        this.inventory = new Inventory(space);
        this.mainHand = null;
        this.offHand = null;
    }

    public abstract int getBonus();

    public Inventory getInventory() {
        return inventory;
    }

    public Weapon getMainHand() {
        return mainHand;
    }

    public Weapon getOffHand() {
        return offHand;
    }

    public String equipOffHand(Weapon weapon) {
        String message;
        if (offHand == null) {
            message = String.format("%s has equipped %s in off-hand.", getName(), weapon.getName());
        } else {
            inventory.addWeapon(offHand);
            message = String.format("%s has unequipped %s from off-hand and equipped %s.", getName(), offHand.getName(), weapon.getName());
        }

        this.offHand = weapon;
        inventory.removeWeapon(weapon);
        //setDefenseStrategy(weapon.getDefenseStrategy);
        return message;
    }

    public String equipMainHand(Weapon weapon) {
        String message;
        if (mainHand == null) {
            message = String.format("%s has equipped %s in main-hand.", getName(), weapon.getName());
        } else {
            inventory.addWeapon(mainHand);
            message = String.format("%s has unequipped %s from main-hand and equipped %s.", getName(), mainHand.getName(), weapon.getName());
        }

        this.mainHand = weapon;
        inventory.removeWeapon(weapon);
        //setAttackStrategy(weapon.getAttackStrategy);
        return message;
    }

    public String equip(Weapon weapon) {
        if (!inventory.getWeapons().contains(weapon)) {
            return String.format("%s does not have %s in inventory.", getName(), weapon.getName());
        }

        if (weapon instanceof Shield) {
            return equipOffHand(weapon);
        } else {
            return equipMainHand(weapon);
        }
    }

    public String talk(String message) {
        return String.format("%s says: %s", getName(), message);
    }

    public void drink(Potion potion) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }

        if (!inventory.getPotions().contains(potion)) {
            throw new IllegalArgumentException(String.format("%s does not have %s in inventory.", getName(), potion.getName()));
        }

        healHp(potion.getHp());
        inventory.removePotion(potion);
    }

    @Override
    public int getDamage() {
        int totalDamage = super.getDamage();
        if (mainHand != null) {
            totalDamage += mainHand.getDamage();
        }
        if (offHand != null) {
            totalDamage += offHand.getDamage();
        }
        return totalDamage;
    }

    @Override
    public int getDefense() {
        int totalDefence = super.getDefense();
        if (mainHand != null) {
            totalDefence += mainHand.getDefence();
        }
        if (offHand != null) {
            totalDefence += offHand.getDefence();
        }
        return totalDefence;
    }
}