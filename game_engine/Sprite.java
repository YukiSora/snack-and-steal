import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

abstract public class Sprite implements KeyListener {
    public static final int MAP = 0;
    public static final int SNACK = 1;
    public static final int CHARACTER = 2;

    private Point position;
    private Dimension size;
    private int tick;
    private BufferedImage image;

    Sprite(BufferedImage image) {
        this.image = image;
    }

    Sprite(Point position, Dimension size, BufferedImage image) {
        this.position = position;
        this.size = size;
        tick = 0;
        this.image = image;
    }

    abstract public void draw(Graphics2D g);

    abstract public void update();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Point getPosition() {
        return position;
    }

    public void movePosition(int x, int y) {
        int x1 = (int)(position.getX() + x);
        int x2 = (int)(position.getX() + size.getWidth() + x);
        int y1 = (int)(position.getY() + size.getHeight() + y);
        int y2 = (int)(position.getY() + y);
        x = x2 > 1280 ||
            x1 < 0 ? 0 : x;
        y = y1 > 720 ||
            y2 < 0 ? 0 : y;
        position.translate(x, y);
    }

    public Dimension getSize() {
        return size;
    }

    public void tick() {
        tick++;
    }

    public int getTick() {
        return tick;
    }

    public void resetTick() {
        tick = 0;
    }

    public BufferedImage getImage() {
        return image;
    }
}
