package Megumin.Nodes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Point;

public class Sprite {
    private BufferedImage image;
    private Point position;
    private Point size;
    private boolean visible;
    private String name;

    public Sprite() {
        image = null;
        position = null;
        size = null;
        visible = false;
        name = "";
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
        name = "";
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

    public boolean checkCrash(CopyOnWriteArrayList<Sprite> sprites, Action action) {
        boolean crash = false;
        int x1 = position.getX();
        int y1 = position.getY();
        int w1 = size.getX();
        int h1 = size.getY();
        Iterator it = sprites.iterator();
        while (it.hasNext()) {
            Sprite sprite = (Sprite)it.next();
            int x2 = sprite.getPosition().getX();
            int y2 = sprite.getPosition().getY();
            int w2 = sprite.getSize().getX();
            int h2 = sprite.getSize().getY();
            if (Math.max(Math.abs(x2 - (x1 + w1)), Math.abs(x2 + w2 - x1)) < w1 + w2 &&
                Math.max(Math.abs(y2 - (y1 + h1)), Math.abs(y2 + h2 - y1)) < h1 + h2) {
                ((Effect)action).setSprite(sprite);
                runAction(action);
                crash = true;
            }
        }

        return crash;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getSize() {
        return size;
    }

    public void setSize(Point size) {
        this.size = size;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
