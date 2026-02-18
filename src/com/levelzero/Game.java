package com.levelzero;

import com.levelzero.cli.*;
import com.levelzero.creature.Monster;
import com.levelzero.creature.hero.Hero;
import com.levelzero.creature.hero.HeroFactory;
import com.levelzero.creature.hero.HeroType;
import com.levelzero.item.Item;
import com.levelzero.item.potion.Potion;
import com.levelzero.item.weapon.Shield;
import com.levelzero.item.weapon.Sword;
import com.levelzero.item.weapon.Weapon;
import com.levelzero.utils.TradeUtils;
import com.levelzero.village.Village;
import com.levelzero.village.building.Hostel;
import com.levelzero.village.building.Merchant;
import com.levelzero.village.building.Mine;
import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.levelzero.item.weapon.WeaponFactory.*;

public class Game {
    private Hero hero;
    private Village currentVillage;
    private final Terminal terminal;
    private final NonBlockingReader reader;

    public enum KeyAction {
        UP, DOWN, ENTER, UNKNOWN
    }

    public Game(Terminal terminal) {
        this.terminal = terminal;
        this.reader = terminal.reader();
    }

    public void start(String heroName, HeroType heroType) {
        if (heroName == null || heroName.trim().isEmpty()) {
            throw new IllegalArgumentException("Hero name cannot be null or empty");
        }
        if (heroType == null) {
            throw new IllegalArgumentException("Hero type cannot be null");
        }

        this.hero = createHero(heroName, heroType);

        switch (heroType) {
            case WARRIOR:
                Sword warriorSword = createSword("Basic Sword", 5, 0);
                hero.getInventory().addItem(warriorSword);
                hero.equip(warriorSword);
                break;

            case MAGE:
                Weapon staff = createStaff("Basic Staff", 5, 0);
                hero.getInventory().addItem(staff);
                hero.equip(staff);
                break;

            case PALADIN:
                Sword paladinSword = createSword("Basic Sword", 5, 0);
                Shield paladinShield = createShield("Basic Shield", 2, 0);
                hero.getInventory().addItem(paladinSword);
                hero.getInventory().addItem(paladinShield);
                hero.equip(paladinSword);
                hero.equip(paladinShield);
                break;
        }

        this.currentVillage = createVillage("Village", 1);
    }

    private Hero createHero(String name, HeroType type) {
        return HeroFactory.createHero(type, name);
    }

    private Village createVillage(String name, int level) {
        return new Village(name, level);
    }

    public Hero getHero() {
        return hero;
    }

    public Village getCurrentVillage() {
        return currentVillage;
    }

    public void setCurrentVillage(Village currentVillage) {
        this.currentVillage = currentVillage;
    }

    public boolean isInitialized() {
        return hero != null && currentVillage != null;
    }

    private KeyAction readKey() throws IOException {
        terminal.enterRawMode();
        int c = reader.read();

        if (c == 10 || c == 13) { // Enter
            return KeyAction.ENTER;
        } else if (c == 27) { // ESC sequence
            int next1 = reader.read();
            if (next1 == 91) { // [
                int next2 = reader.read();
                if (next2 == 65) { // Up arrow
                    return KeyAction.UP;
                } else if (next2 == 66) { // Down arrow
                    return KeyAction.DOWN;
                }
            }
        }
        return KeyAction.UNKNOWN;
    }

    public void gameLoop() throws IOException, InterruptedException {
        while (true) {
            int actionIndex = 0;
            String[] actions = {"Enter Hostel", "Visit Merchant", "Go to Mine", "View Inventory"};

            while (true) {
                VillageScreen villageScreen = new VillageScreen(hero, currentVillage, actions, actionIndex);
                villageScreen.render();

                KeyAction key = readKey();
                if (key == KeyAction.UP) {
                    actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                } else if (key == KeyAction.DOWN) {
                    actionIndex = (actionIndex + 1) % actions.length;
                } else if (key == KeyAction.ENTER) {
                    break;
                }
            }

            switch (actionIndex) {
                case 0:
                    hostelLoop();
                    break;
                case 1:
                    merchantLoop();
                    break;
                case 2:
                    mineLoop();
                    break;
                case 3:
                    inventoryLoop();
                    break;
            }
        }
    }

