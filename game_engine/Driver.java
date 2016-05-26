import java.awt.Dimension;
import java.awt.Point;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Driver {
    public static void main(String[] args) {
        Director director = new Director();

        BufferedImage mapImage = getImage("resource/image/small_map.png");
        byte[][] path = new byte[mapImage.getHeight()][mapImage.getWidth()];
        int i = 0, j = 0;
        try (FileInputStream in = new FileInputStream("resource/path")) {
            int c;
            while ((c = in.read()) != -1) {
                if (c == '0') {
                    path[i][j++] = 0;
                }
                else if (c == '1') {
                    path[i][j++] = 1;
                }
                else if (c == '\n') {
                    i++;
                    j = 0;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        Sprite map = new Map(mapImage, path);
        director.addSprite(map, Sprite.MAP);

        BufferedImage nastuImage = getImage("resource/image/natsu.png");
        Sprite nastu = new Guard(new Point(500, 400), new Dimension(nastuImage.getWidth() / 2, nastuImage.getHeight()), nastuImage, 100, 2);
        director.addSprite(nastu, Sprite.CHARACTER);

        BufferedImage machiImage = getImage("resource/image/machi.png");
        Sprite machi = new OwnPlayer(new Point(300, 300), new Dimension(machiImage.getWidth() / 2, machiImage.getHeight()), machiImage, 100, 2);
        director.addSprite(machi, Sprite.CHARACTER);

        BufferedImage snackImage = getImage("resource/image/snack.png");
        Sprite snack = new Snack(new Point(400, 300), new Dimension(snackImage.getWidth(), snackImage.getHeight()), snackImage, 100);
        director.addSprite(snack, Sprite.SNACK);

        director.start();
    }

    public static BufferedImage getImage(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        }

        return null;
    }
}

