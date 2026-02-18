package com.levelzero.cli;

import com.levelzero.creature.Monster;
import com.levelzero.creature.hero.Hero;
import static com.levelzero.cli.RendererService.*;

public class MineScreen extends Screen {

    private final Hero hero;
    private final Monster monster;
    private final String message;
    private final int actionIndex;
    private final String[] actions;

    public MineScreen(Hero hero, Monster monster, int actionIndex, String message) {
        this(hero, monster, actionIndex, message, new String[]{"Attack", "Drink Potion"});
    }

    public MineScreen(Hero hero, Monster monster, int actionIndex, String message, String[] actions) {
        super();
        this.hero = hero;
        this.monster = monster;
        this.actionIndex = actionIndex;
        this.message = message;
        this.actions = actions;

        buildContent();
    }

    @Override
    void buildContent() {
        ScreenData screenData = ScreenData.getInstance();
        screenData.clear();

        String[] box = drawBox(width, height);
        screenData.setStringList(0, 0, box);

        String heroName = "Hero: " + hero.getName();
        String heroHpBar = drawProgressBar(hero.getHp(), hero.getMaxHp(), 15);
        String heroGold = "Gold: " + hero.getGold();

        String heroDmg = "Damage: " + hero.getDamage() + " + " + (hero.getMainHand() != null ? hero.getMainHand().getDamage() : 0);
        String heroDef = "Defense: " + hero.getDefense() + " + " + (hero.getOffHand() != null ? hero.getOffHand().getDefense() : 0);

        screenData.setString(3, 3, heroName);
        screenData.setString(3, 4, heroHpBar);
        screenData.setString(3, 6, heroGold);
        screenData.setString(3, 7, heroDmg);
        screenData.setString(3, 8, heroDef);

        String monsterName = "Monster: " + monster.getName();
        String monsterHpBar = drawProgressBar(monster.getHp(), monster.getMaxHp(), 15);
        String monsterDmg = "Damage: " + monster.getDamage();
        String monsterDef = "Defense: " + monster.getDefense();

        int rightAlign = width - 30;
        screenData.setString(rightAlign, 3, monsterName);
        screenData.setString(rightAlign, 4, monsterHpBar);
        screenData.setString(rightAlign, 6, monsterDmg);
        screenData.setString(rightAlign, 7, monsterDef);

        if (message != null && !message.isEmpty()) {
            int msgX = (width - message.length()) / 2;
            int msgY = height / 2;
            screenData.setString(msgX, msgY, ">> " + message + " <<");
        }

        String[] actionsMenu = drawActions(actions, actionIndex);
        screenData.setStringList(2, height - 1 - (actionsMenu.length), actionsMenu);
    }
}