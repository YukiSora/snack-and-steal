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

	public GameMap setPath(String filename) throws IOException {
		byte[][] path = new byte[getSize().getY()][getSize().getX()];
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