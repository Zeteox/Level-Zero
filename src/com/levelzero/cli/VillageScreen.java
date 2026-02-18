package com.levelzero.cli;

import com.levelzero.creature.hero.Hero;
import com.levelzero.village.Village;

import static com.levelzero.cli.RendererService.*;

public class VillageScreen extends Screen {

    private final Hero hero;
    private final Village village;
    private final String[] actions;
    private final int actionIndex;


    public VillageScreen(Hero hero, Village village, String[] actions, int actionIndex) {
        super();
        this.hero = hero;
        this.village = village;
        this.actions = actions;
        this.actionIndex = actionIndex;

        buildContent();
    }

    @Override
    public void buildContent() {
        ScreenData screenData = ScreenData.getInstance();
        screenData.clear();
        String[] box = drawBox(width, height);
        String[] actionsMenu = drawActions(actions, actionIndex);
        String hpBar = drawProgressBar(hero.getHp(), hero.getMaxHp(), 10);
        String goldInfo = "Gold: " + hero.getGold();
        String damageInfo = "Damage: " + hero.getDamage() + " +  " + hero.getMainHand().getDamage();
        String defenseInfo = "Defense: " + hero.getDefense() + " +  " + hero.getOffHand().getDefense();
        String heroName = "Hero: " + hero.getName();

        String locationInfo = "Location: " + village.getName() + " " + village.getLevel();

        screenData.setStringList(0, 0, box);
        screenData.setStringList(2, height - 1 - (actionsMenu.length), actionsMenu);
        screenData.setString(width - hpBar.length() - 3, 3, hpBar);
        screenData.setString(width - goldInfo.length() - 3, 5, goldInfo);
        screenData.setString(width - damageInfo.length() - 3, 7, damageInfo);
        screenData.setString(width - defenseInfo.length() - 3, 9, defenseInfo);
        screenData.setString(width / 2 - (locationInfo.length() / 2), 2, locationInfo);
        screenData.setString( 3, 3, heroName);
    }
}

