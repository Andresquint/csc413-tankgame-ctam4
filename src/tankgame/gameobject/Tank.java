package tankgame.gameobject;

import java.awt.image.BufferedImage;
import java.lang.Math;

import tankgame.Launcher;

public class Tank extends GameMovableObject {
    private final int R = 20;
    private final int ROTATESPEED = 10;

    private double health;

    private boolean left, up, down, right, fire;

    public Tank(Launcher app, BufferedImage image, int x, int y, int vx, int vy, int angle, int maxX, int maxY) {
        super(app, image, x, y, vx, vy, angle, maxX, maxY);
        this.health = 3.0;
        this.left = this.up = this.down = this.right = this.fire = false;
    }

    public boolean onCollision(GameObject gameObject) {
        switch (gameObject.getClass().getSimpleName()) {
            case "Bullet":
                return !takeDamage(((Bullet) gameObject).getDamage());
            case "PowerUp":
                return false;
            case "Tank":
                return true;
            case "Wall":
                if (this.angle >= 0 && this.angle < 45 || this.angle >= 225) {
                    drive();
                } else {
                    reverse();
                }
                return false;
        }
        return false;
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(double health) {
        this.health = health;
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
            this.fire = false;
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
        int x = this.x + this.width / 2 + (int) Math.round(this.width * Math.cos(Math.toRadians(this.angle))) + 1;
        int y = this.y + this.height / 2 + (int) Math.round(this.height * Math.sin(Math.toRadians(this.angle))) + 1;
        this.app.getGameworld().addPlayerBullet(this, x, y, this.vx, this.vy, this.angle, 0.2);
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

    public boolean takeDamage(double damage) {
        System.out.println(this.getClass().getSimpleName() + " - takeDamage()");
        if (this.health - damage > 0) {
            this.health -= damage;
            System.out.println("new health: "+this.health);
            return true;
        }
        return false;
    }
}
