package BluebellAdventures.Actions;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class CrashSnack extends Effect {
	@Override
	public void update(Sprite sprite) {
		Director.getInstance().getScene().getLayerByName("snack").removeSprite(getSprite());
	}
}
