package BluebellAdventures.Actions;

import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.MoveTo;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class EnemyMove extends MoveTo {
	public static final int CLOCKWISE = 1;
	public static final int ANTICLOCKWISE = -1;
	private Point position;
	private Point size;
	private int speed;
	private int rotateDirection;

	public EnemyMove(int speed, int rotateDirection, Point position, Point size) {
		super(speed, 0);
		this.speed = speed;
		this.rotateDirection = rotateDirection;
		this.position = position;
		this.size = size;
	}

	@Override
	public void update(Sprite sprite) {
		int[] direction = sprite.getDirection();
		int x = sprite.getPosition().getX() + direction[0] * speed;
		int y = sprite.getPosition().getY() + direction[1] * speed;
		int w = sprite.getSize().getX();
		int h = sprite.getSize().getY();

		System.out.println("x: " + x + "&y: " + y);
		if (GameMap.mapCrash(sprite, direction[0] * speed, direction[1] * speed)) {
			direction[0] = -direction[0];
			setX(direction[0] * speed);
			// 	setX(direction[0] * speed);
			//  if (x + w > position.getX() + size.getX() || x < position.getX()) {
			// 	direction[1] = rotateDirection * direction[0];
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

		super.update(sprite);
	}
}
