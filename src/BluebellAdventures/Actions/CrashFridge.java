package BluebellAdventures.Actions;

import java.io.IOException;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class CrashFridge extends Effect {
    @Override
    public void update(Sprite sprite) {
        AudioEngine.getInstance().play("fridge");
        try {
            Sprite fridge = Director.getInstance().getScene().getSpriteByName("fridge");
            fridge.setImage("resource/image/fridge_open.png");
            fridge.setPosition(new Point(3387, 2420));
        }
        catch(IOException e) {
            System.exit(1);
        }
        ((MovableObject)this.getSprite()).setLock(false);
    }
}
