package BluebellAdventures.Actions;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;
import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.MoveTo;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class EnemyMove extends MoveTo {
	public static final int CLOCKWISE = 1;
	public static final int ANTICLOCKWISE = -1;
	private CopyOnWriteArrayList<CopyOnWriteArrayList<Sprite>> sprites;
	private Point position;
	private Point size;
	private int speed;
	private int rotateDirection;

	public EnemyMove(int speed, int rotateDirection, Point position, Point size) {
		super(speed, 0);
		sprites = new CopyOnWriteArrayList<>();
		this.speed = speed;
		this.rotateDirection = rotateDirection;
		this.position = position;
		this.size = size;
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

		int[] dir = {-1, 0, 1};
		Random rand = new Random();
		if (GameMap.enemyCrash(sprite, direction[0] * speed, direction[1] * speed)) {
			direction[0] = dir[rand.nextInt(3)];
			direction[1] = dir[rand.nextInt(3)];
			while (direction[0] == 0 && direction[1] == 0) {
				direction[0] = dir[rand.nextInt(3)];
				direction[1] = dir[rand.nextInt(3)];
			}
			setX(direction[0] * speed);
			setY(direction[1] * speed);

			// setX(direction[0] * speed);

			//  if (x + w > position.getX() + size.getX() || x < position.getX()) {
			// 	directiossssssn[1] = rotateDirection * direction[0];
			// 	direction[0] = 0;
			// 	setX(direction[0] * speed);
			// 	setY(direction[1] * speed);
			// }

			// if (y + h > position.getY() + size.getY() || y < position.getY()) {
			// 	direction[0] = -rotateDirection * direction[1];
			// 	direction[1] = 0;
			// 	setX(direction[0] * speed);
			// 	setY(direction[1] * speed);
			// }
		}

		sprite.checkCrash(sprites.get(0), new CrashCharacter());

		super.update(sprite);
	}
}
