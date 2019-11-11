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

    private boolean checkCollision(final GameObject gameObject_1, final GameObject gameObject_2) {
        boolean x, y;
        x = (gameObject_1.getX() >= gameObject_2.getX()
        && gameObject_1.getX() <= gameObject_2.getX() + gameObject_2.getWidth())
        || (gameObject_1.getX() + gameObject_1.getWidth() >= gameObject_2.getX()
        && gameObject_1.getX() + gameObject_1.getWidth() <= gameObject_2.getX() + gameObject_2.getWidth());
        y = (gameObject_1.getY() >= gameObject_2.getY()
        && gameObject_1.getY() <= gameObject_2.getY() + gameObject_2.getHeight())
        || (gameObject_1.getY() + gameObject_1.getHeight() >= gameObject_2.getY()
        && gameObject_1.getY() + gameObject_1.getHeight() <= gameObject_2.getY() + gameObject_2.getHeight());
        return (x && y);
    }

    private ArrayList<GameObject> checkCollision(final GameMovableObject gameMovableObject) {
        ArrayList<GameObject> collidedGameObjects = new ArrayList<>();
        // check collision on gameObjects
        this.gameObjects.forEach((n) -> {
            if (checkCollision(gameMovableObject, n)) {
                collidedGameObjects.add(n);
            }
        });
        // check collision on gameMovableObjects
        this.gameMovableObjects.forEach((n) -> {
            if (!gameMovableObject.equals(n) && checkCollision(gameMovableObject, n)) {
                collidedGameObjects.add(n);
            }
        });
        return collidedGameObjects;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(this.getClass().getSimpleName() + " - actionPerformed()");
        this.gameMovableObjects.forEach((n) -> {
            // update objects on gameMovableObjects
            n.update();
            // check collision on gameMovableObjects
            for (GameObject m : checkCollision(n)) {
                // TODO
            }
        });
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
