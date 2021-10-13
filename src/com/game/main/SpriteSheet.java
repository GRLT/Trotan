package com.game.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sprite;

    public SpriteSheet(BufferedImage t) {
        this.sprite = t;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        return sprite.getSubimage((row * 16) - 16, (col * 16) - 16, width, height);
    }

}
