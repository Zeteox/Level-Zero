package com.levelzero.village.building;

import com.levelzero.creature.Monster;

import java.util.ArrayList;


public class Mine implements Building {
    private final int mineLevel;
    private final ArrayList<Monster> monsters;

    public Mine(int mineLevel) {
        this.mineLevel = mineLevel;
        this.monsters = new ArrayList<>();

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
}
