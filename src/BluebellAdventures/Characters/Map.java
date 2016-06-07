package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Map extends Sprite {
	private byte[] path;

	public Map(String filename) throws IOException {
		super(filename, new Point(0, 0));
	}

	public Map(String filename, Point position) throws IOException {
		super(ImageIO.read(new File(filename)), position);
	}

	public Map(BufferedImage image) {
		super(image, new Point(0, 0));
	}

	public Map(BufferedImage image, Point position) {
		super(image, position);
	}

	public Map setPath(byte[] path) {
		this.path = path;
		return this;
	}

	public byte[] getPath() {
		return path;
	}
}