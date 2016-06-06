package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Character extends Sprite {
	int hp, mp, speed, unlockSpeed, detectionRange, attackScore, snackScore;
	
	public Character(String filename, int hp, int mp, int speed, int unlockSpeed,int detectionRange, int attackScore, int snackScore){
		super(filename, new Point(0, 0));
		this.hp = 100;
		this.mp = 100;
		this.speed = speed;
		this.unlockSpeed = unlockSpeed;
		this.detectionRange = detectionRange;
		this.attackScore = 0;
		this.snackScore = 0;
	}
	
	public Character(String filename, Point position, int hp, int mp, int speed, int unlockSpeed,int detectionRange, int attackScore, int snackScore){
		super(ImageIO.read(new File(filename)), position);
		this.hp = 100;
		this.mp = 100;
		this.speed = speed;
		this.unlockSpeed = unlockSpeed;
		this.detectionRange = detectionRange;
		this.attackScore = 0;
		this.snackScore = 0;
	}
	
	public Character(BufferedImage image, int hp, int mp, int speed, int unlockSpeed,int detectionRange, int attackScore, int snackScore){
		super(image, new Point(0, 0));
		this.hp = 100;
		this.mp = 100;
		this.speed = speed;
		this.unlockSpeed = unlockSpeed;
		this.detectionRange = detectionRange;
		this.attackScore = 0;
		this.snackScore = 0;
	}
	
	public Character(BufferedImage image, Point position, int hp, int mp, int speed, int unlockSpeed,int detectionRange, int attackScore, int snackScore){
		super(image, position);
		this.hp = 100;
		this.mp = 100;
		this.speed = speed;
		this.unlockSpeed = unlockSpeed;
		this.detectionRange = detectionRange;
		this.attackScore = 0;
		this.snackScore = 0;
	}
	
	public void setHp(int hp){
		this.hp = hp;
	}
	public int getHp(){
		return hp;
	}
	
	public void setMp(int mp){
		this.mp = mp;
	}
	public int getMp(){
		return mp;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public int getSpeed(){
		return speed;
	}
	
	public void setUnlockSpeed(int unlockSpeed){
		this.unlockSpeed = unlockSpeed;
	}
	public int getUnlockSpeed(){
		return unlockSpeed;
	}
	
	public void setDetectionRange(int detectionRange){
		this.detectionRange = detectionRange;
	}
	public int getDetectionRange(){
		return detectionRange;
	}
	
	public void setAttackScore(int attackScore){
		this.attackScore = attackScore;
	}
	public int getAttackScore(){
		return attackScore;
	}
	
	public void setSnackScore(int snackScore){
		this.snackScore = snackScore;
	}
	public int getSnackScore(){
		return snackScore;
	}
}