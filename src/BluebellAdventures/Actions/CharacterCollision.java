package BluebellAdventures.Actions;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Sprite;

public class CharacterCollision extends Effect {
    private Action action;

    CharacterCollision(Action action) {
        this.action = action;
    }

    @Override
    public void update(Sprite sprite) {
        AudioEngine.getInstance().stop("nervous");
        AudioEngine.getInstance().play("attacking");
        AudioEngine.getInstance().play("main");

        Character player = (Character) getSprite();
        // Minus Player's Health
        player.setHp(player.getHp() - 1);

        // Set Enemy to Patrol
        ((EnemyMove)action).setMode(0);
        ((EnemyMove)action).setCharacterSprite(null);

        // Respawn Player's Position
        GameMap.getInstance().setPosition(-705, -2600);

        if (player.getHp() == 0) {
            new GameOver(false).update(getSprite());
        }
    }
}
