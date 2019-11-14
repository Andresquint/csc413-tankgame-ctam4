package tankgame.gameobject;

import java.awt.image.BufferedImage;
import java.lang.Math;

import tankgame.Launcher;

public class Bullet extends GameMovableObject {
    private final int R = 15;

    private int damage;

    public Bullet(Launcher app, BufferedImage image, int x, int y, int vx, int vy, int angle, int maxX, int maxY, int damage) {
        super(app, image, x, y, vx, vy, angle, maxX, maxY);
        this.damage = damage;
    }

    public boolean onCollision(GameObject gameObject) {
        switch (gameObject.getClass().getSimpleName()) {
            case "Bullet":
                return true;
            case "PowerUp":
                return false;
            case "Tank":
                return ((Tank) gameObject).takeDamage(getDamage());
            case "Wall":
                return true;
        }
        return false;
    }

    public void update() {
        // go straight
        this.vx = (int) Math.round(this.R * this.app.getScale() * Math.cos(Math.toRadians(this.angle)));
        this.vy = (int) Math.round(this.R * this.app.getScale() * Math.sin(Math.toRadians(this.angle)));
        // if the new position within current panel
        if (validPosition(this.x + this.vx, this.y + this.vy)) {
            this.x += this.vx;
            this.y += this.vy;
        }
        // if the new position is out of current panel
        else {
            this.app.getGameworld().movePlayerBullet(this, this.x + this.vx, this.y + this.vy, this.vx, this.vy, this.angle, this.damage);
        }
    }

    public int getDamage() {
        return this.damage;
    }
}
