package tankgame.gameobject;

import java.awt.image.BufferedImage;

import tankgame.Launcher;

public class Wall extends GameObject {
    public Wall(Launcher app, BufferedImage image, int x, int y) {
        super(app, image, x, y);
    }
}
