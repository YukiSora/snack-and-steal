package BluebellAdventures.Characters;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class MovableObject extends Sprite {
    private boolean locked;
    private boolean opened;

    // Constructors //
    public MovableObject(String filename) throws IOException {
        super(filename, new Point(0, 0));
    }

    public MovableObject(String filename, Point position) throws IOException {
        super(ImageIO.read(new File(filename)), position);
    }

    public MovableObject(BufferedImage image) {
        super(image, new Point(0, 0));
    }

    public MovableObject(BufferedImage image, Point position) {
        super(image, position);
    }

    @Override
    public void render(Graphics2D g) {
        if (getVisible()) {
            GameMap map = GameMap.getInstance();
            g.drawImage(getImage(), map.getPosition().getX() + getPosition().getX(), map.getPosition().getY() + getPosition().getY(), null);
        }
    }

    // Set and Gets //
    public MovableObject setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public boolean getLocked() {
        return locked;
    }

    public MovableObject setOpened(boolean opened) {
        this.opened = opened;
        return this;
    }

    public boolean getOpened() {
        return opened;
    }
}
