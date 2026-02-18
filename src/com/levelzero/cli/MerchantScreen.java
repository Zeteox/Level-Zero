package com.levelzero.cli;

import com.levelzero.creature.hero.Hero;
import com.levelzero.village.building.Merchant;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.item.potion.Potion;
import static com.levelzero.cli.RendererService.*;

public class MerchantScreen extends Screen {

    private final Hero hero;
    private final Merchant merchant;
    private final String[] actions;
    private final int actionIndex;

    public MerchantScreen(Hero hero, Merchant merchant, String[] actions, int actionIndex) {
        super();
        this.hero = hero;
        this.merchant = merchant;
        this.actions = actions;
        this.actionIndex = actionIndex;

        buildContent();
    }

    @Override
    void buildContent() {
        ScreenData screenData = ScreenData.getInstance();
        screenData.clear();

        String[] box = drawBox(width, height);
        screenData.setStringList(0, 0, box);

        String goldInfo = "Your Gold: " + hero.getGold();
        screenData.setString(3, 2, goldInfo);
        screenData.setString(width / 2 - 5, 2, "--- MERCHANT ---");

        screenData.setString(3, 6, "[ Your Inventory ]");
        int leftRow = 8;
        for (Weapon w : hero.getInventory().getWeapons()) {
            String stat = w.getDamage() > 0 ? "Dmg: " + w.getDamage() : "Def: " + w.getDefense();
            screenData.setString(3, leftRow++, String.format("- %-10s (%s) Val: %d", w.getName(), stat, w.getPrice()));
        }
        for (Potion p : hero.getInventory().getPotions()) {
            screenData.setString(3, leftRow++, String.format("- %-10s (Heal)      Val: %d", p.getName(), p.getPrice()));
        }

        int rightAlign = (width / 2) + 2;
        screenData.setString(rightAlign, 6, "[ Merchant Stock ]");
        int rightRow = 8;
        for (Weapon w : merchant.getWeapons()) {
            String stat = w.getDamage() > 0 ? "Dmg: " + w.getDamage() : "Def: " + w.getDefense();
            screenData.setString(rightAlign, rightRow++, String.format("- %-10s (%s) Cost: %d", w.getName(), stat, w.getPrice()));
        }
        for (Potion p : merchant.getPotions()) {
            screenData.setString(rightAlign, rightRow++, String.format("- %-10s (Heal)      Cost: %d", p.getName(), p.getPrice()));
        }

        String[] actionsMenu = drawActions(actions, actionIndex);
        screenData.setStringList(2, height - 1 - (actionsMenu.length), actionsMenu);
    }
}