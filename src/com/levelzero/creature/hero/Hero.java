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

    /**
     * Equip a weapon in the off-hand slot.
     * If a weapon is already equipped, it will be unequipped and returned to inventory.
     * @param weapon the weapon to equip in off-hand
     * @return a message describing the action performed
     */
    private String equipOffHand(Weapon weapon) {
        String message;
        if (offHand == null) {
            message = String.format("%s has equipped %s in off-hand.", getName(), weapon.getName());
        } else {
            inventory.addWeapon(offHand);
            message = String.format("%s has unequipped %s from off-hand and equipped %s.", getName(), offHand.getName(), weapon.getName());
        }
        this.offHand = weapon;
        inventory.removeWeapon(weapon);
        setDefenseStrategy(weapon.getDefenseStrategy());
        return message;
    }

    /**
     * Equip a weapon in the main-hand slot.
     * If a weapon is already equipped, it will be unequipped and returned to inventory.
     * @param weapon the weapon to equip in main-hand
     * @return a message describing the action performed
     */
    private String equipMainHand(Weapon weapon) {
        String message;
        if (mainHand == null) {
            message = String.format("%s has equipped %s in main-hand.", getName(), weapon.getName());
        } else {
            inventory.addWeapon(mainHand);
            message = String.format("%s has unequipped %s from main-hand and equipped %s.", getName(), mainHand.getName(), weapon.getName());
        }

        this.mainHand = weapon;
        inventory.removeWeapon(weapon);
        setAttackStrategy(weapon.getAttackStrategy());
        return message;
    }

    /**
     * Equip a weapon from the inventory.
     * Shields are equipped in the off-hand, other weapons in the main-hand.
     * @param weapon the weapon to equip
     * @return a message describing the action performed
     */
    public String equip(Weapon weapon) {
        if (!inventory.contains(weapon)) {
            return String.format("%s does not have %s in inventory.", getName(), weapon.getName());
        }

        if (weapon instanceof Shield) {
            return equipOffHand(weapon);
        } else {
            return equipMainHand(weapon);
        }
    }

    /**
     * Make the hero say something.
     * @param message the message to say
     * @return a formatted string with the hero's name and message
     */
    public String talk(String message) {
        return String.format("%s says: %s", getName(), message);
    }

    /**
     * Drink a potion from the inventory.
     * The potion's effect will be applied to the hero and removed from inventory.
     * @param potion the potion to drink
     * @throws IllegalArgumentException if the potion is null or not in inventory
     */
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

    public Inventory getInventory() {
        return inventory;
    }
    public Weapon getMainHand() {
        return mainHand;
    }
    public Weapon getOffHand() {
        return offHand;
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