package BluebellAdventures.Actions;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;

import BluebellAdventures.Characters.GameMap;
import BluebellAdventures.Characters.Character;

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

	// Mode 0 - Idle
	// Mode 1 - Pursuit

	public EnemyMove(int speed, Point position, Point size) {
		super(speed, 0);
		sprites = new CopyOnWriteArrayList<>();
		this.speed = speed;
		this.position = position;
		this.size = size;
		mode = 0;
	}

	public int getMode(){
		return mode;
	}

	public void setMode(int mode){
		this.mode = mode;
	}

	public Sprite getCharacterSprite(){
		return characterSprite;
	}

	public void setCharacterSprite(Sprite characterSprite){
		this.characterSprite = characterSprite;
	}

	public void addSprites(CopyOnWriteArrayList<Sprite> sprites){
		this.sprites.add(sprites);
	}

	@Override
	public void update(Sprite sprite) {
		Sprite chaseSprite = new Sprite();

		int[] direction = sprite.getDirection();
		int x = sprite.getPosition().getX() + direction[0] * speed;
		int y = sprite.getPosition().getY() + direction[1] * speed;
		int w = sprite.getSize().getX();
		int h = sprite.getSize().getY();

		int[] dir = {-1, 0, 1};
		Random rand = new Random();

		if (mode == 1) {
			Point characterPosition = GameMap.getInstance().getPosition();
			if (sprite.getPosition().getX() > -characterPosition.getX() + 600) {
				direction[0] = -1;
			} else {
				direction[0] = 1;
			}
			
			if (sprite.getPosition().getY() > -characterPosition.getY() + 200) {
				direction[1] = -1;
			} else {
				direction[1] = 1;
			}

			setX(direction[0] * speed);
			setY(direction[1] * speed);
			// Character player = (Character) getSprite();

			System.out.println(GameMap.getInstance().getPosition());
		}

		// if (GameMap.enemyCrash(sprite, direction[0] * speed, direction[1] * speed) || (x + w) > position.getX() + size.getX() || (y + h) > position.getY() + size.getY() || x < position.getX() || y < position.getY()) {
		// 	if (mode == 0){
		// 		direction[0] = dir[rand.nextInt(3)];
		// 		direction[1] = dir[rand.nextInt(3)];

		// 		while (direction[0] == 0 && direction[1] == 0) {
		// 			direction[0] = dir[rand.nextInt(3)];
		// 			direction[1] = dir[rand.nextInt(3)];
		// 		}

		// 		setX(direction[0] * speed);
		// 		setY(direction[1] * speed);
		// 	}
		// } else {
			chaseSprite.setPosition(position);
			chaseSprite.setSize(size);

			chaseSprite.checkCrash(sprites.get(0), new ChaseCharacter(this));
			sprite.checkCrash(sprites.get(0), new CrashCharacter());

			super.update(sprite);
		//}
	}
}
