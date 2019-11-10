package tankgame.gameobject;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;

public abstract class GameMovableObject extends GameObject {
    protected int vx, vy, angle;

    protected GameMovableObject(BufferedImage image, int x, int y, int vx, int vy, int angle) {
        super(image, x, y);
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
    }

    public abstract void update();

    @Override
    public void draw(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(this.x, this.y);
        rotation.rotate(Math.toRadians(this.angle), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, rotation, null);
    }
}
