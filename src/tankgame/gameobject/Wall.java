package tankgame.gameobject;

import java.awt.image.BufferedImage;

import tankgame.Launcher;

public class Wall extends GameObject {
    private boolean isBreakable;
    private boolean isBroken;

    public Wall(Launcher app, BufferedImage image, int x, int y, boolean isBreakable) {
        super(app, image, x, y);
        this.isBreakable = isBreakable;
        if (this.isBreakable) {
            this.isBroken = false;
        }
    }

    public boolean breakIt() {
        if (this.isBreakable && !this.isBroken) {
            this.isBroken = true;
            return true;
        }
        return false;
    }
}
