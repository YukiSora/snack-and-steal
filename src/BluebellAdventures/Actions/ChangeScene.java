package BluebellAdventures.Actions;

import javax.sound.sampled.Clip;

import Megumin.Actions.Action;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

public class ChangeScene extends Action {
	private Scene scene;
	private String audio;

	public ChangeScene(Scene scene, String audio) {
		this.scene = scene;
		this.audio = audio;
	}

	@Override
	public void update(Sprite sprite) {
		Director.getInstance().setScene(scene);
		AudioEngine.getInstance().stopAll();
		AudioEngine.getInstance().loop(audio, Clip.LOOP_CONTINUOUSLY);
		super.update(sprite);
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
}