    private void hostelLoop() throws IOException {
        Hostel hostel = (Hostel) currentVillage.getHostelBuildings().get(0);

        while (true) {
            int actionIndex = 0;
            String[] actions = {"Rest", "Leave"};

            while (true) {
                HostelScreen hostelScreen = new HostelScreen(hero, hostel, actions, actionIndex);
                hostelScreen.render();

                KeyAction key = readKey();
                if (key == KeyAction.UP) {
                    actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                } else if (key == KeyAction.DOWN) {
                    actionIndex = (actionIndex + 1) % actions.length;
                } else if (key == KeyAction.ENTER) {
                    break;
                }
            }

            if (actionIndex == 0) {
                if (hero.getGold() >= hostel.getPrice()) {
                    hero.setGold(hero.getGold() - hostel.getPrice());
                    hostel.heal(hero);
                }
            } else {
                return;
            }
        }
    }

    private void merchantLoop() throws IOException {
        Merchant merchant = (Merchant) currentVillage.getMerchantBuildings().get(0);

        while (true) {
            int actionIndex = 0;
            String[] actions = {"Buy Weapon", "Buy Potion", "Sell Weapon", "Sell Potion", "Leave"};

            while (true) {
                MerchantScreen merchantScreen = new MerchantScreen(hero, merchant, actions, actionIndex);
                merchantScreen.render();

                KeyAction key = readKey();
                if (key == KeyAction.UP) {
                    actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                } else if (key == KeyAction.DOWN) {
                    actionIndex = (actionIndex + 1) % actions.length;
                } else if (key == KeyAction.ENTER) {
                    break;
                }
            }

            switch (actionIndex) {
                case 0: // Buy Weapon
                    buyWeaponSubMenu(merchant);
                    break;
                case 1: // Buy Potion
                    buyPotionSubMenu(merchant);
                    break;
                case 2: // Sell Weapon
                    sellWeaponSubMenu(merchant);
                    break;
                case 3: // Sell Potion
                    sellPotionSubMenu(merchant);
                    break;
                case 4: // Leave
                    return;
            }
        }
    }

    private void buyWeaponSubMenu(Merchant merchant) throws IOException {
        List<Weapon> weapons = merchant.getWeapons();
        if (weapons.isEmpty()) {
            return;
        }

        while (true) {
            int actionIndex = 0;
            String[] actions = new String[weapons.size() + 1];
            for (int i = 0; i < weapons.size(); i++) {
                Weapon weapon = weapons.get(i);
                String stat = weapon.getDamage() > 0 ? "Dmg: " + weapon.getDamage() : "Def: " + weapon.getDefense();
                actions[i] = weapon.getName() + " (" + stat + ") - " + weapon.getPrice() + "g";
            }
            actions[weapons.size()] = "Back";

            while (true) {
                MerchantScreen merchantScreen = new MerchantScreen(hero, merchant, actions, actionIndex);
                merchantScreen.render();

                KeyAction key = readKey();
                if (key == KeyAction.UP) {
                    actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                } else if (key == KeyAction.DOWN) {
                    actionIndex = (actionIndex + 1) % actions.length;
                } else if (key == KeyAction.ENTER) {
                    break;
                }
            }

            if (actionIndex == weapons.size()) { // Back
                return;
            } else {
                Weapon selectedWeapon = weapons.get(actionIndex);
                TradeUtils.getInstance().buyItem(hero, merchant, selectedWeapon);
                weapons = merchant.getWeapons();
                if (weapons.isEmpty()) {
                    return;
                }
            }
        }
    }

    private void buyPotionSubMenu(Merchant merchant) throws IOException {
        List<Potion> potions = merchant.getPotions();
        if (potions.isEmpty()) {
            return;
        }

        while (true) {
            int actionIndex = 0;
            String[] actions = new String[potions.size() + 1];
            for (int i = 0; i < potions.size(); i++) {
                Potion p = potions.get(i);
                actions[i] = p.getName() + " (Heal) - " + p.getPrice() + "g";
            }
            actions[potions.size()] = "Back";

            while (true) {
                MerchantScreen merchantScreen = new MerchantScreen(hero, merchant, actions, actionIndex);
                merchantScreen.render();

                KeyAction key = readKey();
                if (key == KeyAction.UP) {
                    actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                } else if (key == KeyAction.DOWN) {
                    actionIndex = (actionIndex + 1) % actions.length;
                } else if (key == KeyAction.ENTER) {
                    break;
                }
            }

            if (actionIndex == potions.size()) { // Back
                return;
            } else {
                Potion selectedPotion = potions.get(actionIndex);
                TradeUtils.getInstance().buyItem(hero, merchant, selectedPotion);
                potions = merchant.getPotions();
                if (potions.isEmpty()) {
                    return;
                }
            }
        }
    }

