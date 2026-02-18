package com.levelzero.cli;

import java.util.List;
import static com.levelzero.cli.RendererService.*;

public class MessageScreen extends Screen {

    private final String[] messages;

    public MessageScreen(String[] messages) {
        super();
        this.messages = messages;

        buildContent();
    }

    @Override
    void buildContent() {
        ScreenData screenData = ScreenData.getInstance();
        screenData.clear();

        String[] box = drawBox(width, height);
        screenData.setStringList(0, 0, box);

        int startY = (height - messages.length) / 2;

        for (int i = 0; i < messages.length; i++) {
            String line = messages[i];

            int centerX = (width - line.length()) / 2;

            int currentY = startY + i;
            if (currentY >= 0 && currentY < height) {
                screenData.setString(centerX, currentY, line);
            }
        }
    }
}