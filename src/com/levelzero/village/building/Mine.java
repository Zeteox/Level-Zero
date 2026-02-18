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

    /**
     * Calculates the nth Fibonacci number recursively.
     * Used to determine the number of monsters in a mine based on its level.
     *
     * @param n the position in the Fibonacci sequence
     * @return the Fibonacci number
     */
    static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Creates a randomly generated monster with stats scaled to the specified level.
     * The monster's HP, gold, damage, and defense are randomized within level-appropriate ranges.
     *
     * @param level the level of the monster (affects all stats)
     * @param index the index/number of this monster in the mine
     * @return a newly created Monster instance with random stats
     */
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

        int damage = 5 + level + rnd.nextInt(level + 1);
        Sword sword = WeaponFactory.createSword("Monster Sword L" + level, damage, level * 2);

        int defense = 1 + rnd.nextInt(level + 1);
        Shield shield = WeaponFactory.createShield("Monster Shield L" + level, defense, level * 2);

        return new Monster(name, maxHp, gold, baseDamage, baseDefense, attackStrategy, healStrategy, defenseStrategy, sword, shield);
    }
}
