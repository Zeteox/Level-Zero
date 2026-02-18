package com.levelzero.village.building;

import com.levelzero.creature.Monster;
import com.levelzero.creature.attack.SimpleAttackStrategy;
import com.levelzero.creature.defense.SimpleDefenseStrategy;
import com.levelzero.creature.heal.SimpleHealStrategy;
import com.levelzero.item.weapon.Shield;
import com.levelzero.item.weapon.Sword;
import com.levelzero.item.weapon.WeaponFactory;

import java.util.ArrayList;
import java.util.Random;


public class Mine implements Building {
    private final int mineLevel;
    private final ArrayList<Monster> monsters;

    public Mine(int mineLevel) {
        this.mineLevel = mineLevel;
        this.monsters = new ArrayList<>();

        int monsterCount = fibonacci(mineLevel);
        for (int i = 1; i <= monsterCount; i++) {
            this.monsters.add(createRandomMonster(mineLevel, i));
        }
    }

    public int getMineLevel() {
        return mineLevel;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void removeMonster(Monster monster) {
        this.monsters.remove(monster);
    }

    public boolean isEmpty() {
        return this.monsters.isEmpty();
    }

    static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static Monster createRandomMonster(int level, int index) {
        Random rnd = new Random();

        String name = "Monster L" + level + " #" + index;

        int maxHp = 20 + level * 10 + rnd.nextInt(5 * level + 1);
        int gold = 5 + level * 2 + rnd.nextInt(level + 1);
        int baseDamage = 2 + level * 2 + rnd.nextInt(level + 1);
        int baseDefense = 1 + level + rnd.nextInt(level + 1);

        SimpleAttackStrategy attackStrategy = new SimpleAttackStrategy();
        SimpleHealStrategy healStrategy = new SimpleHealStrategy();
        SimpleDefenseStrategy defenseStrategy = new SimpleDefenseStrategy();

        Sword sword = null;
        Shield shield = null;
        if (rnd.nextDouble() < Math.min(0.5, 0.1 * level)) {
            int damage = 1 + level * 2 + rnd.nextInt(level + 1);
            sword = WeaponFactory.createSword("Monster Sword L" + level, damage, damage * 10);
        }
        if (rnd.nextDouble() < Math.min(0.5, 0.1 * level)) {
            int defense = 1 + level + rnd.nextInt(level + 1);
            shield = WeaponFactory.createShield("Monster Shield L" + level, defense, defense * 10);
        }

        return new Monster(name, maxHp, gold, baseDamage, baseDefense, attackStrategy, healStrategy, defenseStrategy, sword, shield);
    }
}
