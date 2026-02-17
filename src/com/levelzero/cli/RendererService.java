package com.levelzero.cli;

import java.io.IOException;

public class RendererService {

    public static String[] drawBox(String[] lines, int width) {
        int innerWidth = width - 2;
        String[] box = new String[lines.length + 2];

        box[0] = "┌" + "─".repeat(innerWidth) + "┐";

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String formattedLine = line.length() > innerWidth
                    ? line.substring(0, innerWidth)
                    : String.format("%-" + innerWidth + "s", line);
            box[i + 1] = "│" + formattedLine + "│";
        }

        box[box.length - 1] = "└" + "─".repeat(innerWidth) + "┘";
        return box;
    }

    public static String drawProgressBar(float value, float maxValue, int barLength) {
        int percentage = Math.min(100, Math.max(0, (int) ((value / maxValue) * 100)));
        int filled = (int) ((percentage / 100.0) * barLength);
        String bar = "█".repeat(filled) + " ".repeat(barLength - filled);
        return String.format("[%s] %d%%", bar, percentage);
    }

    public static String[] drawActions(String[] actions, int selectedIndex) {
        String[] lines = new String[actions.length + 4];
        lines[0] = "";
        lines[1] = " Actions:";
        lines[2] = "";

        for (int i = 0; i < actions.length; i++) {
            String prefix = i == selectedIndex ? " > " : "   ";
            lines[i + 3] = prefix + actions[i];
        }

        lines[lines.length - 1] = "";
        return drawBox(lines, 26);
    }

    public static void renderTerminalTooSmallWarning(int termWidth, int termHeight, int minWidth, int minHeight) {
        clearScreen();
        String[] messageLines = {
                "",
                "Terminal is too small!",
                "",
                String.format("Minimum size required: %dx%d", minWidth, minHeight),
                String.format("Current size: %dx%d", termWidth, termHeight),
                "",
                "Please resize your terminal.",
                ""
        };

        int startLine = (termHeight - messageLines.length) / 2;

        for (int i = 0; i < termHeight; i++) {
            if (startLine <= i && i < startLine + messageLines.length) {
                String msg = messageLines[i - startLine];
                int padding = (termWidth - msg.length()) / 2;
                System.out.println(" ".repeat(padding) + msg);
            } else {
                System.out.println();
            }
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
