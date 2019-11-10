import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.lang.Math;
import javax.swing.JPanel;

public class Tank extends GameMovableObject {
    private final int R = 2;
    private final int ROTATESPEED = 10;

    private JPanel panel;
    private double health;

    private boolean left, up, down, right, fire;

    public Tank(BufferedImage image, int x, int y, int vx, int vy, int angle, JPanel panel) {
        super(image, x, y, vx, vy, angle);
        this.panel = panel;
        this.health = 1.0;
        this.left = this.up = this.down = this.right = this.fire = false;
    }

    protected void update() {
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
        this.x += this.vx;
        this.y += this.vy;
    }

    private void reverse() {
        this.vx = (int) Math.round(this.R * Math.cos(Math.toRadians(this.angle)));
        this.vy = (int) Math.round(this.R * Math.sin(Math.toRadians(this.angle)));
        this.x -= this.vx;
        this.y -= this.vy;
    }

    private void turnRight() {
        this.angle += this.ROTATESPEED;
    }

    private void shoot() {
        // TODO
    }

    public void toggleLeft() {
        this.left ^= true;
    }

    public void toggleUp() {
        this.up ^= true;
    }

    public void toggleDown() {
        this.down ^= true;
    }

    public void toggleRight() {
        this.right ^= true;
    }

    public void toggleFire() {
        this.fire ^= true;
    }
}
