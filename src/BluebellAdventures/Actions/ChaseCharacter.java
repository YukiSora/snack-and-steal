package BluebellAdventures.Actions;

import BluebellAdventures.Characters.Snack;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Sprite;

public class ChaseCharacter extends Effect {
    private Action action;

    ChaseCharacter(Action action){
        this.action = action;
    }

    @Override
    public void update(Sprite sprite) {
        // Set Enemy to Chase
        ((EnemyMove)action).setMode(1);
        ((EnemyMove)action).setCharacterSprite(getSprite());
    }
}
