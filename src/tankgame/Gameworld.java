package tankgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.lang.Math;
import java.lang.IllegalArgumentException;

import tankgame.gameobject.*;

public class Gameworld extends JContainer {
    private JCustomPanel panel_1, panel_2;
    private JLabel tank_1_health, tank_2_health;

    private Tank tank_1, tank_2;

    public Gameworld(Launcher app) {
        super(app);
        // import resources using path
        this.app.putResource("Gameworld/background", "/resources/Background.bmp");
        this.app.putResource("Gameworld/wall_1", "/resources/Wall1.gif");
        this.app.putResource("Gameworld/wall_2", "/resources/Wall2.gif");
        this.app.putResource("Gameworld/bullet", "/resources/Weapon.gif");
        this.app.putResource("Gameworld/tank_1", "/resources/Tank1.gif");
        this.app.putResource("Gameworld/tank_2", "/resources/Tank2.gif");
        this.app.putResource("Gameworld/shield_1", "/resources/Shield1.gif");
        this.app.putResource("Gameworld/shield_2", "/resources/Shield2.gif");
        this.app.putResource("Gameworld/powerup", "/resources/Pickup.gif");
        // TODO
        // import strings
        this.app.putString("Gameworld/player_1", "PLAYER 1");
        this.app.putString("Gameworld/player_2", "PLAYER 2");
        this.app.putString("Gameworld/weapon", "Weapon");
        this.app.putString("Gameworld/shield", "Shield");
        // TODO
        // create JFrame object
        this.frame = new JFrame();
        // set frame title
        this.frame.setTitle(this.app.getString("name"));
        // set frame icon
        this.frame.setIconImage(this.app.getResource("icon"));
        // set frame close rules
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // set frame resizable
        this.frame.setResizable(false);
        // set frame size
        Dimension size;
        switch (this.app.getScale()) {
            case 1:
                size = new Dimension(1280, 960);
                break;
            case 2:
                size = new Dimension(1920, 1440);
                break;
            case 3:
                size = new Dimension(2160, 1620);
                break;
            default:
                size = new Dimension(1280, 960);
                break;
        }
        this.frame.setSize(size);
        // create JCustomPanel objects
        this.panel_1 = new JCustomPanel();
        this.panel_2 = new JCustomPanel();
        // set panels layout
        this.panel_1.setLayout(new BorderLayout());
        this.panel_2.setLayout(new BorderLayout());
        // set panels size
        size = new Dimension((int) Math.round(this.frame.getWidth() * 0.5), this.frame.getHeight() - 75);
        this.panel_1.setSize(size);
        this.panel_2.setSize(size);
        // set panels background color
        this.panel_1.setBackground(Color.ORANGE);
        this.panel_2.setBackground(Color.PINK);
        // set panels visible
        this.panel_1.setVisible(true);
        this.panel_2.setVisible(true);
        // set player info bar
        playerInfoBar();
        // set GameObjects
        addGameObjects();
        // create JSplitPane object
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.panel_1, this.panel_2);
        // remove splitPane divider
        splitPane.setDividerSize(0);
        // set splitPane equal width
        splitPane.setResizeWeight(0.5);
        // set splitPane visible
        splitPane.setVisible(true);
        // add splitPane to frame
        this.frame.add(splitPane);
        // bind GameworldKeyListener to frame
        this.frame.addKeyListener(new GameworldKeyListener(this.app));
    }

    private void playerInfoBar() {
        Font font;
        // font for player_1 & player_2
        font = new Font(Font.MONOSPACED, Font.BOLD, (int) Math.round(30 * this.app.getScale()));
        // set player_1 text to panel_1
        JLabel player_1 = new JLabel();
        player_1.setLayout(new BorderLayout());
        player_1.setText("< " + this.app.getString("Gameworld/player_1") + " >");
        player_1.setFont(font);
        player_1.setHorizontalAlignment(JLabel.CENTER);
        player_1.setVerticalAlignment(JLabel.BOTTOM);
        player_1.setOpaque(false);
        player_1.setVisible(true);
        this.panel_1.add(player_1);
        // set player_2 text to panel_2
        JLabel player_2 = new JLabel();
        player_2.setLayout(new BorderLayout());
        player_2.setText("< " + this.app.getString("Gameworld/player_2") + " >");
        player_2.setFont(font);
        player_2.setHorizontalAlignment(JLabel.CENTER);
        player_2.setVerticalAlignment(JLabel.BOTTOM);
        player_2.setOpaque(false);
        player_2.setVisible(true);
        this.panel_2.add(player_2);
        font = new Font(Font.MONOSPACED, Font.PLAIN, (int) Math.round(30 * this.app.getScale()));
        // set tank_1_health to panel_1
        this.tank_1_health = new JLabel();
        this.tank_1_health.setFont(font);
        //this.tank_1_health.setForeground(Color.GRAY);
        this.tank_1_health.setHorizontalAlignment(JLabel.LEFT);
        this.tank_1_health.setVerticalAlignment(JLabel.BOTTOM);
        this.tank_1_health.setOpaque(false);
        this.tank_1_health.setVisible(true);
        player_1.add(this.tank_1_health);
        // set tank_2_health to panel_2
        this.tank_2_health = new JLabel();
        this.tank_2_health.setFont(font);
        //this.tank_2_health.setForeground(Color.GRAY);
        this.tank_2_health.setHorizontalAlignment(JLabel.RIGHT);
        this.tank_2_health.setVerticalAlignment(JLabel.BOTTOM);
        this.tank_2_health.setOpaque(false);
        this.tank_2_health.setVisible(true);
        player_2.add(this.tank_2_health);
    }

    public void addPlayerTankHealthInfo() {
        this.tank_1_health.setText("❤ " + ((this.tank_1.getHealth() % 100.0 == 0) ? (this.tank_1.getHealth() / 100 - 1) : (this.tank_1.getHealth() / 100)));
        this.tank_2_health.setText(((this.tank_2.getHealth() % 100.0 == 0) ? (this.tank_2.getHealth() / 100 - 1) : (this.tank_2.getHealth() / 100)) + " ❤");
    }

    private void addGameObjects() {
        BufferedImage image;
        int unitX, unitY, maxUnitX, maxUnitY;
        // get unitX & unitY for unbreakable wall
        image = this.app.getResource("Gameworld/wall_1");
        unitX = (int) Math.round(this.app.getUnitSize() * this.app.getScale());
        unitY = (int) Math.round(this.app.getUnitSize() * this.app.getScale());
        // add unbreakable walls on border to panel_1
        maxUnitX = (int) Math.ceil(this.panel_1.getWidth() / unitX);
        maxUnitY = (int) Math.ceil(this.panel_1.getHeight() / unitY);
        for (int x = 0; x < maxUnitX; x++) {
            for (int y = 0; y < maxUnitY; y++) {
                if (x == 0 || y == 0 || y == maxUnitY - 1) {
                    this.panel_1.gameObjects.add(new Wall(this.app, image, x * unitX, y * unitY, false));
                }
            }
        }
        // add unbreakable walls on border to panel_2
        maxUnitX = (int) Math.ceil(this.panel_2.getWidth() / unitX);
        maxUnitY = (int) Math.ceil(this.panel_2.getHeight() / unitY);
        for (int x = 0; x < maxUnitX; x++) {
            for (int y = 0; y < maxUnitY; y++) {
                if (x == maxUnitX - 1 || y == 0 || y == maxUnitY - 1) {
                    this.panel_2.gameObjects.add(new Wall(this.app, image, x * unitX, y * unitY, false));
                }
            }
        }
        // get unitX & unitY for breakable wall
        image = this.app.getResource("Gameworld/wall_2");
        unitX = (int) Math.round(this.app.getUnitSize() * this.app.getScale());
        unitY = (int) Math.round(this.app.getUnitSize() * this.app.getScale());
        // add breakable walls on border to panel_1
        maxUnitX = (int) Math.ceil(this.panel_1.getWidth() / unitX);
        maxUnitY = (int) Math.ceil(this.panel_1.getHeight() / unitY);
        for (int x = 0; x < maxUnitX; x++) {
            for (int y = 0; y < maxUnitY; y++) {
                if (x == maxUnitX - 1 && !(y == 0 || y == maxUnitY - 1)) {
                    this.panel_1.gameObjects.add(new Wall(this.app, image, x * unitX, y * unitY, true));
                }
            }
        }
        // add breakable walls on border to panel_2
        maxUnitX = (int) Math.ceil(this.panel_2.getWidth() / unitX);
        maxUnitY = (int) Math.ceil(this.panel_2.getHeight() / unitY);
        for (int x = 0; x < maxUnitX; x++) {
            for (int y = 0; y < maxUnitY; y++) {
                if (x == 0 && !(y == 0 || y == maxUnitY - 1)) {
                    this.panel_2.gameObjects.add(new Wall(this.app, image, x * unitX, y * unitY, true));
                }
            }
        }
        // set tank_1 to panel_1
        this.tank_1 = new Tank(this.app, this.app.getResource("Gameworld/tank_1"), this.panel_1.getWidth() / 2, this.panel_1.getHeight() / 2, 0, 0, 0, this.panel_1.getWidth(), this.panel_1.getHeight());
        this.panel_1.gameMovableObjects.add(this.tank_1);
        // set tank_2 to panel_2
        this.tank_2 = new Tank(this.app, this.app.getResource("Gameworld/tank_2"), this.panel_2.getWidth() / 2, this.panel_2.getHeight() / 2, 0, 0, 180, this.panel_2.getWidth(), this.panel_2.getHeight());
        this.panel_2.gameMovableObjects.add(this.tank_2);
        image = this.app.getResource("Gameworld/powerup");
        for (int i = 0; i < 3; i++) {
            // add powerups to panel_1
            this.panel_1.gameObjects.add(new PowerUp(this.app, image, (int) Math.round(Math.random() * (this.panel_1.getWidth() * 0.8) + this.panel_1.getWidth() * 0.1), (int) Math.round(Math.random() * (this.panel_1.getHeight() * 0.8) + this.panel_1.getWidth() * 0.1), "health"));
            // add powerups to panel_2
            this.panel_2.gameObjects.add(new PowerUp(this.app, image, (int) Math.round(Math.random() * (this.panel_2.getWidth() * 0.8) + this.panel_2.getWidth() * 0.1), (int) Math.round(Math.random() * (this.panel_2.getHeight() * 0.8) + this.panel_2.getWidth() * 0.1), "health"));
        }
    }

    public Tank getPlayerTank(int player) {
        switch (player) {
            case 1:
                return this.tank_1;
            case 2:
                return this.tank_2;
        }
        throw new IllegalArgumentException();
    }

    public void addPlayerBullet(Tank tank, int x, int y, int vx, int vy, int angle, int damage) {
        if (tank.equals(getPlayerTank(1))) {
            System.out.println(this.getClass().getSimpleName() + " - addPlayerBullet() - Player 1 - x,y,vx,vy,angle: " + x + "," + y + "," + vx + "," + vy + "," + angle);
            this.panel_1.gameMovableObjects.add(new Bullet(this.app, this.app.getResource("Gameworld/bullet"), x, y, vx, vy, angle, this.panel_1.getWidth(), this.panel_1.getHeight(), damage));
        } else if (tank.equals(getPlayerTank(2))) {
            System.out.println(this.getClass().getSimpleName() + " - addPlayerBullet() - Player 2 - x,y,vx,vy,angle: " + x + "," + y + "," + vx + "," + vy + "," + angle);
            this.panel_2.gameMovableObjects.add(new Bullet(this.app, this.app.getResource("Gameworld/bullet"), x, y, vx, vy, angle, this.panel_2.getWidth(), this.panel_2.getHeight(), damage));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void movePlayerBullet(Bullet bullet, int x, int y, int vx, int vy, int angle, int damage) {
        // move from panel_1 to panel_2
        if (this.panel_1.gameMovableObjects.contains(bullet)) {
            System.out.println(this.getClass().getSimpleName() + " - movePlayerBullet() - Player 1 - x,y,vx,vy,angle: " + x + "," + y + "," + vx + "," + vy + "," + angle);
            // remove bullet from panel_1
            this.panel_1.gameMovableObjects.remove(bullet);
            this.panel_2.gameMovableObjects.add(new Bullet(this.app, this.app.getResource("Gameworld/bullet"), x - this.panel_2.getWidth(), y, vx, vy, angle, this.panel_2.getWidth(), this.panel_1.getHeight(), damage));
        }
        // move from panel_2 to panel_1
        else if (this.panel_2.gameMovableObjects.contains(bullet)) {
            System.out.println(this.getClass().getSimpleName() + " - movePlayerBullet() - Player 2 - x,y,vx,vy,angle: " + x + "," + y + "," + vx + "," + vy + "," + angle);
            // remove bullet from panel_2
            this.panel_2.gameMovableObjects.remove(bullet);
            this.panel_1.gameMovableObjects.add(new Bullet(this.app, this.app.getResource("Gameworld/bullet"), x + this.panel_1.getWidth(), y, vx, vy, angle, this.panel_1.getWidth(), this.panel_1.getHeight(), damage));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected void close() {
        this.panel_1.timer.stop();
        this.panel_2.timer.stop();
        super.close();
    }
}
