package tankgame;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;

public class Splash extends JContainer {
    private JPanel panel;

    public Splash(Launcher app) {
        super(app);
        // set width & height
        this.width = 0.4;
        this.height = 0.4;
        // import resources using path
        this.app.putResource("Splash/title", "./resources/Title.bmp");
        // import strings
        this.app.putString("Splash/instruction", "[ENTER] to start | [ESC] to quit");
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
        System.out.println(this.getClass().getSimpleName() + " - Splash() - Get screen size: " + device.getWidth() + "x" + device.getHeight());
        this.frame.setSize(new Dimension((int) (device.getWidth() * this.width), (int) (device.getHeight() * this.height)));
        this.frame.setMaximumSize(this.frame.getSize());
        // create JPanel object
        this.panel = new JPanel();
        // set panel size
        this.panel.setSize(this.frame.getSize());
        // set panel padding
        int paddingWidth = (int) (this.panel.getSize().getWidth() * 0.1);
        int paddingHeight = (int) (this.panel.getSize().getHeight() * 0.1);
        this.panel.setBorder(BorderFactory.createEmptyBorder(paddingHeight, paddingWidth, paddingHeight, paddingWidth));
        // set panel visible
        this.panel.setVisible(true);
        // add panel to frame
        this.frame.setContentPane(panel);
        // set title to panel
        JLabel title = new JLabel(new ImageIcon(this.app.getResource("Splash/title")));
        title.setPreferredSize(new Dimension((int) (this.panel.getSize().getWidth()), (int) (this.panel.getSize().getHeight() * 0.5)));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        this.panel.add(title);
        // set instruction to panel
        JLabel instruction = new JLabel();
        instruction.setText(this.app.getString("Splash/instruction"));
        instruction.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        instruction.setPreferredSize(new Dimension((int) (this.panel.getSize().getWidth()), (int) (this.panel.getSize().getHeight() * 0.3)));
        instruction.setHorizontalAlignment(JLabel.CENTER);
        instruction.setVerticalAlignment(JLabel.BOTTOM);
        this.panel.add(instruction);
        // bind SplashKeyListener to frame
        this.frame.addKeyListener(new SplashKeyListener(this.app));
    }
}
