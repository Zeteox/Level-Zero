package com.levelzero;

import com.levelzero.cli.*;
import com.levelzero.creature.hero.HeroType;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        int width = terminal.getWidth();
        int height = terminal.getHeight();

        ScreenData.createInstance(height, width);

        // Hero selection
        HeroType selectedHeroType = selectHero(terminal);

        // Exit raw mode to get hero name
        RendererService.clearScreen();
        System.out.print("Enter your hero name: ");
        System.out.flush();

        NonBlockingReader reader = terminal.reader();
        StringBuilder nameBuilder = new StringBuilder();
        int c;
        while ((c = reader.read()) != 10 && c != 13) { // Read until Enter
            if (c >= 32 && c <= 126) { // Printable characters
                nameBuilder.append((char) c);
                System.out.print((char) c);
                System.out.flush();
            } else if (c == 127 || c == 8) { // Backspace
                if (nameBuilder.length() > 0) {
                    nameBuilder.deleteCharAt(nameBuilder.length() - 1);
                    System.out.print("\b \b");
                    System.out.flush();
                }
            }
        }

        String heroName = nameBuilder.toString().trim();
        if (heroName.isEmpty()) {
            heroName = "Hero";
        }

        // Start game
        Game game = new Game(terminal);
        game.start(heroName, selectedHeroType);
        game.gameLoop();
    }

    private static HeroType selectHero(Terminal terminal) throws IOException {
        NonBlockingReader reader = terminal.reader();
        HeroType[] heroTypes = {HeroType.PALADIN, HeroType.WARRIOR, HeroType.MAGE};
        int selectedIndex = 0;

        terminal.enterRawMode();

        while (true) {
            HeroSelectionScreen heroScreen = new HeroSelectionScreen(selectedIndex);
            heroScreen.render();

            int c = reader.read();

            if (c == 10 || c == 13) { // Enter
                return heroTypes[selectedIndex];
            } else if (c == 27) { // ESC sequence
                int next1 = reader.read();
                if (next1 == 91) { // [
                    int next2 = reader.read();
                    if (next2 == 65) { // A - Up arrow
                        selectedIndex = (selectedIndex - 1 + heroTypes.length) % heroTypes.length;
                    } else if (next2 == 66) { // B - Down arrow
                        selectedIndex = (selectedIndex + 1) % heroTypes.length;
                    }
                }
            }
        }
    }
}