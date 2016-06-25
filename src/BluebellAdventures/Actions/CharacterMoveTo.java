package BluebellAdventures.Actions;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import BluebellAdventures.Characters.GameMap;
import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Action;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class CharacterMoveTo extends Action {
    private CopyOnWriteArrayList<CopyOnWriteArrayList<Sprite>> sprites;
    private int x;
    private int y;

    public CharacterMoveTo(int x, int y) {
        this.x = x;
        this.y = y;
        sprites = new CopyOnWriteArrayList<>();
    }
    
    public void addSprites(CopyOnWriteArrayList<Sprite> sprites) {
        this.sprites.add(sprites);
    }

    @Override
    public void update(Sprite sprite) {
        if (!GameMap.characterCrash(sprite, x, y)) {
            sprite.checkCrash(sprites.get(0), new CrashSnack());
            Action fridgeLock = new CrashFridge();
            Action doorLock = new CrashDoor(new Point(2698, 1450));
            Action keyTouch = new CrashKey();
            
            sprite.checkCrash(sprites.get(1), fridgeLock);
            sprite.checkCrash(sprites.get(1), doorLock);
            sprite.checkCrash(sprites.get(2), keyTouch);
//            if ((((MovableObject)sprites.get(1).get(0)).getLock()) == true) {
                GameMap.getInstance().getPosition().offset(-x, -y);
//            }
        }
        super.update(sprite);
    }
}
