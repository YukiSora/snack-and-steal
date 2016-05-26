import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

abstract public class Player extends Character {
    Player(Point position, Dimension size, BufferedImage image, int movingSpeed, int imageQuantity) {
        super(position, size, image, movingSpeed, imageQuantity);
    }

    public void update() {
        super.update();
    }
}
