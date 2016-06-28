package BluebellAdventures.Characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;

import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Character extends Sprite {
    private int hp;
    private int mp;
    private int chargeBar;
    private int speed;
    private int unlockSpeed;
    private int attackScore;
    private int snackScore;
    private int key;

    // Constructors //
    public Character() {
    }

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

    public void render(Graphics2D g) {
        if (getVisible()) {
            super.render(g);
        }
    }

    @Override
    public boolean checkCrash(CopyOnWriteArrayList<Sprite> sprites, Action action) {
        boolean crash = false;
        GameMap map = GameMap.getInstance();
        int x1 = getPosition().getX();
        int y1 = getPosition().getY();
        int w1 = getSize().getX();
        int h1 = getSize().getY();
        Iterator it = sprites.iterator();
        while (it.hasNext()) {
            Sprite sprite = (Sprite)it.next();

            int x2 = map.getPosition().getX() + sprite.getPosition().getX();
            int y2 = map.getPosition().getY() + sprite.getPosition().getY();
            int w2 = sprite.getSize().getX();
            int h2 = sprite.getSize().getY();

            //check whether crash area exist
            if (w2 == 0 || h2 == 0) {
                continue;
            }
            //check whether two rectangle intersect
            if (Math.max(Math.abs(x2 - (x1 + w1)), Math.abs(x2 + w2 - x1)) < w1 + w2 &&
                Math.max(Math.abs(y2 - (y1 + h1)), Math.abs(y2 + h2 - y1)) < h1 + h2) {
                ((Effect)action).setSprite(sprite);
                runAction(action);
                crash = true;
            }
        }

        return crash;
    }

    // Get and Sets //
    public Character setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public int getHp() {
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

    public void addSnackScore(int snackScore) {
        this.snackScore += snackScore;
    }

    public int getSnackScore() {
        return snackScore;
    }

    public Character setKey(int key) {
        this.key = key;
        return this;
    }

    public int getKey() {
        return key;
    }

    public void addKey(int key) {
        this.key += key;
    }

    public void useKey(int key) {
        this.key -= key;
    }
}
