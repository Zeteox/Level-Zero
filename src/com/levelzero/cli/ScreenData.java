package com.levelzero.cli;

public class ScreenData {
    private static ScreenData instance;
    private int height;
    private int width;
    private char[][] grid;

    private ScreenData(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new char[height][width];
        clear();
    }

    public static void createInstance(int height, int width) {
        if (instance == null) {
            instance = new ScreenData(height, width);
        }
    }

    public static ScreenData getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ScreenData must be initialized with height and width first");
        }
        return instance;
    }

    public void setChar(int x, int y, char ch) {
        if (y >= 0 && y < height && x >= 0 && x < width) {
            grid[y][x] = ch;
        }
    }

    public char getChar(int x, int y) {
        if (y >= 0 && y < height && x >= 0 && x < width) {
            return grid[y][x];
        }
        return ' ';
    }

    public void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = ' ';
            }
        }
    }

    public void resize(int newHeight, int newWidth) {
        this.height = newHeight;
        this.width = newWidth;
        this.grid = new char[newHeight][newWidth];
        clear();
    }

    public void setString(int x, int y, String str) {
        for (int i = 0; i < str.length(); i++) {
            setChar(x + i, y, str.charAt(i));
        }
    }

    public void setStringList(int x, int y, String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            setString(x, y + i, lines[i]);
        }
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < width; x++) {
                line.append(getChar(x, y));
            }
            if (y < height - 1) {
                System.out.println(line);
            } else {
                System.out.print(line);
            }
        }
        System.out.flush();
    }

    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
}
