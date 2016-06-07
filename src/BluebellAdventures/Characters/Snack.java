package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Snack extends Sprite {
	private int score;

	// Constructors //
	public Snack(String filename) throws IOException {
		super(filename, new Point(0, 0));
	}

	public Snack(String filename, Point position) throws IOException {
		super(ImageIO.read(new File(filename)), position);
	}

	public Snack(BufferedImage image) {
		super(image, new Point(0, 0));
	}

	public Snack(BufferedImage image, Point position) {
		super(image, position);
	}

	// Set and Gets //
	public Snack setScore(int score) {
		this.score = score;
		return this;
	}

	public int getScore() {
		return score;
	}
}