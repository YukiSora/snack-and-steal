package BluebellAdventures.Actions;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;
import Megumin.Point;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class CrashDoor extends Effect {
    private Point pt;
    
    CrashDoor(Point pt){
        this.pt = pt;
    }
    
    @Override
    public void update(Sprite sprite) {
        AudioEngine.getInstance().play("fridge");
        try{
        Director.getInstance().getScene().getLayerByName("locks").getSpriteByName("door").setImage(ImageIO.read(new File("resource/image/door_open.png")));
        Director.getInstance().getScene().getLayerByName("locks").getSpriteByName("door").setPosition(new Point(2698, 1450));
        }
        catch(IOException e){
            System.exit(1);
        }
        ((MovableObject)this.getSprite()).setLock(false);
        
    }
}
