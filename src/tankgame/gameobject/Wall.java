package tankgame.gameobject;

import java.awt.image.BufferedImage;

import tankgame.Launcher;

public class Wall extends GameObject {
    private boolean isBreakable;

    public Wall(Launcher app, BufferedImage image, int x, int y, boolean isBreakable) {
        super(app, image, x, y);
        this.isBreakable = isBreakable;
    }

    public boolean onCollision(GameObject gameObject) {
        switch (gameObject.getClass().getSimpleName()) {
            case "Tank":
                return false;
        }
        return false;
    }

    public boolean getIsBreakable() {
        return this.isBreakable;
    }
}
