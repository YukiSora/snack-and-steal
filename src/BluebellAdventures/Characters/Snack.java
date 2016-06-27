package BluebellAdventures.Characters;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.Action;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Snack extends Sprite {
    private int score;
    private Action effect;

    // Constructors //
    public Snack(String filename) throws IOException {
        super(filename, new Point(0, 0));
    }

    public Snack(String filename, Point position) throws IOException {
        super(ImageIO.read(new File(filename)), position);
    }

    public Snack(BufferedImage image) {
        super(image, new Point(0, 0));
    }

    public Snack(BufferedImage image, Point position) {
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
    public Snack setScore(int score) {
        this.score = score;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Snack setEffect(Action effect) {
        this.effect = effect;
        return this;
    }

    public Action getEffect() {
        return effect;
    }
}
