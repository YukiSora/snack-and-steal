package BluebellAdventures.Characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Character extends Sprite {
	private int hp;
	private int mp;
	private int chargeBar;
	private int lives;
	private int speed;
	private int unlockSpeed;
	private int detectionRange;
	private int attackScore;
	private int snackScore;

	// Constructors //
	public Character(String filename) throws IOException {
		super(filename, new Point(0, 0));
	}

	public Character(String filename, Point position) throws IOException {
		super(ImageIO.read(new File(filename)), position);
	}

	public Character(BufferedImage image) {
		super(image, new Point(0, 0));
	}

	public Character(BufferedImage image, Point position) {
		super(image, position);
	}

	// Get and Sets //
	public Character setHp(int hp) {
		this.hp = hp;
		return this;
	}

	public int getHp(){
		return hp;
	}

	public Character setMp(int mp) {
		this.mp = mp;
		return this;
	}

	public int getMp(){
		return mp;
	}

	public Character setChargeBar(int chargeBar) {
		this.chargeBar = chargeBar;
		return this;
	}

	public int getChargeBar() {
		return chargeBar;
	}

	public Character setLives(int lives) {
		this.lives = lives;
		return this;
	}

	public int getLives() {
		return lives;
	}

	public Character setSpeed(int speed) {
		this.speed = speed;
		return this;
	}

	public int getSpeed() {
		return speed;
	}

	public Character setUnlockSpeed(int unlockSpeed) {
		this.unlockSpeed = unlockSpeed;
		return this;
	}

	public int getUnlockSpeed() {
		return unlockSpeed;
	}

	public Character setDetectionRange(int detectionRange) {
		this.detectionRange = detectionRange;
		return this;
	}

	public int getDetectionRange() {
		return detectionRange;
	}

	public Character setAttackScore(int attackScore) {
		this.attackScore = attackScore;
		return this;
	}

	public int getAttackScore() {
		return attackScore;
	}

	public Character setSnackScore(int snackScore) {
		this.snackScore = snackScore;
		return this;
	}

	public int getSnackScore() {
		return snackScore;
	}
}