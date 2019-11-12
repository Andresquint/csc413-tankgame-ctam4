package tankgame;

import java.util.concurrent.CopyOnWriteArrayList;
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
    public CopyOnWriteArrayList<GameObject> gameObjects;
    public CopyOnWriteArrayList<GameMovableObject> gameMovableObjects;

    public JCustomPanel() {
        super();
        // initialize gameObjects & gameMovableObjects
        this.gameObjects = new CopyOnWriteArrayList<>();
        this.gameMovableObjects = new CopyOnWriteArrayList<>();
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

    private CopyOnWriteArrayList<GameObject> checkCollision(final GameMovableObject gameMovableObject) {
        CopyOnWriteArrayList<GameObject> collidedGameObjects = new CopyOnWriteArrayList<>();
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
                if (m.onCollision(n)) {
                    switch (m.getClass().getSuperclass().getSimpleName()) {
                        case "GameObject":
                            this.gameObjects.remove(m);
                            break;
                        case "GameMovableObject":
                            this.gameMovableObjects.remove(m);
                            break;
                        default:
                            System.out.println("Failed");
                    }
                }
            }
        });
        // repaint automatically
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw objects on gameObjects & gameMovableObjects
        this.gameObjects.forEach((n) -> n.draw(g));
        this.gameMovableObjects.forEach((n) -> n.draw(g));
    }
}
