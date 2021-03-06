package BluebellAdventures.Characters;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;

import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Enemy extends Sprite {
    private int attack;
    private int detectionRange;
    private int speed;
    private int rotate;

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
            int[] direction = getDirection();
            int x = map.getPosition().getX() + getPosition().getX();
            int y = map.getPosition().getY() + getPosition().getY();
            int w = getImage().getWidth();
            int h = getImage().getHeight();

            //TODO: rotate area corrected
            if (direction[0] == -1) {
                if (direction[1] == -1) {
                    rotate = 7;
                    setSize(h, h);
                }
                else if (direction[1] == 0) {
                    rotate = 6;
                    setSize(h, w);
                }
                else if (direction[1] == 1) {
                    rotate = 5;
                    setSize(h, h);
                }
            }
            else if (direction[0] == 0) {
                if (direction[1] == -1) {
                    rotate = 0;
                    setSize(w, h);
                }
                else if (direction[1] == 0) {
                }
                else if (direction[1] == 1) {
                    rotate = 4;
                    setSize(w, h);
                }
            }
            else if (direction[0] == 1) {
                if (direction[1] == -1) {
                    setSize(h, h);
                    rotate = 1;
                }
                else if (direction[1] == 0) {
                    setSize(h, w);
                    rotate = 2;
                }
                else if (direction[1] == 1) {
                    setSize(h, h);
                    rotate = 3;
                }
            }
            double theta = Math.PI / 4 * rotate;

            g.rotate(theta, x + getImage().getWidth() / 2, y + getImage().getHeight() / 2);
            g.drawImage(getImage(), x, y, null);
            g.rotate(-theta, x + getImage().getWidth() / 2, y + getImage().getHeight() / 2);
        }
    }

    @Override
    public boolean checkCollision(CopyOnWriteArrayList<Sprite> sprites, Action action) {
        boolean collision = false;
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

            //check whether collision area exist
            if (w2 == 0 || h2 == 0) {
                continue;
            }
            //check whether two rectangle intersect
            if (Math.max(Math.abs(x2 - (x1 + w1)), Math.abs(x2 + w2 - x1)) < w1 + w2 &&
                Math.max(Math.abs(y2 - (y1 + h1)), Math.abs(y2 + h2 - y1)) < h1 + h2) {
                ((Effect)action).setSprite(sprite);
                runAction(action);
                collision = true;
            }
        }

        return collision;
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
