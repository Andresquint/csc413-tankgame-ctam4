package tankgame;

import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;

import tankgame.gameobject.GameObject;

public class JCustomPanel extends JPanel implements ActionListener {
    private ArrayList<GameObject> gameObjects;

    public JCustomPanel() {
        super();
        // initialize gameObjects
        gameObjects = new ArrayList<>();
        // set & start timer
        Timer timer = new Timer(100, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        // repaint automatically
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        // draw objects on gameObjects
        gameObjects.forEach((n) -> n.draw(g));
    }
}
