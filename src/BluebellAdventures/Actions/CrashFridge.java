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
        //Unlock fridge if player has key
        if (((MovableObject) this.getSprite()).getLock() == true) {
            Character player = ((Character) Director.getInstance().getScene().getSpriteByName("player"));
            int key = player.getKey();
            if (key != 0) {
                player.setKey(key - 1);
                ((MovableObject) this.getSprite()).setLock(false);
            }
        }
        //Open fridge if unlocked
        try {
            if (((MovableObject) this.getSprite()).getLock() == false) {
                Sprite fridge = Director.getInstance().getScene().getSpriteByName("fridge");
                AudioEngine.getInstance().play("fridge");
                fridge.setImage("resource/image/fridge_open.png");
                fridge.setPosition(new Point(3387, 2420));
            }
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
