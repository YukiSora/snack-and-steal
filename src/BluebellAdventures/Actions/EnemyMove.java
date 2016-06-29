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
        System.out.println(mode);
        int[] direction = sprite.getDirection();
        int x = sprite.getPosition().getX() + direction[0] * speed;
        int y = sprite.getPosition().getY() + direction[1] * speed;
        int w = sprite.getSize().getX();
        int h = sprite.getSize().getY();
        boolean walkable = false;

        // In Pursuit
        if (mode == 1) {
            Point characterPosition = GameMap.getInstance().getPosition();

            if (sprite.getPosition().getX() - characterSprite.getSize().getX() > -characterPosition.getX() + characterSprite.getPosition().getX()) {
                direction[0] = -1;
            }
            else if (sprite.getPosition().getX() + characterSprite.getSize().getX() < -characterPosition.getX() + characterSprite.getPosition().getX()) {
                direction[0] = 1;
            }
            else {
                direction[0] = 0;
            }

            if (sprite.getPosition().getY() - characterSprite.getSize().getY() > -characterPosition.getY() + characterSprite.getPosition().getY()) {
                direction[1] = -1;
            }
            else if (sprite.getPosition().getY() + characterSprite.getSize().getY() < -characterPosition.getY() + characterSprite.getPosition().getY()) {
                direction[1] = 1;
            }
            else {
                direction[1] = 0;
            }

            setX(direction[0] * speed);
            setY(direction[1] * speed);

            boolean xCrash = GameMap.enemyCrash(sprite, direction[0] * speed, 0);
            boolean yCrash = GameMap.enemyCrash(sprite, 0, direction[1] * speed);
            if (xCrash || yCrash) {
                if (xCrash && !yCrash) {
                    setX(0);
                    if (sprite.getPosition().getY() > -characterPosition.getY() + characterSprite.getPosition().getY()) {
                        setY(-1 * speed);
                    }
                    else if (sprite.getPosition().getY() <= -characterPosition.getY() + characterSprite.getPosition().getY()) {
                        setY(1 * speed);
                    }
                }
                else if (!xCrash && yCrash) {
                    if (sprite.getPosition().getX() > -characterPosition.getX() + characterSprite.getPosition().getX()) {
                        setX(-1 * speed);
                    }
                    else if (sprite.getPosition().getX() <= -characterPosition.getX() + characterSprite.getPosition().getX()) {
                        setX(1 * speed);
                    }
                    setY(0);
                }
                else if (xCrash && yCrash) {
                    setX(-direction[0] * speed);
                    setY(-direction[1] * speed);
                }
                walkable = true;
            } else {
                walkable = true;
            }
            if ((x + w) + direction[0] * speed > position.getX() + size.getX()
                || (y + h) + direction[1] * speed > position.getY() + size.getY()
                || x + direction[0] * speed < position.getX()
                || y + direction[1] * speed < position.getY()) {
                mode = 0;
            } 
        }

        // Patrolling
        if (mode == 0){
            if (GameMap.enemyCrash(sprite, direction[0] * speed, direction[1] * speed)
                || (x + w) + direction[0] * speed > position.getX() + size.getX()
                || (y + h) + direction[1] * speed > position.getY() + size.getY()
                || x + direction[0] * speed < position.getX()
                || y + direction[1] * speed < position.getY()) {
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
                walkable = true;
            }
        }

        if (walkable) {
            super.update(sprite);
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
