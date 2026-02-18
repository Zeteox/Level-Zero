package com.levelzero.creature.hero;

import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;
import com.levelzero.creature.heal.HealStrategy;
import com.levelzero.creature.LivingCreature;
import com.levelzero.item.potion.Potion;
import com.levelzero.item.weapon.Shield;
import com.levelzero.item.weapon.Weapon;

public abstract class Hero extends LivingCreature {
    protected Inventory inventory;
    protected Weapon mainHand;
    protected Weapon offHand;

    public Hero(String name, int maxHp, int gold, int damage, int defense, AttackStrategy attackStrategy, HealStrategy healStrategy, DefenseStrategy defenseStrategy, int space) {
        super(name, maxHp, gold, damage, defense, attackStrategy, healStrategy, defenseStrategy);
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

    private void equipOffHand(Weapon weapon) {
        if (offHand != null) {
            inventory.addWeapon(offHand);
        }

        this.offHand = weapon;
        inventory.removeWeapon(weapon);
        setDefenseStrategy(weapon.getDefenseStrategy());
    }

    private void equipMainHand(Weapon weapon) {
        if (mainHand != null) {
            inventory.addWeapon(mainHand);
        }

        this.mainHand = weapon;
        inventory.removeWeapon(weapon);
        setAttackStrategy(weapon.getAttackStrategy());
    }

    public void equip(Weapon weapon) {
        if (!inventory.contains(weapon)) {
            throw new IllegalArgumentException("Weapon must be in inventory to equip.");
        }

        if (weapon instanceof Shield) {
            equipOffHand(weapon);
        } else {
            equipMainHand(weapon);
        }
    }

    public String talk(String message) {
        return String.format("%s says: %s", getName(), message);
    }

    public void drink(Potion potion) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }

        if (!inventory.contains(potion)) {
            throw new IllegalArgumentException(String.format("%s does not have %s in inventory.", getName(), potion.getName()));
        }

        potion.use(this);
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
        int totalDefense = super.getDefense();
        if (mainHand != null) {
            totalDefense += mainHand.getDefense();
        }
        if (offHand != null) {
            totalDefense += offHand.getDefense();
        }
        return totalDefense;
    }
}