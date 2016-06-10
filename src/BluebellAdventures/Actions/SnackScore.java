package BluebellAdventures.Actions;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Actions.MoveTo;
import Megumin.Nodes.Sprite;
import Megumin.Nodes.Director;



public class SnackScore extends MoveTo{

	private CopyOnWriteArrayList<Sprite> sprites;

	public SnackScore(int x, int y, CopyOnWriteArrayList<Sprite> sprites){
		super(x, y);
		this.sprites = sprites;
	}

	@Override
	public void update(Sprite sprite) {
		if (!sprite.checkCrash(sprites, new Effect() {
			@Override
			public void update(Sprite sprite) {
				// sprite.setSize(getSprite().getSize());
				Director.getInstance().getScene().getLayerByName("snack").removeSprite(getSprite());
			}
		})) {
			sprite.getPosition().offset(getX(), getY());
			Iterator it = getActions().iterator();
			while (it.hasNext()) {
				Action action = (Action) it.next();
				sprite.runAction(action);
			}
		} else {
			System.out.println("crash");
		}
	}
}