package com.levelzero;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        int width = terminal.getWidth();
        int height = terminal.getHeight();

        System.out.println("Width: " + width);
        System.out.println("Height: " + height);

    }
}