package tankgame.gameobject;

import java.awt.image.BufferedImage;

public class PowerUp extends GameObject {
    String type;

    public PowerUp(BufferedImage image, int x, int y, String type) {
        super(image, x, y);
        this.type = type;
    }
}
