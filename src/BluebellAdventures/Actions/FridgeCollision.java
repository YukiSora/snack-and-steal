package BluebellAdventures.Actions;

import java.io.IOException;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class FridgeCollision extends Effect {
    @Override
    public void update(Sprite sprite) {
        try {
            //Open fridge if it hasn't opened
            MovableObject fridge = (MovableObject)getSprite();
            if (!fridge.getOpened()) {
                //Unlock fridge if it hasn't unlocked
                if (fridge.getLocked()) {
                    //Unlock fridge if character has enough key
                    Character player = (Character)sprite;
                    int key = player.getKey();
                    if (key > 0) {
                        player.setKey(key - 1);
                        fridge.setLocked(false);
                    }
                    else {
                        return;
                    }
                }

                //change position base on the image size
                AudioEngine.getInstance().play("fridge");
                int oldWidth = fridge.getImage().getWidth();
                int oldHeight = fridge.getImage().getHeight();
                fridge.setImage("resource/image/fridge_open.png");
                int newWidth = fridge.getImage().getWidth();
                int newHeight = fridge.getImage().getHeight();
                fridge.setPosition(fridge.getPosition().offset(oldWidth - newWidth, oldHeight - newHeight));
                //set collision area to 0
                fridge.setSize(new Point(0, 0));

                fridge.setOpened(true);
            }
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
