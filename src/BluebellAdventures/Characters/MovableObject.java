package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class MovableObject extends Sprite {
		int score;
	
	// Constructors //
	public Snack(String filename, int score) throws IOException {
		super(filename, new Point(0, 0));
		this.score = score;
	}

	public Snack(String filename, Point position, int score) throws IOException {
		super(ImageIO.read(new File(filename)), position);
		this.score = score;
	}

	public Snack(BufferedImage image, int score) {
		super(image, new Point(0, 0));
		this.score = score;
	}

	public Snack(BufferedImage image, Point position, int score) {
		super(image, position);
		this.score = score;
	}
	
	// Set and Gets //
	public void setScore(int score){
		this.score = score;
	}
	public int getScore(){
		return score;
	}
}