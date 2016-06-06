package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Enemy extends Sprite{
	int detectionRange, speed, attack;
	
	// Constructors //
	public Enemy(String filename, int detectionRange, int speed, int attack) throws IOException{
		super(filename, new Point(0, 0));
		this.detectionRange = detectionRange;
		this.speed = speed;
		this.attack = attack;
	}
	
	public Enemy(String filename, Point position, int detectionRange, int speed, int attack) throws IOException {
		super(ImageIO.read(new File(filename)), position);
		this.detectionRange = detectionRange;
		this.speed = speed;
		this.attack = attack;
	}
	
	public Enemy(BufferedImage image, int detectionRange, int speed, int attack){
		super(image, new Point(0, 0));
		this.detectionRange = detectionRange;
		this.speed = speed;
		this.attack = attack;
	}
	
	public Enemy(BufferedImage image, Point position, int detectionRange, int speed, int attack){
		super(image, position);
		this.detectionRange = detectionRange;
		this.speed = speed;
		this.attack = attack;
	}
	
	// Get and Sets //
	public void setDetectionRange(int detectionRange){
		this.detectionRange = detectionRange;
	}
	public int getDetectionRange(){
		return detectionRange;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public int getSpeed(){
		return speed;
	}
	
	public void setAttack(int attack){
		this.attack = attack;
	}
	public int getAttack(){
		return attack;
	}
}