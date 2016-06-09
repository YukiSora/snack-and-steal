package BluebellAdventures.Actions;

import Megumin.Actions.Action;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

public class Quit extends Action {
	@Override
	public void update(Sprite sprite) {
		System.exit(1);
		super.update(sprite);
	}
}
