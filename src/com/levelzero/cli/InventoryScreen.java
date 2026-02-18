package com.levelzero.cli;

import com.levelzero.creature.hero.Hero;
import static com.levelzero.cli.RendererService.*;

public class InventoryScreen extends Screen {

    private final Hero hero;
    private final String[] actions;
    private final int selectedIndex;

    public InventoryScreen(Hero hero, String[] actions, int selectedIndex) {
        super();
        this.hero = hero;
        this.actions = actions;
        this.selectedIndex = selectedIndex;

        buildContent();
    }

    @Override
    void buildContent() {
        ScreenData screenData = ScreenData.getInstance();
        screenData.clear();

        String[] box = drawBox(width, height);
        screenData.setStringList(0, 0, box);

        String title = "--- HERO INVENTORY ---";
        screenData.setString((width - title.length()) / 2, 2, title);

        screenData.setString(3, 4, "[ Currently Equipped ]");

        String mainHandName = (hero.getMainHand() != null) ?
                hero.getMainHand().getName() + " (Dmg: " + hero.getMainHand().getDamage() + ")" : "None";
        String offHandName = (hero.getOffHand() != null) ?
                hero.getOffHand().getName() + " (Def: " + hero.getOffHand().getDefense() + ")" : "None";

        screenData.setString(5, 6, "Main Hand: " + mainHandName);
        screenData.setString(5, 7, "Off Hand:  " + offHandName);

        int rightAlign = width - 30;
        screenData.setString(rightAlign, 4, "[ Combat Totals ]");
        screenData.setString(rightAlign, 6, "Total Damage:  " + hero.getDamage());
        screenData.setString(rightAlign, 7, "Total Defense: " + hero.getDefense());
        screenData.setString(rightAlign, 8, "Current Gold:  " + hero.getGold());

        String[] actionsMenu = drawActions(actions, selectedIndex);
        screenData.setStringList(2, height - 1 - (actionsMenu.length), actionsMenu);

        String prompt = "Select an item to equip or swap:";
        screenData.setString(3, height - actionsMenu.length - 2, prompt);
    }
}