    private void sellWeaponSubMenu(Merchant merchant) throws IOException {
        List<Weapon> weapons = hero.getInventory().getWeapons();
        if (weapons.isEmpty()) {
            return;
        }

        while (true) {
            int actionIndex = 0;
            String[] actions = new String[weapons.size() + 1];
            for (int i = 0; i < weapons.size(); i++) {
                Weapon w = weapons.get(i);
                String stat = w.getDamage() > 0 ? "Dmg: " + w.getDamage() : "Def: " + w.getDefense();
                actions[i] = w.getName() + " (" + stat + ") - " + w.getPrice() + "g";
            }
            actions[weapons.size()] = "Back";

            while (true) {
                MerchantScreen merchantScreen = new MerchantScreen(hero, merchant, actions, actionIndex);
                merchantScreen.render();

                KeyAction key = readKey();
                if (key == KeyAction.UP) {
                    actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                } else if (key == KeyAction.DOWN) {
                    actionIndex = (actionIndex + 1) % actions.length;
                } else if (key == KeyAction.ENTER) {
                    break;
                }
            }

            if (actionIndex == weapons.size()) { // Back
                return;
            } else {
                Weapon selectedWeapon = weapons.get(actionIndex);
                TradeUtils.getInstance().sellItem(hero, merchant, selectedWeapon);
                weapons = hero.getInventory().getWeapons();
                if (weapons.isEmpty()) {
                    return;
                }
            }
        }
    }

    private void sellPotionSubMenu(Merchant merchant) throws IOException {
        List<Potion> potions = hero.getInventory().getPotions();
        if (potions.isEmpty()) {
            return;
        }

        while (true) {
            int actionIndex = 0;
            String[] actions = new String[potions.size() + 1];
            for (int i = 0; i < potions.size(); i++) {
                Potion p = potions.get(i);
                actions[i] = p.getName() + " (Heal) - " + p.getPrice() + "g";
            }
            actions[potions.size()] = "Back";

            while (true) {
                MerchantScreen merchantScreen = new MerchantScreen(hero, merchant, actions, actionIndex);
                merchantScreen.render();

                KeyAction key = readKey();
                if (key == KeyAction.UP) {
                    actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                } else if (key == KeyAction.DOWN) {
                    actionIndex = (actionIndex + 1) % actions.length;
                } else if (key == KeyAction.ENTER) {
                    break;
                }
            }

            if (actionIndex == potions.size()) { // Back
                return;
            } else {
                Potion selectedPotion = potions.get(actionIndex);
                TradeUtils.getInstance().sellItem(hero, merchant, selectedPotion);
                potions = hero.getInventory().getPotions();
                if (potions.isEmpty()) {
                    return;
                }
            }
        }
    }

