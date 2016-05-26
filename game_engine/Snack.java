import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Snack extends Sprite {
    private int score;

    Snack(Point position, Dimension size, BufferedImage image, int score) {
        super(position, size, image);
        this.score = score;
    }

    public void check(Sprite sprite) {
        int x1 = (int)getPosition().getX();
        int y1 = (int)getPosition().getY();
        int w1 = (int)getSize().getWidth();
        int h1 = (int)getSize().getHeight();
        int x2 = (int)sprite.getPosition().getX();
        int y2 = (int)sprite.getPosition().getY();
        int w2 = (int)sprite.getSize().getWidth();
        int h2 = (int)sprite.getSize().getHeight();
        if (sprite instanceof OwnPlayer) {
            if (Math.max(Math.abs(x2 - (x1 + w1)), Math.abs(x2 + w2 - x1)) < w1 + w2 &&
                Math.max(Math.abs(y2 - (y1 + h1)), Math.abs(y2 + h2 - y1)) < h1 + h2) {
                System.out.println(Math.random() * 10 * score);
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(getImage(), (int)getPosition().getX(), (int)getPosition().getY(), null);
    }

    @Override
    public void update() {
    }

    public int getScore() {
        return score;
    }

    public void serScore(int score) {
        this.score = score;
    }
}
