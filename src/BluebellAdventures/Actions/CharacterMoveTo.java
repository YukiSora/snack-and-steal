package BluebellAdventures.Actions;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.Action;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class CharacterMoveTo extends Action{
	private CopyOnWriteArrayList<Sprite> sprites;
	private int x;
	private int y;

	public CharacterMoveTo(int x, int y, CopyOnWriteArrayList<Sprite> sprites) {
		this.sprites = sprites;
		this.x = x;
		this.y = y;
	}

	@Override
	public void update(Sprite sprite) {
		if (!GameMap.mapCrash(sprite, x, y)) {
			sprite.checkCrash(sprites, new CrashSnack());
			GameMap.getInstance().getPosition().offset(-x, -y);
		}
		super.update(sprite);
	}
}