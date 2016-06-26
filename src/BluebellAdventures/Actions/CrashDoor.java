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

public class CrashDoor extends Effect {
    @Override
    public void update(Sprite sprite) {
/* if (((MovableObject) this.getSprite()).getLock() == true){
           System.out.println(Director.getInstance().getScene().getLayerByName("ownPlayerLayer").((Character)getSprite()).getName());
           if (int key = (Director.getInstance().getScene().getLayerByName("ownPlayerLayer").getSprite().getKey()) != 0){
                Director.getInstance().getScene().getLayerByName("ownPlayerLayer").getSprite().setKey(key - 1);
                ((MovableObject) this.getSprite()).setLock(false);
            }*/
            try {
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
            } catch (IOException e) {
                System.exit(1);
        }
    }
}
