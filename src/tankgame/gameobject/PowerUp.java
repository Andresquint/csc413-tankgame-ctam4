package tankgame.gameobject;

import java.awt.image.BufferedImage;

import tankgame.Launcher;

public class PowerUp extends GameObject {
    String type;

    public PowerUp(Launcher app, BufferedImage image, int x, int y, String type) {
        super(app, image, x, y);
        this.type = type;
    }

    public boolean onCollision(GameObject gameObject) {
        switch (gameObject.getClass().getSimpleName()) {
            case "Bullet":
            case "PowerUp":
            case "Wall":
                return false;
            case "Tank":
                switch (this.type) {
                    case "health":
                        ((Tank) gameObject).setHealth(3.0);
                        break;
                }
                return true;
        }
        return false;
    }
}
