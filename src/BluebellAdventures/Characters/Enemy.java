package BluebellAdventures.Characters;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.Arc2D;
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

	@Override
	public void render(Graphics2D g) {
		if (getVisible()) {
			GameMap map = GameMap.getInstance();
			g.drawImage(getImage(), map.getPosition().getX() + getPosition().getX(), map.getPosition().getY() + getPosition().getY(), null);
			int dir = 0;
			if (getDirection()[0] == 1) {
				dir = 3;
			}
			if (getDirection()[0] == -1) {
				dir = 1;
			}
			if (getDirection()[1] == 1) {
				dir = 2;
			}
			if (getDirection()[1] == -1) {
				dir = 0;
			}
			g.fill(new Arc2D.Double(map.getPosition().getX() + getPosition().getX(), map.getPosition().getY() + getPosition().getY(), 200, 200, 30 + 90 * dir, 120, Arc2D.PIE));
		}
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