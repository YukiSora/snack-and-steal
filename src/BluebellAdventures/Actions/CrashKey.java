package BluebellAdventures.Actions;

import BluebellAdventures.Characters.Character;

import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class CrashKey extends Effect {
    @Override
    public void update(Sprite sprite) {
        AudioEngine.getInstance().play("key");

        Director.getInstance().getScene().getLayerByName("keys").removeSprite(getSprite());
        ((Character)sprite).addKey(1);
    }
}
