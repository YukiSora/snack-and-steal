package BluebellAdventures.Actions;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import BluebellAdventures.Characters.GameMap;
import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Action;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class CharacterMoveTo extends Action {

    private CopyOnWriteArrayList<CopyOnWriteArrayList<Sprite>> sprites;
    private int x;
    private int y;

    public CharacterMoveTo(int x, int y) {
        sprites = new CopyOnWriteArrayList<>();
        this.x = x;
        this.y = y;
    }
    
    public void addSprites(CopyOnWriteArrayList<Sprite> sprites){
        this.sprites.add(sprites);
    }
        
    @Override
    public void update(Sprite sprite) {
        if (!GameMap.mapCrash(sprite, x, y)) {
            sprite.checkCrash(sprites.get(0), new CrashSnack());
            Action lock = new CrashLock();
            ((CrashLock)lock).setSprite(sprites.get(1).get(0));
            sprite.checkCrash(sprites.get(1), lock);
            if ((((MovableObject)sprites.get(1).get(0)).getLock()) == true) {
                GameMap.getInstance().getPosition().offset(-x, -y);
            }
        }
        super.update(sprite);
    }
}
