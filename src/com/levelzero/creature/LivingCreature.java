package com.levelzero.creature;

import com.levelzero.creature.attack.AttackStrategy;
import com.levelzero.creature.defense.DefenseStrategy;
import com.levelzero.creature.heal.HealStrategy;

public abstract class LivingCreature {
    protected final String name;
    protected int maxHp;
    protected int hp;
    protected int gold;
    protected final int baseDamage;
    protected final int baseDefense;
    protected AttackStrategy attackStrategy;
    protected HealStrategy healStrategy;
    protected DefenseStrategy defenseStrategy;

    public LivingCreature(String name, int maxHp, int gold, int baseDamage, int baseDefense, AttackStrategy attackStrategy, HealStrategy healStrategy, DefenseStrategy defenseStrategy) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        if (maxHp <= 0) throw new IllegalArgumentException("Max HP must be positive");
        if (gold < 0) throw new IllegalArgumentException("Gold cannot be negative");

        this.name = name;
        this.baseDamage = baseDamage;
        this.baseDefense = baseDefense;
        setMaxHp(maxHp);
        setHp(maxHp);
        setGold(gold);
        setAttackStrategy(attackStrategy);
        setHealStrategy(healStrategy);
        setDefenseStrategy(defenseStrategy);
    }

    public abstract String getStats();

    /**
     * Check if the creature is alive (HP > 0).
     * @return boolean, true if the creature is alive, false otherwise
     */
    public boolean isAlive() {
        return this.hp > 0;
    }

    public void healHp(int heal) {
        if (healStrategy == null) {
            throw new IllegalStateException("Heal strategy not set");
        }
        healStrategy.healHp(this, heal);
    }

    /**
     * Attack the target creature using the current attack strategy.
     * @param target the LivingCreature to attack
     * @return boolean, true if the function worked or false otherwise
     */
    public boolean attack(LivingCreature target) {
        if (attackStrategy == null) {
            throw new IllegalStateException("Attack strategy not set");
        }
        return attackStrategy.attack(this, target);
    }

    /**
     * Remove HP from the creature with applying defense.
     * This method is used for normal attack.
     * @param damage the amount of HP to remove
     */
    public void removeHp(int damage) {
        if (defenseStrategy == null) {
            throw new IllegalStateException("Defense strategy not set");
        }
        int damageTaken = defenseStrategy.defend(damage, this);
        setHp(this.hp - damageTaken);
    }

    /**
     * Removes HP from the creature without applying defense.
     * This method is used for magic attacks that bypass defense.
     * @param damage the amount of HP to remove
     */
    public void removeMagicHp(int damage) {
        setHp(this.hp - damage);
    }

    public String getName() {
        return name;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getHp() {
        return hp;
    }
    public int getGold() {
        return gold;
    }
    public int getDamage() {
        return baseDamage;
    }
    public int getDefense() {
        return baseDefense;
    }
    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }
    public HealStrategy getHealStrategy() {
        return healStrategy;
    }
    public DefenseStrategy getDefenseStrategy() {
        return defenseStrategy;
    }

    public void setMaxHp(int maxHp) {
        if (maxHp <= 0) {
            throw new IllegalArgumentException("Max HP must be strictly positive.");
        }
        this.maxHp = maxHp;

        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
    }
    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else this.hp = Math.min(hp, this.maxHp);
    }
    public void setGold(int gold) {
        this.gold = Math.max(gold, 0);
    }
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        if (attackStrategy == null) {
            throw new IllegalArgumentException("Attack strategy cannot be null");
        }
        this.attackStrategy = attackStrategy;
    }
    public void setHealStrategy(HealStrategy healStrategy) {
        if (healStrategy == null) {
            throw new IllegalArgumentException("Heal strategy cannot be null");
        }
        this.healStrategy = healStrategy;
    }
    public void setDefenseStrategy(DefenseStrategy defenseStrategy) {
        if (defenseStrategy == null) {
            throw new IllegalArgumentException("Defense strategy cannot be null");
        }
        this.defenseStrategy = defenseStrategy;
    }
}
