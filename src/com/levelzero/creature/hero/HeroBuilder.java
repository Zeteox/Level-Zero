package com.levelzero.creature.hero;

public class HeroBuilder {
    private String name;
    private int maxHp;
    private int gold;
    private int damage;
    private int defense;
    private int inventorySpace;
    private int bonus;

    public HeroBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public HeroBuilder setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        return this;
    }
    public HeroBuilder setGold(int gold) {
        this.gold = gold;
        return this;
    }
    public HeroBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }
    public HeroBuilder setDefense(int defense) {
        this.defense = defense;
        return this;
    }
    public HeroBuilder setInventorySpace(int inventorySpace) {
        this.inventorySpace = inventorySpace;
        return this;
    }
    public HeroBuilder setBonus(int bonus) {
        this.bonus = bonus;
        return this;
    }

    public Warrior buildWarrior() {
        return new Warrior(name, maxHp, gold, damage, defense, inventorySpace, bonus);
    }
    public Mage buildMage() {
        return new Mage(name, maxHp, gold, damage, defense, inventorySpace, bonus);
    }
    public Paladin buildPaladin() {
        return new Paladin(name, maxHp, gold, damage, defense, inventorySpace);
    }
}
