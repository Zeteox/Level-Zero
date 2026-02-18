package com.levelzero.cli;

import static com.levelzero.cli.RendererService.*;

public class HeroSelectionScreen extends Screen {

    private final int selectedIndex;

    private final String[] heroClasses = {"Paladin", "Warrior", "Mage"};
    private final String[] descriptions = {
            "Paladin: The balanced choice. Starts with a Sword and a Shield.",
            "Warrior: High Strength. No shield, but deals significantly more damage.",
            "Mage: Fragile but clever. Starts with a Staff and a natural chance to dodge."
    };

    public HeroSelectionScreen(int selectedIndex) {
        super();
        this.selectedIndex = selectedIndex;
        buildContent();
    }

    @Override
    void buildContent() {
        ScreenData screenData = ScreenData.getInstance();
        screenData.clear();

        String[] box = drawBox(width, height);
        screenData.setStringList(0, 0, box);

        String[] actionsMenu = drawActions(heroClasses, selectedIndex);

        int centerX = (width / 2) - 10;
        int centerY = (height / 2);

        String message = "Welcome to Level-Zero! Please choose your hero type:";
        int messageX = (width - message.length()) / 2;
        screenData.setString(messageX, centerY - 6, message);

        String currentDesc = descriptions[selectedIndex];
        int descX = (width - currentDesc.length()) / 2;
        screenData.setString(descX, centerY - 4, ">> " + currentDesc + " <<");

        screenData.setStringList(centerX, centerY - 2, actionsMenu);
    }
}