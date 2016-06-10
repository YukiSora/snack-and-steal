package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class MovableObject extends Sprite {
	private int score;

	// Constructors //
	public MovableObject(String filename) throws IOException {
		super(filename, new Point(0, 0));
	}

	public MovableObject(String filename, Point position) throws IOException {
		super(ImageIO.read(new File(filename)), position);
	}

	public MovableObject(BufferedImage image) {
		super(image, new Point(0, 0));
	}

	public MovableObject(BufferedImage image, Point position) {
		super(image, position);
	}
	
	// Set and Gets //
	public MovableObject setScore(int score) {
		this.score = score;
		return this;
	}

	public int getScore() {
		return score;
	}
}