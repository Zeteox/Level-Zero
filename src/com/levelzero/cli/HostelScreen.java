package com.levelzero.cli;

import com.levelzero.creature.hero.Hero;
import com.levelzero.village.building.Hostel;
import static com.levelzero.cli.RendererService.*;

public class HostelScreen extends Screen {

    private final Hero hero;
    private final Hostel hostel;
    private final String[] actions;
    private final int actionIndex;

    public HostelScreen(Hero hero, Hostel hostel, String[] actions, int actionIndex) {
        super();
        this.hero = hero;
        this.hostel = hostel;
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

        String title = "--- THE VILLAGE HOSTEL ---";
        screenData.setString((width - title.length()) / 2, 2, title);

        String heroName = "Guest: " + hero.getName();
        String hpBar = drawProgressBar(hero.getHp(), hero.getMaxHp(), 15);
        String goldInfo = "Gold: " + hero.getGold();

        screenData.setString(3, 5, heroName);
        screenData.setString(3, 6, hpBar);
        screenData.setString(3, 8, goldInfo);

        String priceInfo = "Price per Night: " + hostel.getPrice() + " Gold";
        String serviceInfo = "Effect: Fully restores HP";

        int rightAlign = width - priceInfo.length() - 5;
        screenData.setString(rightAlign, 5, priceInfo);
        screenData.setString(rightAlign, 6, serviceInfo);

        String welcome = "Need a place to rest ?";
        screenData.setString((width - welcome.length()) / 2, height / 2, welcome);

        String[] actionsMenu = drawActions(actions, actionIndex);
        screenData.setStringList(2, height - 1 - (actionsMenu.length), actionsMenu);
    }
}