package BluebellAdventures.Actions;

import java.io.IOException;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class CrashFridge extends Effect {
    @Override
    public void update(Sprite sprite) {
        try {
            //Open fridge if it hasn't opened
            MovableObject fridge = (MovableObject)getSprite();
            if (!fridge.getOpen()) {
                //Unlock fridge if it hasn't unlocked
                if (fridge.getLock()) {
                    //Unlock fridge if character has enough key
                    Character player = (Character)sprite;
                    int key = player.getKey();
                    if (key > 0) {
                        player.setKey(key - 1);
                        fridge.setLock(false);
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
                //set crash area to 0
                fridge.setSize(new Point(0, 0));

                fridge.setOpen(true);
            }
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
