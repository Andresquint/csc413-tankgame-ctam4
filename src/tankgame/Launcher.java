package tankgame;

import java.util.HashMap;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Launcher {
    private JContainer splash, gameworld;

    private HashMap<String, BufferedImage> resources;
    private HashMap<String, String> strings;

    public Launcher() {
        // initialize resource & strings
        resources = new HashMap<>();
        strings = new HashMap<>();
        // import resources using path
        putResource("icon", "../resources/icon.ico");
        // TODO import strings
        putString("name", "Tank Game");
        // initialize JContainer object
        this.splash = new Splash(this);
        // ready to splash
        this.splash.show();
    }

    public static void main(String[] args) {
        new Launcher();
    }

    protected BufferedImage getResource(String key) {
        return this.resources.get(key);
    }

    protected void putResource(String key, String value) {
        try {
            this.resources.put(key, ImageIO.read(new File(value)));
        } catch (Exception e) {
            System.out.println(this.getClass().getSimpleName() + " - putResource() - " + e);
        }
    }

    protected String getString(String key) {
        return this.strings.get(key);
    }

    protected void putString(String key, String value) {
        this.strings.put(key, value);
    }

    public void start() {
        this.splash.hide();
        // initialize JContainer object
        this.gameworld = new Gameworld(this);
        // ready to game
        this.gameworld.show();
    }

    public void reset() {
        this.gameworld.close();
        this.splash.show();
    }

    public void quit() {
        this.splash.close();
        System.exit(0);
    }
}
