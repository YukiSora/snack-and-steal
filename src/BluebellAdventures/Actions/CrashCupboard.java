package BluebellAdventures.Actions;

import java.io.IOException;

import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class CrashCupboard extends Effect {
    @Override
    public void update(Sprite sprite) {
        try {
            //Open cupboard if hasn't opened
            MovableObject cupboard = (MovableObject)getSprite();
            if (!cupboard.getOpened()) {
                AudioEngine.getInstance().play("fridge");

                //change position base on the image size
                int oldWidth = cupboard.getImage().getWidth();
                int oldHeight = cupboard.getImage().getHeight();
                if(oldWidth == 182) {
                    cupboard.setImage("resource/image/cupboard_open.png");
                }
                else if (oldWidth == 363){
                    cupboard.setImage("resource/image/closet_open.png");
                }
                else if (oldWidth == 165){
                    cupboard.setImage("resource/image/cooker_open.png");
                }
                int newWidth = cupboard.getImage().getWidth();
                int newHeight = cupboard.getImage().getHeight();
                cupboard.setPosition(cupboard.getPosition().offset(oldWidth - newWidth, oldHeight - newHeight));
                cupboard.setOpened(true);
            }
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