    private void mineLoop() throws IOException, InterruptedException {
        Mine mine = (Mine) currentVillage.getMineBuildings().get(0);
        ArrayList<Monster> monsters = mine.getMonsters();

        for (Monster monster : new ArrayList<>(monsters)) {
            String message = "";

            while (hero.isAlive() && monster.isAlive()) {
                int actionIndex = 0;
                String[] actions = {"Attack", "Drink Potion"};

                while (true) {
                    MineScreen mineScreen = new MineScreen(hero, monster, actionIndex, message);
                    mineScreen.render();

                    KeyAction key = readKey();
                    if (key == KeyAction.UP) {
                        actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                    } else if (key == KeyAction.DOWN) {
                        actionIndex = (actionIndex + 1) % actions.length;
                    } else if (key == KeyAction.ENTER) {
                        break;
                    }
                }

                if (actionIndex == 0) { // Attack
                    int monsterHpBefore = monster.getHp();
                    hero.attack(monster);
                    int damageDealt = monsterHpBefore - monster.getHp();

                    message = hero.getName() + " attacked " + monster.getName() + " for " + damageDealt + " damage!";

                    if (monster.isAlive()) {
                        int heroHpBefore = hero.getHp();
                        monster.attack(hero);
                        int damageTaken = heroHpBefore - hero.getHp();
                        message += " " + monster.getName() + " strikes back for " + damageTaken + " damage!";
                    } else {
                        message += " " + monster.getName() + " is defeated!";
                    }

                    Thread.sleep(500);
                } else { // Drink Potion
                    boolean potionDrunk = drinkPotionSubMenu(monster);
                    if (potionDrunk && monster.isAlive()) {
                        int heroHpBefore = hero.getHp();
                        monster.attack(hero);
                        int damageTaken = heroHpBefore - hero.getHp();
                        message = "You drank a potion. " + monster.getName() + " attacks for " + damageTaken + " damage!";
                        Thread.sleep(500);
                    } else {
                        message = "";
                    }
                }
            }

            if (!hero.isAlive()) {
                MessageScreen gameOverScreen = new MessageScreen(new String[]{
                    "",
                    "GAME OVER",
                    "",
                    "You have been defeated...",
                    "",
                    "Press any key to exit."
                });
                gameOverScreen.render();
                reader.read();
                System.exit(0);
            }

            if (!monster.isAlive()) {
                hero.setGold(hero.getGold() + monster.getGold());
                mine.removeMonster(monster);
            }
        }

        if (mine.isEmpty()) {
            MessageScreen victoryScreen = new MessageScreen(new String[]{
                "",
                "VILLAGE CLEARED!",
                "",
                "You have conquered this village!",
                "Advancing to the next level...",
                "",
                "Press any key to continue."
            });
            victoryScreen.render();
            reader.read();

            currentVillage = createVillage("Village", currentVillage.getLevel() + 1);
        }
    }

    private boolean drinkPotionSubMenu(Monster monster) throws IOException {
        List<Potion> potions = hero.getInventory().getPotions();
        if (potions.isEmpty()) {
            return false;
        }

        while (true) {
            int actionIndex = 0;
            String[] actions = new String[potions.size() + 1];
            for (int i = 0; i < potions.size(); i++) {
                Potion p = potions.get(i);
                actions[i] = p.getName();
            }
            actions[potions.size()] = "Back";

            while (true) {
                MineScreen mineScreen = new MineScreen(hero, monster, actionIndex, "Select a potion to drink:", actions);
                mineScreen.render();

                KeyAction key = readKey();
                if (key == KeyAction.UP) {
                    actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                } else if (key == KeyAction.DOWN) {
                    actionIndex = (actionIndex + 1) % actions.length;
                } else if (key == KeyAction.ENTER) {
                    break;
                }
            }

            if (actionIndex == potions.size()) { // Back
                return false;
            } else {
                Potion selectedPotion = potions.get(actionIndex);
                hero.drink(selectedPotion);
                return true;
            }
        }
    }

    private void inventoryLoop() throws IOException {
        while (true) {
            List<Item> items = hero.getInventory().getAllItems();
            int actionIndex = 0;

            String[] actions;
            if (items.isEmpty()) {
                actions = new String[]{"Back"};
            } else {
                actions = new String[items.size() + 1];
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    if (item instanceof Weapon) {
                        Weapon w = (Weapon) item;
                        String stat = w.getDamage() > 0 ? "Dmg: " + w.getDamage() : "Def: " + w.getDefense();
                        actions[i] = w.getName() + " (" + stat + ")";
                    } else if (item instanceof Potion) {
                        Potion p = (Potion) item;
                        actions[i] = p.getName() + " (Heal)";
                    } else {
                        actions[i] = item.getName();
                    }
                }
                actions[items.size()] = "Back";
            }

            while (true) {
                InventoryScreen inventoryScreen = new InventoryScreen(hero, actions, actionIndex);
                inventoryScreen.render();

                KeyAction key = readKey();
                if (key == KeyAction.UP) {
                    actionIndex = (actionIndex - 1 + actions.length) % actions.length;
                } else if (key == KeyAction.DOWN) {
                    actionIndex = (actionIndex + 1) % actions.length;
                } else if (key == KeyAction.ENTER) {
                    break;
                }
            }

            if (items.isEmpty() || actionIndex == items.size()) { // Back
                return;
            } else {
                Item selectedItem = items.get(actionIndex);
                if (selectedItem instanceof Weapon) {
                    try {
                        hero.equip((Weapon) selectedItem);
                    } catch (IllegalArgumentException e) {
                        // The hero type can't handle this weapon, so we just skip
                    }
                }
            }
        }
    }
}


