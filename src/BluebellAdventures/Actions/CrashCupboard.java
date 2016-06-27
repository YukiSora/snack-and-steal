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

public class CrashCupboard extends Effect {
    @Override
    public void update(Sprite sprite) {
        //Open cupboards
        try {
            Sprite cupboard = Director.getInstance().getScene().getSpriteByName("cupboard");
            AudioEngine.getInstance().play("fridge");
            cupboard.setImage("resource/image/cupboard_open.png");
            cupboard.setPosition(cupboard.getPosition().offset(-147, 0));

        } catch (IOException e) {
            System.exit(1);
        }
    }
}
