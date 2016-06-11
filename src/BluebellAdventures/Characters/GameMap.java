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

	public static boolean mapCrash(Sprite sprite) {
		byte[][] path = map.getPath();
		int mapX = map.getPosition().getX();
		int mapY = map.getPosition().getY();
		int x = sprite.getPosition().getX();
		int y = sprite.getPosition().getY();
		int w = sprite.getSize().getX();
		int h = sprite.getSize().getY();
		for (int i = 0; i < w; i++) {
			if (path[mapY + y][mapX + x + i] == 1 || path[mapY + y + h][mapX + x + i] == 1) {
				return true;
			}
		}
		for (int i = 0; i < h; i++) {
			if (path[mapY + y + i][mapX + x] == 1 || path[mapY + y + i][mapX + x + w] == 1) {
				return true;
			}
		}

		return false;
	}

	public GameMap setPath(String filename) throws IOException {
		path = new byte[getSize().getY()][getSize().getX()];
		int i = 0, j = 0;
		try (FileInputStream in = new FileInputStream(filename)) {
			int c;
			while ((c = in.read()) != -1) {
				if (c == '\n') {
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