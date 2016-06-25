package BluebellAdventures.Characters;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.Arc2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Iterator;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import java.util.concurrent.CopyOnWriteArrayList;
import BluebellAdventures.Characters.GameMap;
import Megumin.Nodes.Sprite;
import Megumin.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Enemy extends Sprite {
    private int attack;
    private int detectionRange;
    private int speed;

    // Constructors //
    public Enemy() {
        super();
    }

    public Enemy(String filename) throws IOException {
        super(filename, new Point(0, 0));
    }

    public Enemy(String filename, Point position) throws IOException {
        super(ImageIO.read(new File(filename)), position);
    }

    public Enemy(BufferedImage image) {
        super(image, new Point(0, 0));
    }

    public Enemy(BufferedImage image, Point position) {
        super(image, position);
    }

    @Override
    public void render(Graphics2D g) {
        if (getVisible()) {
            GameMap map = GameMap.getInstance();
            int dir = 0;
            if (getDirection()[0] == 1) {
                dir = 3;
            }
            if (getDirection()[0] == -1) {
                dir = 1;
            }
            if (getDirection()[1] == 1) {
                dir = 2;
            }
            if (getDirection()[1] == -1) {
                dir = 0;
            }
            
            AffineTransform tx = new AffineTransform();
            tx.rotate(Math.PI/dir, getImage().getWidth()/2, getImage().getHeight()/2);

            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            g.drawImage(op.filter(getImage(), null), map.getPosition().getX() + getPosition().getX(), map.getPosition().getY() + getPosition().getY(), null);
            
            g.drawImage(getImage(), map.getPosition().getX() + getPosition().getX(), map.getPosition().getY() + getPosition().getY(), null);
            //g.fill(new Arc2D.Double(map.getPosition().getX() + getPosition().getX(), map.getPosition().getY() + getPosition().getY(), 200, 200, 30 + 90 * dir, 120, Arc2D.PIE));
        }
    }

    @Override
    public boolean checkCrash(CopyOnWriteArrayList<Sprite> sprites, Action action) {
        boolean crash = false;
        int x1 = getPosition().getX();
        int y1 = getPosition().getY();
        int w1 = getSize().getX();
        int h1 = getSize().getY();
        Iterator it = sprites.iterator();
        while (it.hasNext()) {
            Sprite sprite = (Sprite)it.next();

            int x2 = sprite.getPosition().getX() - GameMap.getInstance().getPosition().getX();
            int y2 = sprite.getPosition().getY() - GameMap.getInstance().getPosition().getY();
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

    // Get and Sets //
    public Enemy setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public int getAttack() {
        return attack;
    }

    public Enemy setDetectionRange(int detectionRange) {
        this.detectionRange = detectionRange;
        return this;
    }

    public int getDetectionRange() {
        return detectionRange;
    }

    public Enemy setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public int getSpeed() {
        return speed;
    }
}