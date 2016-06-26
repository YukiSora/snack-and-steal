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

//Check door upon touching
public class CrashDoor extends Effect {
    @Override
    public void update(Sprite sprite) {
        //Unlock door if player has key
        if (((MovableObject) this.getSprite()).getLock() == true) {
            Character player = ((Character) Director.getInstance().getScene().getSpriteByName("player"));
            int key = player.getKey();
            if (key != 0) {
                player.setKey(key - 1);
                ((MovableObject) this.getSprite()).setLock(false);
            }
        }
        //Open door if it's unlocked
        try {
            if (((MovableObject) this.getSprite()).getLock() == false) {
                Sprite door = getSprite();
                if (door.getImage().getHeight() == 53) {
                    AudioEngine.getInstance().play("door");
                    door.setImage("resource/image/door_open.png");
                    door.setPosition(door.getPosition().offset(-15, -200));
                } else if (door.getImage().getWidth() == 50) {
                    AudioEngine.getInstance().play("door");
                    door.setImage("resource/image/door_open2.png");
                    door.setPosition(door.getPosition().offset(-200, 200));
                }
            }
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
