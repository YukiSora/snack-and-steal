package BluebellAdventures.Actions;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import Megumin.Actions.Action;
import Megumin.Actions.MoveTo;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class CharacterMoveTo extends MoveTo {
	private CopyOnWriteArrayList<Sprite> sprites;

	public CharacterMoveTo(int x, int y, CopyOnWriteArrayList<Sprite> sprites) {
		super(x, y);
		this.sprites = sprites;
	}

	@Override
	public void update(Sprite sprite) {
		sprite.checkCrash(sprites, new CrashSnack());
		super.update(sprite);
	}
}