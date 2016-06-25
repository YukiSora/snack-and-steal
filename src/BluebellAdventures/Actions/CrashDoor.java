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
    private Point pt;
    
    CrashDoor(Point pt){
        this.pt = pt;
    }
    
    @Override
    public void update(Sprite sprite) {
        AudioEngine.getInstance().play("fridge");
        try {
            Sprite door = Director.getInstance().getScene().getSpriteByName("door");
            door.setImage("resource/image/door_open.png");
            door.setPosition(new Point(2698, 1450));
        }
        catch(IOException e){
            System.exit(1);
        }
        ((MovableObject)this.getSprite()).setLock(false);
    }
}
