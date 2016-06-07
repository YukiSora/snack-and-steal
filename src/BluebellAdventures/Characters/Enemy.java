package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Enemy extends Sprite {
	private int attack;
	private int detectionRange;
	private int speed;

	// Constructors //
	public Enemy(String filename) throws IOException {
		super(filename, new Point(0, 0));
	}
	
	public Enemy(String filename, Point position) throws IOException {
		super(ImageIO.read(new File(filename)), position);
	}
	
	public Enemy(BufferedImage image) {
		super(image, new Point(0, 0));
	}
	
	public Enemy(BufferedImage image, Point position) {
		super(image, position);
	}
	
	// Get and Sets //
	public Enemy setAttack(int attack) {
		this.attack = attack;
		return this;
	}

	public int getAttack() {
		return attack;
	}

	public Enemy setDetectionRange(int detectionRange) {
		this.detectionRange = detectionRange;
		return this;
	}

	public int getDetectionRange() {
		return detectionRange;
	}

	public Enemy setSpeed(int speed) {
		this.speed = speed;
		return this;
	}

	public int getSpeed() {
		return speed;
	}
}