package tankgame.gameobject;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;

import tankgame.Launcher;

public abstract class GameMovableObject extends GameObject {
    protected int vx, vy, angle, maxX, maxY;

    protected GameMovableObject(Launcher app, BufferedImage image, int x, int y, int vx, int vy, int angle, int maxX, int maxY) {
        super(app, image, x, y);
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public abstract void update();

    protected boolean validPosition(int x, int y) {
        return (x >= 0 && x < this.maxX - this.image.getWidth() * this.app.getScale() && y >= 0 && y < this.maxY - this.image.getHeight() * this.app.getScale());
    }

    @Override
    public void draw(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(this.x, this.y);
        rotation.scale(this.app.getScale(), this.app.getScale());
        rotation.rotate(Math.toRadians(this.angle), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, rotation, null);
    }
}
