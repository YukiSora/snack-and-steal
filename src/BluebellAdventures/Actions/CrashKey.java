package BluebellAdventures.Actions;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.Snack;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class CrashKey extends Effect {
    @Override
    public void update(Sprite sprite) {
        AudioEngine.getInstance().play("clink");
        Director.getInstance().getScene().getLayerByName("keys").removeSprite(getSprite());
        ((Character)sprite).addKey(1);
        System.out.println("Keys: " + ((Character)sprite).getKey());
    }
}
