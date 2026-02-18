package com.levelzero.cli;

import static com.levelzero.cli.RendererService.clearScreen;

/**
 * Abstract base class for all screen types in the CLI interface.
 * Provides common rendering functionality and screen dimensions.
 */
public abstract class Screen {
    protected int height;
    protected int width;

    public Screen() {
        height = ScreenData.getInstance().getHeight();
        width = ScreenData.getInstance().getWidth();
    }

    /**
     * Renders the screen by clearing the display and drawing the current content.
     * Subclasses should implement buildContent() to define what is displayed.
     */
    public void render() {
        ScreenData screenData = ScreenData.getInstance();
        clearScreen();
        screenData.render();
    }

    /**
     * Builds the content to be displayed on this screen.
     * Must be implemented by subclasses to define their specific content.
     */
    abstract void buildContent();
}
