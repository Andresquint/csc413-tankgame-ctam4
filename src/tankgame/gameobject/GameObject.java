package tankgame.gameobject;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GameObject {
    protected BufferedImage image;
    protected int x, y;

    protected GameObject(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.drawImage(this.image, this.x, this.y, null);
    }
}
