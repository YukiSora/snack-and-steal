package BluebellAdventures.Actions;

import Megumin.Actions.MoveTo;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

public class EnemyMove extends MoveTo {
	public static final int CLOCKWISE = 1;
	public static final int ANTICLOCKWISE = -1;
	private int speed;
	private int rotateDirection;

	public EnemyMove(int speed, int rotateDirection) {
		super(speed, 0);
		this.speed = speed;
		this.rotateDirection = rotateDirection;
	}

	@Override
	public void update(Sprite sprite) {
		int[] direction = sprite.getDirection();
		int x = sprite.getPosition().getX() + direction[0] * speed;
		int y = sprite.getPosition().getY() + direction[1] * speed;
		int w = sprite.getSize().getX();
		int h = sprite.getSize().getY();
		if (x + w > 1280 || x < 0) {
			direction[1] = rotateDirection * direction[0];
			direction[0] = 0;
			setX(direction[0] * speed);
			setY(direction[1] * speed);
		}
		if (y + h > 720 || y < 0) {
			direction[0] = -rotateDirection * direction[1];
			direction[1] = 0;
			setX(direction[0] * speed);
			setY(direction[1] * speed);
		}
		super.update(sprite);
	}
}
