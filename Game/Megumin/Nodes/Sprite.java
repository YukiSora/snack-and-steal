package Megumin.Nodes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Actions.Action;
import Megumin.Point;

public class Sprite {
    private BufferedImage image;
    private Point position;
    private Point size;
    private boolean visible;

    public Sprite() {
        image = null;
        position = null;
        size = null;
        visible = false;
    }

    public Sprite(String filename) throws IOException {
        this(filename, new Point(0, 0));
    }

    public Sprite(String filename, Point position) throws IOException {
        this(ImageIO.read(new File(filename)), position);
    }

    public Sprite(BufferedImage image) {
        this(image, new Point(0, 0));
    }

    public Sprite(BufferedImage image, Point position) {
        this.image = image;
        this.position = position;
        size = new Point(image.getWidth(), image.getHeight());
        visible = true;
    }

    public void render(Graphics2D g) {
        g.drawImage(image,
                    position.getX(), position.getY(),
                    position.getX() + size.getX(), position.getY() + size.getY(),
                    0, 0,
                    size.getX(), size.getY(),
                    null);
    }

    public Action runAction(Action action) {
        action.update(this);

        return action;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
