package BluebellAdventures.Actions;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.Snack;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class CrashSnack extends Effect {
	@Override
	public void update(Sprite sprite) {
		AudioEngine.getInstance().play("eating");
		Director.getInstance().getScene().getLayerByName("snack").removeSprite(getSprite());
		((Character)sprite).addSnackScore(((Snack)getSprite()).getScore());
		System.out.println(((Character)sprite).getSnackScore());
	}
}
