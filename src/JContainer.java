import javax.swing.JFrame;

public abstract class JContainer {
    protected double width, height;

    protected TankGame app;
    protected JFrame frame;

    protected JContainer(TankGame app) {
        // set app reference
        this.app = app;
    }

    protected void show() {
        this.frame.setVisible(true);
        this.frame.setState(JFrame.NORMAL);
    }

    protected void hide() {
        this.frame.setState(JFrame.ICONIFIED);
        this.frame.setVisible(false);
    }

    protected void close() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}
