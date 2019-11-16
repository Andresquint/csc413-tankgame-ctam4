package tankgame.gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.Math;

import tankgame.Launcher;

public class Tank extends GameMovableObject {
    private final int R = 5;
    private final int ROTATESPEED = 10;

    private int health;
    private boolean left, up, down, right, fire;

    public Tank(Launcher app, double scale, BufferedImage image, int x, int y, int vx, int vy, int angle, int maxX, int maxY) {
        super(app, scale, image, x, y, vx, vy, angle, maxX, maxY);
        this.health = 300;
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
                this.left = this.up = this.down = this.right = this.fire = false;
                int vx = (int) Math.round(this.R * this.app.getScale() * this.scale * Math.cos(Math.toRadians(this.angle)));
                int vy = (int) Math.round(this.R * this.app.getScale() * this.scale * Math.sin(Math.toRadians(this.angle)));
                if (vx > 0 || vy > 0) {
                    reverse();
                } else if (vx < 0 || vy < 0){
                    drive();
                }
                return false;
        }
        return false;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void update() {
        if (this.left) {
            turnLeft();
            toggleLeft(false);
            toggleUp(false);
            toggleDown(false);
        }
        if (this.up) {
            drive();
            toggleUp(false);
        }
        if (this.down) {
            reverse();
            toggleDown(false);
        }
        if (this.right) {
            turnRight();
            toggleRight(false);
            toggleUp(false);
            toggleDown(false);
        }
        if (this.fire) {
            shoot();
            toggleFire(false);
        }
    }

    private void turnLeft() {
        this.angle -= this.ROTATESPEED;
        if (this.angle < 0) {
            this.angle += 360;
        } else if (this.angle >= 360) {
            this.angle -= 360;
        }
    }

    private void drive() {
        this.vx = (int) Math.round(this.R * this.app.getScale() * this.scale * Math.cos(Math.toRadians(this.angle)));
        this.vy = (int) Math.round(this.R * this.app.getScale() * this.scale * Math.sin(Math.toRadians(this.angle)));
        if (validPosition(this.x + this.vx, this.y + this.vy)) {
            this.x += this.vx;
            this.y += this.vy;
        }
    }

    private void reverse() {
        this.vx = (int) Math.round(this.R * this.app.getScale() * this.scale * Math.cos(Math.toRadians(this.angle)));
        this.vy = (int) Math.round(this.R * this.app.getScale() * this.scale * Math.sin(Math.toRadians(this.angle)));
        if (validPosition(this.x - this.vx, this.y - this.vy)) {
            this.x -= this.vx;
            this.y -= this.vy;
        }
    }

    private void turnRight() {
        this.angle += this.ROTATESPEED;
        if (this.angle < 0) {
            this.angle += 360;
        } else if (this.angle >= 360) {
            this.angle -= 360;
        }
    }

    private void shoot() {
        int vx = 0, vy = 0;
        do {
            vx += (int) Math.round((this.width + 1) * Math.cos(Math.toRadians(this.angle)));
            vy += (int) Math.round((this.height + 1) * Math.sin(Math.toRadians(this.angle)));
        } while ((vx != 0 && Math.abs(vx) < this.width + 1) || (vy != 0 && Math.abs(vy) < this.height + 1));
        if (vx == 0) {
            if (this.angle == 0 || this.angle == 270) {
                vy -= this.height + 1;
            } else {
                vy += this.height + 1;
            }
        } else if (vy == 0) {
            if (this.angle == 90 || this.angle == 180) {
                vx -= this.width + 1;
            } else {
                vx += this.width + 1;
            }
        }
        this.app.getGameworld().addPlayerBullet(this, this.x + vx, this.y + vy, this.vx, this.vy, this.angle, 20);
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

    public boolean takeDamage(int damage) {
        System.out.println(this.getClass().getSimpleName() + " - takeDamage()");
        if (this.health - damage > 0) {
            this.health -= damage;
            return true;
        }
        this.health = 0;
        return false;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        // draw health bar
        Graphics2D g2d = (Graphics2D) g;
        // left
        Rectangle rect_1 = new Rectangle(this.x, (int) Math.round(this.y - 10 * this.app.getScale() * this.scale), (int) Math.round(this.width * ((this.health / 100.0 > 0 && this.health % 100.0 == 0) ? 1.0 : (this.health % 100.0 / 100))), (int) Math.round(this.height * 0.1));
        if (rect_1.getWidth() > 0) {
            g2d.setColor(Color.GREEN);
            g2d.fillRect((int) Math.round(rect_1.getX()), (int) Math.round(rect_1.getY()), (int) Math.round(rect_1.getWidth()), (int) Math.round(rect_1.getHeight()));
        }
        // right
        Rectangle rect_2 = new Rectangle((int) Math.round(this.x + rect_1.getWidth()), (int) Math.round(rect_1.getY()), (int) Math.round(this.width - rect_1.getWidth()), (int) Math.round(rect_1.getHeight()));
        if (rect_2.getWidth() > 0) {
            g2d.setColor(Color.RED);
            g2d.fillRect((int) Math.round(rect_2.getX()), (int) Math.round(rect_2.getY()), (int) Math.round(rect_2.getWidth()), (int) Math.round(rect_2.getHeight()));
        }
    }
}
