import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Guard extends Character {
    Guard(Point position, Dimension size, BufferedImage image, int movingSpeed, int imageQuantity) {
        super(position, size, image, movingSpeed, imageQuantity);
    }

    @Override
    public void update() {
        super.update();

        movePosition(getMovingSpeed() * ((int)(Math.random() * 3) - 1), getMovingSpeed() * ((int)(Math.random() * 3) - 1));
    }
}
