package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class GameMap extends Sprite {
    private static GameMap map;
    private byte[][] path;

    public GameMap() {
    }

    public GameMap(String filename) throws IOException {
        super(filename, new Point(0, 0));
    }

    public static GameMap getInstance(String filename) throws IOException {
        if (map == null) {
            map = new GameMap(filename);
        }
        else {
            map.setImage(ImageIO.read(new File(filename)));
        }

        return map;
    }

    public static GameMap getInstance() {
        if (map == null) {
            map = new GameMap();
        }

        return map;
    }

    public static boolean characterCrash(Sprite sprite, int moveX, int moveY) {
        byte[][] path = map.getPath();

        //map position
        //this is negative
        int mapX = map.getPosition().getX();
        int mapY = map.getPosition().getY();

        //character postion
        int x = sprite.getPosition().getX() + moveX;
        int y = sprite.getPosition().getY() + moveY;
        int w = sprite.getSize().getX();
        int h = sprite.getSize().getY();

        //check 4 sides of sprite whether any point in path is 0
        for (int i = 0; i < w; i++) {
            if (path[-mapY + y][-mapX + x + i] == 0 || path[-mapY + y + h][-mapX + x + i] == 0) {
                return true;
            }
        }
        for (int i = 0; i < h; i++) {
            if (path[-mapY + y + i][-mapX + x] == 0 || path[-mapY + y + i][-mapX + x + w] == 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean enemyCrash(Sprite sprite, int moveX, int moveY) {
        byte[][] path = map.getPath();

        //enemy and map in same coordinated system
        //so don't need mapX and mapY

        int x = sprite.getPosition().getX() + moveX;
        int y = sprite.getPosition().getY() + moveY;
        int w = sprite.getSize().getX();
        int h = sprite.getSize().getY();

        for (int i = 0; i < w; i++) {
            if (path[y][x + i] == 0 || path[y + h][x + i] == 0) {
                return true;
            }
        }
        for (int i = 0; i < h; i++) {
            if (path[y + i][x] == 0 || path[y + i][x + w] == 0) {
                return true;
            }
        }

        return false;
    }

    public GameMap setPath(String filename) throws IOException {
        //read map path file and save into byte array
        path = new byte[getSize().getY()][getSize().getX()];
        int i = 0, j = 0;
        try (FileInputStream in = new FileInputStream(filename)) {
            int c;
            while ((c = in.read()) != -1) {
                //skip \r for windows
                if (c == '\r') {
                }
                else if (c == '\n') {
                    i++;
                    j = 0;
                }
                else {
                    path[i][j++] = (byte)(c - '0');
                }
            }
        }

        return this;
    }

    public byte[][] getPath() {
        return path;
    }
}
