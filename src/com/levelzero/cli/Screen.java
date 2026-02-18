package com.levelzero.cli;

import static com.levelzero.cli.RendererService.clearScreen;

public abstract class Screen {
    protected int height;
    protected int width;

    public Screen() {
        height = ScreenData.getInstance().getHeight();
        width = ScreenData.getInstance().getWidth();
    }

    protected void render() {
        ScreenData screenData = ScreenData.getInstance();
        clearScreen();
        screenData.render();
    }

    abstract void buildContent();
}
