package BluebellAdventures.Actions;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.GameMap;
import BluebellAdventures.Characters.Snack;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Sprite;

public class CrashCharacter extends Effect {
	private Action action;

    CrashCharacter(Action action){
        this.action = action;
    }

    @Override
    public void update(Sprite sprite) {
        AudioEngine.getInstance().play("attacking");
        Character player = (Character) getSprite();
        int hp = player.getHp();

        ((EnemyMove)action).setMode(0);
        ((EnemyMove)action).setCharacterSprite(getSprite());

        player.setHp(--hp);
        System.out.println("Health: " + hp);

        // if (hp < 1) {
        //     System.out.println("Game Over Mate!");
        // } else {
        // 	// System.out.println("Chasing Mode: OFF");
	       //  ((EnemyMove)action).setMode(0);
	       //  ((EnemyMove)action).setCharacterSprite(getSprite());

        //     player.setHp(--hp);
        //     System.out.println("Health: " + hp);
        //     GameMap.getInstance().setPosition(-2752, -2619);
        // }
    }
}