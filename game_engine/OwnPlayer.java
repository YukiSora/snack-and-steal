import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class OwnPlayer extends Player {
    private HashMap<Integer, Boolean> key;


    OwnPlayer(Point position, Dimension size, BufferedImage image, int movingSpeed, int imageQuantity) {
        super(position, size, image, movingSpeed, imageQuantity);
        key = new HashMap<>();
        key.put(KeyEvent.VK_W, false);
        key.put(KeyEvent.VK_A, false);
        key.put(KeyEvent.VK_S, false);
        key.put(KeyEvent.VK_D, false);
    }

    @Override
    public void update() {
        super.update();
        if (key.get(KeyEvent.VK_W)) {
            movePosition(0, -getMovingSpeed());
        }
        if (key.get(KeyEvent.VK_A)) {
            movePosition(-getMovingSpeed(), 0);
        }
        if (key.get(KeyEvent.VK_S)) {
            movePosition(0, getMovingSpeed());
        }
        if (key.get(KeyEvent.VK_D)) {
            movePosition(getMovingSpeed(), 0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key.put(e.getKeyCode(), false);
    }
}
