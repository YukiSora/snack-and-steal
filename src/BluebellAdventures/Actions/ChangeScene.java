package BluebellAdventures.Actions;

import Megumin.Actions.Action;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

public class ChangeScene extends Action {
	private Scene scene;

	public ChangeScene(Scene scene) {
		this.scene = scene;
	}

	@Override
	public void update(Sprite sprite) {
		Director.getInstance().setScene(scene);
		super.update(sprite);
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
}
