package BluebellAdventures.Actions;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.Snack;
import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class ChaseCharacter extends Effect {
	private Action action;

	ChaseCharacter(Action action){
		this.action = action;
	}

	@Override
	public void update(Sprite sprite) {
		System.out.println("Chasing Mode: ON");
		((EnemyMove)action).setMode(1);
		((EnemyMove)action).setCharacterSprite(getSprite());

		// AudioEngine.getInstance().play("attacking");
		// Character player = (Character) getSprite();
		// int hp = player.getHp();

		// if (hp < 1){
		// 	System.out.println("Game Over Mate!");
		// } else {
		// 	System.out.println("Health: " + hp);
		// 	player.setHp(--hp);
		// 	GameMap.getInstance().setPosition(new Point (-2752, -2619));
		// }

		// Director.getInstance().getScene().getLayerByName("snack").removeSprite(getSprite());
		// ((Character)sprite).addSnackScore(((Snack)getSprite()).getScore());

		//System.out.println(((Character)sprite).getSnackScore());
	}
}
