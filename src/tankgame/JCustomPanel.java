package tankgame;

import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.Timer;

import tankgame.gameobject.GameObject;
import tankgame.gameobject.GameMovableObject;

public class JCustomPanel extends JPanel implements ActionListener {
    public ArrayList<GameObject> gameObjects;
    public ArrayList<GameMovableObject> gameMovableObjects;

    public JCustomPanel() {
        super();
        // initialize gameObjects & gameMovableObjects
        gameObjects = new ArrayList<>();
        gameMovableObjects = new ArrayList<>();
        // set & start timer
        Timer timer = new Timer(50, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        // update objects on gameMovableObjects
        gameMovableObjects.forEach((n) -> n.update());
        // repaint automatically
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        // draw objects on gameObjects & gameMovableObjects
        gameObjects.forEach((n) -> n.draw(g));
        gameMovableObjects.forEach((n) -> n.draw(g));
    }
}
