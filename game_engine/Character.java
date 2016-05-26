import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

abstract public class Character extends Sprite {
    static final public int updateRate = 16;

    private int movingSpeed;
    private int imageIndex;
    private int imageQuantity;

    Character(Point position, Dimension size, BufferedImage image, int movingSpeed, int imageQuantity) {
        super(position, size, image);
        this.movingSpeed = movingSpeed * 33 / 1000;
        imageIndex = 0;
        this.imageQuantity = imageQuantity;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(getImage(),
                    (int)getPosition().getX(),
                    (int)getPosition().getY(),
                    (int)getPosition().getX() + (int)getSize().getWidth(),
                    (int)getPosition().getY() + (int)getSize().getHeight(),
                    (int)getSize().getWidth() * getImageIndex(),
                    0,
                    (int)getSize().getWidth() * (getImageIndex() + 1),
                    (int)getSize().getHeight(),
                    null);
    }

    @Override
    public void update() {
        tick();
        if (getTick() % updateRate == 0) {
            resetTick();
            imageIndex = (imageIndex + 1) % imageQuantity;
        }
    }

    public int getMovingSpeed() {
        return movingSpeed;
    }

    public void setMovingSpeed(int movingSpeed) {
        this.movingSpeed = movingSpeed;
    }

    public int getImageIndex() {
        return imageIndex;
    }
}
