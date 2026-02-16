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
        if (maxHp <= 0) throw new IllegalArgumentException("Max HP must be positive");
        if (gold < 0) throw new IllegalArgumentException("Gold cannot be negative");

        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.gold = gold;
        this.baseDamage = baseDamage;
        this.baseDefense = baseDefense;
        this.attackStrategy = attackStrategy;
        this.healStrategy = healStrategy;
        this.defenseStrategy = defenseStrategy;
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
        this.maxHp = maxHp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
    public void setHealStrategy(HealStrategy healStrategy) {
        this.healStrategy = healStrategy;
    }
    public void setDefenseStrategy(DefenseStrategy defenseStrategy) {
        this.defenseStrategy = defenseStrategy;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public String attack(LivingCreature target) {
        if (attackStrategy == null) {
            throw new IllegalStateException("Attack strategy not set");
        }
        return attackStrategy.attack(this, target);
    }

    public void removeHp(int damage) {
        if (defenseStrategy == null) {
            throw new IllegalStateException("Defense strategy not set");
        }
        int damageTaken = defenseStrategy.defend(damage, this);
        if (damageTaken > 0) {
            if (this.hp - damageTaken <= 0) {
                this.hp = 0;
            } else {
                this.hp -= damageTaken;
            }
        }
    }

    public void removeMagicHp(int damage) {
        if (damage > 0) {
            if (this.hp - damage <= 0) {
                this.hp = 0;
            } else {
                this.hp -= damage;
            }
        }
    }

    public void healHp(int heal) {
        if (healStrategy == null) {
            throw new IllegalStateException("Heal strategy not set");
        }
        healStrategy.healHp(this, heal);
    }

    public abstract String getStats();
}
