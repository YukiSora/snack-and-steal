package BluebellAdventures.Actions;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.Snack;
import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class CrashLock extends Effect {

    @Override
    public void update(Sprite sprite) {
        AudioEngine.getInstance().play("fridge");
        ((MovableObject)this.getSprite()).setLock(false);
    }
}
