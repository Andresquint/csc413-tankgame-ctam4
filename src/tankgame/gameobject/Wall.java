package tankgame.gameobject;

import java.awt.image.BufferedImage;

import tankgame.Launcher;

public class Wall extends GameObject {
    private boolean isBreakable;

    public Wall(Launcher app, double scale, BufferedImage image, int x, int y, boolean isBreakable) {
        super(app, scale, image, x, y);
        this.isBreakable = isBreakable;
    }

    public boolean onCollision(GameObject gameObject) {
        switch (gameObject.getClass().getSimpleName()) {
            case "Bullet":
                return this.isBreakable;
            case "PowerUp":
            case "Tank":
            case "Wall":
                return false;
        }
        return false;
    }

    public boolean getIsBreakable() {
        return this.isBreakable;
    }
}
