import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Map extends Sprite {
    static private byte[][] path;

    Map(BufferedImage image, byte[][] path) {
        super(image);
        this.path = path;
    }

    static public boolean isAvailablePosition(Sprite sprite, char coordinate, int position) {
        System.out.println(position);
        if (coordinate == 'x') {
            for (int y = (int)sprite.getPosition().getY(); y <= (int)(sprite.getPosition().getY() + sprite.getSize().getHeight()); y++) {
                if (path[y][position] == 0) {
                    return false;
                }
            }
        }
        if (coordinate == 'y') {
            for (int x = 0; x < 1280; x++) {
                if (path[position][x] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(getImage(), 0, 0, null);
    }

    @Override
    public void update() {
    }
}
