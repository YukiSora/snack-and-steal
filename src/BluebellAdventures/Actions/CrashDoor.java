package BluebellAdventures.Actions;

import java.io.IOException;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Sprite;
import Megumin.Point;

//Check door upon touching
public class CrashDoor extends Effect {
    @Override
    public void update(Sprite sprite) {
        try {
            //Open door if it hasn't opened
            MovableObject door = (MovableObject)getSprite();
            if (!door.getOpen()) {
                //Unlock door if it hasn't unlocked
                if (door.getLock()) {
                    //Unlock door if character has enough key
                    Character player = (Character)sprite;
                    int key = player.getKey();
                    if (key > 0) {
                        player.setKey(key - 1);
                        door.setLock(false);
                    }
                    else {
                        return;
                    }
                }

                //change position base on the image size
                AudioEngine.getInstance().play("door");
                int oldWidth = door.getImage().getWidth();
                int oldHeight = door.getImage().getHeight();
                if (oldWidth > oldHeight) {
                    door.setImage("resource/image/door_open.png");
                }
                else {
                    door.setImage("resource/image/door_open2.png");
                }
                int newWidth = door.getImage().getWidth();
                int newHeight = door.getImage().getHeight();
                door.setPosition(door.getPosition().offset(oldWidth - newWidth, oldHeight - newHeight));
                //set crash area to 0
                door.setSize(new Point(0, 0));

                door.setOpen(true);
            }
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
