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
        } else if (hp > this.maxHp) {
            this.hp = this.maxHp;
        } else {
            this.hp = hp;
        }
    }
    public void setGold(int gold) {
        if (gold < 0) {
            this.gold = 0;
        } else {
            this.gold = gold;
        }
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

    public boolean isAlive() {
        return this.hp > 0;
    }

    public boolean attack(LivingCreature target) {
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
        setHp(this.hp - damageTaken);
    }

    public void removeMagicHp(int damage) {
        setHp(this.hp - damage);
    }

    public void healHp(int heal) {
        if (healStrategy == null) {
            throw new IllegalStateException("Heal strategy not set");
        }
        healStrategy.healHp(this, heal);
    }

    public abstract String getStats();
}
