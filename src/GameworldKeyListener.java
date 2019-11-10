import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameworldKeyListener implements KeyListener {
    private TankGame app;

    public GameworldKeyListener(TankGame app) {
        // set app reference
        this.app = app;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                // player 1 up
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_W");
                // TODO
                break;
            case KeyEvent.VK_A:
                // player 1 left
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_A");
                // TODO
                break;
            case KeyEvent.VK_S:
                // player 1 down
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_S");
                // TODO
                break;
            case KeyEvent.VK_D:
                // player 1 right
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_D");
                // TODO
                break;
            case KeyEvent.VK_Z:
                // player 1 shoot
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_Z");
                // TODO
                break;
            case KeyEvent.VK_I:
                // player 2 up
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_I");
                // TODO
                break;
            case KeyEvent.VK_J:
                // player 2 left
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_J");
                // TODO
                break;
            case KeyEvent.VK_K:
                // player 2 down
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_K");
                // TODO
                break;
            case KeyEvent.VK_L:
                // player 2 right
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_L");
                // TODO
                break;
            case KeyEvent.VK_M:
                // player 2 shoot
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_M");
                // TODO
                break;
            case KeyEvent.VK_ESCAPE:
                // press escape key to reset
                System.out.println(this.getClass().getSimpleName() + " - keyPressed() - VK_ESCAPE");
                this.app.reset();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                // player 1 up
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_W");
                // TODO
                break;
            case KeyEvent.VK_A:
                // player 1 left
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_A");
                // TODO
                break;
            case KeyEvent.VK_S:
                // player 1 down
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_S");
                // TODO
                break;
            case KeyEvent.VK_D:
                // player 1 right
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_D");
                // TODO
                break;
            case KeyEvent.VK_Z:
                // player 1 shoot
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_Z");
                // TODO
                break;
            case KeyEvent.VK_I:
                // player 2 up
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_I");
                // TODO
                break;
            case KeyEvent.VK_J:
                // player 2 left
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_J");
                // TODO
                break;
            case KeyEvent.VK_K:
                // player 2 down
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_K");
                // TODO
                break;
            case KeyEvent.VK_L:
                // player 2 right
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_L");
                // TODO
                break;
            case KeyEvent.VK_M:
                // player 2 shoot
                System.out.println(this.getClass().getSimpleName() + " - keyReleased() - VK_M");
                // TODO
                break;
        }
    }
}
