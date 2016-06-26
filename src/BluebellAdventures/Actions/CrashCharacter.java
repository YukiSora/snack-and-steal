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

		if (hp < 2) {
            // Trigger Game Over
            System.out.println("Game Over Mate!");
            GameMap.getInstance().setPosition(-3105, -2650);
        } else {
            // Set Enemy to Patrol
            ((EnemyMove)action).setMode(0);
            ((EnemyMove)action).setCharacterSprite(getSprite());

            // Minus Player's Health
            player.setHp(--hp);
            
            // Respawn Player's Position
            GameMap.getInstance().setPosition(-705, -2600);
        }
    }
}