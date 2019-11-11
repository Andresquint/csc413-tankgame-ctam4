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
    public Timer timer;
    public ArrayList<GameObject> gameObjects;
    public ArrayList<GameMovableObject> gameMovableObjects;

    public JCustomPanel() {
        super();
        // initialize gameObjects & gameMovableObjects
        this.gameObjects = new ArrayList<>();
        this.gameMovableObjects = new ArrayList<>();
        // set & start timer
        this.timer = new Timer(200, this);
        this.timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(this.getClass().getSimpleName() + " - actionPerformed()");
        // update objects on gameMovableObjects
        this.gameMovableObjects.forEach((n) -> n.update());
        // repaint automatically
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        // draw objects on gameObjects & gameMovableObjects
        this.gameObjects.forEach((n) -> n.draw(g));
        this.gameMovableObjects.forEach((n) -> n.draw(g));
    }
}
