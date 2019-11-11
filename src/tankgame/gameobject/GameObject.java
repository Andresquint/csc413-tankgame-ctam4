package tankgame.gameobject;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;

import tankgame.Launcher;

public abstract class GameObject {
    protected int width, height;
    protected Launcher app;
    protected BufferedImage image;
    protected int x, y;

    protected GameObject(Launcher app, BufferedImage image, int x, int y) {
        this.app = app;
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = (int) Math.round(this.image.getWidth() * this.app.getScale());
        this.height = (int) Math.round(this.image.getHeight() * this.app.getScale());
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public abstract boolean onCollision(GameObject gameObject);

    public void draw(Graphics g) {
        g.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }
}
