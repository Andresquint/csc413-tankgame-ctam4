package tankgame;

import java.lang.IllegalArgumentException;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.ImageIcon;

import tankgame.gameobject.*;

public class Gameworld extends JContainer {
    private JCustomPanel panel_1, panel_2;

    private Tank tank_1, tank_2;

    public Gameworld(Launcher app) {
        super(app);
        // set width & height
        this.width = 0.8;
        this.height = 0.8;
        // import resources using path
        this.app.putResource("Gameworld/background", "../resources/Background.bmp");
        this.app.putResource("Gameworld/wall_1", "../resources/Wall1.gif");
        this.app.putResource("Gameworld/wall_2", "../resources/Wall2.gif");
        this.app.putResource("Gameworld/weapon", "../resources/Weapon.gif");
        this.app.putResource("Gameworld/tank_1", "../resources/Tank1.gif");
        this.app.putResource("Gameworld/tank_2", "../resources/Tank2.gif");
        this.app.putResource("Gameworld/shield_1", "../resources/Shield1.gif");
        this.app.putResource("Gameworld/shield_2", "../resources/Shield2.gif");
        // TODO
        // import strings
        this.app.putString("Gameworld/player_1", "Player 1");
        this.app.putString("Gameworld/player_2", "Player 2");
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
        // set frame size by using current screen size
        DisplayMode device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        System.out.println(this.getClass().getSimpleName() + " - Gameworld() - Get screen size: " + device.getWidth() + "x" + device.getHeight());
        this.frame.setSize(new Dimension((int) (device.getWidth() * this.width), (int) (device.getHeight() * this.height)));
        this.frame.setMaximumSize(this.frame.getSize());
        // create JCustomPanel objects
        this.panel_1 = new JCustomPanel();
        this.panel_2 = new JCustomPanel();
        // set panels layout
        this.panel_1.setLayout(new BorderLayout());
        this.panel_2.setLayout(new BorderLayout());
        // set panels size
        Dimension size = new Dimension((int) (this.frame.getWidth() * 0.5), (int) this.frame.getHeight());
        this.panel_1.setSize(size);
        this.panel_2.setSize(size);
        // set panels visible
        this.panel_1.setVisible(true);
        this.panel_2.setVisible(true);
        // set player info bar
        playerInfoBar();
        // create JSplitPane object
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.panel_1, this.panel_2);
        // remove splitPane divider
        splitPane.setDividerSize(0);
        // set splitPane visible
        splitPane.setVisible(true);
        // add splitPane to frame
        this.frame.add(splitPane);
        // bind GameworldKeyListener to frame
        this.frame.addKeyListener(new GameworldKeyListener(this.app));
    }

    private void playerInfoBar() {
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 50);
        // set player_1 text to panel_1
        JLabel player_1 = new JLabel();
        player_1.setText(this.app.getString("Gameworld/player_1"));
        player_1.setFont(font);
        //player_1.setMaximumSize(new Dimension((int) (this.panel_1.getSize().getWidth()), (int) (this.panel_1.getSize().getHeight() * 0.05)));
        player_1.setHorizontalAlignment(JLabel.CENTER);
        player_1.setVerticalAlignment(JLabel.BOTTOM);
        player_1.setBackground(new Color(0,0,0));
        this.panel_1.add(player_1);
        // set player_1 lives to panel_1
        // TODO
        // set player_2 text to panel_2
        JLabel player_2 = new JLabel();
        player_2.setText(this.app.getString("Gameworld/player_2"));
        player_2.setFont(font);
        //player_2.setMaximumSize(new Dimension((int) (this.panel_2.getSize().getWidth()), (int) (this.panel_2.getSize().getHeight() * 0.05)));
        player_2.setHorizontalAlignment(JLabel.CENTER);
        player_2.setVerticalAlignment(JLabel.BOTTOM);
        this.panel_2.add(player_2);
        // set player_2 lives to panel_2
        // TODO
    }

    public Tank getPlayerTank(int player) {
        switch (player) {
            case 1:
                return this.tank_1;
            case 2:
                return this.tank_2;
            default:
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
