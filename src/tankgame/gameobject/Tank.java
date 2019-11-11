package tankgame.gameobject;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.lang.Math;
import javax.swing.JPanel;

import tankgame.Launcher;

public class Tank extends GameMovableObject {
    private final int R = 5;
    private final int ROTATESPEED = 10;

    private double health;

    private boolean left, up, down, right, fire;

    public Tank(Launcher app, BufferedImage image, int x, int y, int vx, int vy, int angle, int offsetX, int offsetY, int maxX, int maxY) {
        super(app, image, x, y, vx, vy, angle, offsetX, offsetY, maxX, maxY);
        this.health = 1.0;
        this.left = this.up = this.down = this.right = this.fire = false;
    }

    public void update() {
        if (this.left) {
            turnLeft();
        }
        if (this.up) {
            drive();
        }
        if (this.down) {
            reverse();
        }
        if (this.right) {
            turnRight();
        }
        if (this.fire) {
            shoot();
        }
    }

    private void turnLeft() {
        this.angle -= this.ROTATESPEED;
    }

    private void drive() {
        this.vx = (int) Math.round(this.R * Math.cos(Math.toRadians(this.angle)));
        this.vy = (int) Math.round(this.R * Math.sin(Math.toRadians(this.angle)));
        if (validPosition(this.x + this.vx, this.y + this.vy)) {
            this.x += this.vx;
            this.y += this.vy;
        }
    }

    private void reverse() {
        this.vx = (int) Math.round(this.R * Math.cos(Math.toRadians(this.angle)));
        this.vy = (int) Math.round(this.R * Math.sin(Math.toRadians(this.angle)));
        if (validPosition(this.x - this.vx, this.y - this.vy)) {
            this.x -= this.vx;
            this.y -= this.vy;
        }
    }

    private void turnRight() {
        this.angle += this.ROTATESPEED;
    }

    private void shoot() {
        // TODO
    }

    public void toggleLeft(boolean value) {
        System.out.println(this.getClass().getSimpleName() + " - toggleLeft()");
        this.left = value;
    }

    public void toggleUp(boolean value) {
        System.out.println(this.getClass().getSimpleName() + " - toggleUp()");
        this.up = value;
    }

    public void toggleDown(boolean value) {
        System.out.println(this.getClass().getSimpleName() + " - toggleDown()");
        this.down = value;
    }

    public void toggleRight(boolean value) {
        System.out.println(this.getClass().getSimpleName() + " - toggleRight()");
        this.right = value;
    }

    public void toggleFire(boolean value) {
        System.out.println(this.getClass().getSimpleName() + " - toggleFire()");
        this.fire = value;
    }
}
