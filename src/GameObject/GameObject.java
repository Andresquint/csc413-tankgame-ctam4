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

    protected void drawImage(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.drawImage(this.image, this.x, this.y, null);
    }
}
