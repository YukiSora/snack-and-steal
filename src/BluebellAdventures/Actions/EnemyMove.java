package BluebellAdventures.Actions;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.Enemy;
import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.MoveTo;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class EnemyMove extends MoveTo {
    private CopyOnWriteArrayList<CopyOnWriteArrayList<Sprite>> sprites;
    private Point position;
    private Point size;
    private int speed;
    private int mode;
    private Sprite characterSprite;
    private int[] possibleDirection;
    private Random random;
    private Sprite chaseArea;

    // Mode 0 - Idle
    // Mode 1 - Pursuit

    public EnemyMove(int speed, Point position, Point size) {
        super(speed, 0);
        this.speed = speed;
        this.position = position;
        this.size = size;
        sprites = new CopyOnWriteArrayList<>();
        mode = 0;
        possibleDirection = new int[]{-1, 0, 1};
        random = new Random();

        //create chase detection area
        chaseArea = new Enemy();
        chaseArea.setPosition(position);
        chaseArea.setSize(size);
    }

    public void addSprites(CopyOnWriteArrayList<Sprite> sprites){
        this.sprites.add(sprites);
    }

    @Override
    public void update(Sprite sprite) {
        int[] direction = sprite.getDirection();
        int x = sprite.getPosition().getX() + direction[0] * speed;
        int y = sprite.getPosition().getY() + direction[1] * speed;
        int w = sprite.getSize().getX();
        int h = sprite.getSize().getY();

        // In Pursuit
        if (mode == 1) {
            Point characterPosition = GameMap.getInstance().getPosition();

            if (sprite.getPosition().getX() > -characterPosition.getX() + characterSprite.getPosition().getX()) {
                direction[0] = -1;
            } else {
                direction[0] = 1;
            }

            if (sprite.getPosition().getY() > -characterPosition.getY() + characterSprite.getPosition().getY()) {
                direction[1] = -1;
            } else {
                direction[1] = 1;
            }

            setX(direction[0] * speed);
            setY(direction[1] * speed);
            if (GameMap.enemyCrash(sprite, direction[0] * speed, direction[1] * speed)) {
                direction[0] = possibleDirection[random.nextInt(3)];
                direction[1] = possibleDirection[random.nextInt(3)];
                while (direction[0] == 0 && direction[1] == 0) {
                    direction[0] = possibleDirection[random.nextInt(3)];
                    direction[1] = possibleDirection[random.nextInt(3)];
                }

                setX(direction[0] * speed);
                setY(direction[1] * speed);
            }
            else {
                super.update(sprite);
            }
            if ((x + w) > position.getX() + size.getX()
                || (y + h) > position.getY() + size.getY()
                || x < position.getX()
                || y < position.getY()) {
                mode = 0;
            }
        }

        // Idle
        if (mode == 0){
            if (GameMap.enemyCrash(sprite, direction[0] * speed, direction[1] * speed)
                || (x + w) > position.getX() + size.getX()
                || (y + h) > position.getY() + size.getY()
                || x < position.getX()
                || y < position.getY()) {
                direction[0] = possibleDirection[random.nextInt(3)];
                direction[1] = possibleDirection[random.nextInt(3)];
                while (direction[0] == 0 && direction[1] == 0) {
                    direction[0] = possibleDirection[random.nextInt(3)];
                    direction[1] = possibleDirection[random.nextInt(3)];
                }

                setX(direction[0] * speed);
                setY(direction[1] * speed);
            }
            else {
                super.update(sprite);
            }
        }

        chaseArea.checkCrash(sprites.get(0), new ChaseCharacter(this));
        sprite.checkCrash(sprites.get(0), new CrashCharacter(this));
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Sprite getCharacterSprite() {
        return characterSprite;
    }

    public void setCharacterSprite(Sprite characterSprite) {
        this.characterSprite = characterSprite;
    }
}
