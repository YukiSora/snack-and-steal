package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Map extends Sprite {
	byte[] path;

	public Map(String filename, byte[] path) throws IOException {
		super(filename, new Point(0, 0));
		this.path = path;
	}

	public Map(String filename, Point position, byte[] path) throws IOException {
		super(ImageIO.read(new File(filename)), position);
		this.path = path;
	}

	public Map(BufferedImage image, byte[] path) {
		super(image, new Point(0, 0));
		this.path = path;
	}

	public Map(BufferedImage image, Point position, byte[] path) {
		super(image, position);
		this.path = path;
	}

	public void setPath(byte[] path) {
		this.path = path;
	}

	public byte[] getPath() {
		return path;
	}
}