package BluebellAdventures.Actions;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.IOException;

import BluebellAdventures.Characters.Enemy;
import BluebellAdventures.Characters.GameMap;
import BluebellAdventures.Characters.MovableObject;
import BluebellAdventures.Characters.Character;

import Megumin.Actions.Action;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class CharacterMoveTo extends Action {
    private CopyOnWriteArrayList<CopyOnWriteArrayList<Sprite>> sprites;
    private int x;
    private int y;

    public CharacterMoveTo(int x, int y) throws IOException{
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
            Action doorLock = new CrashDoor();
            Action keyTouch = new CrashKey();
            Action finishLine = new GameOver();
            
            sprite.checkCrash(sprites.get(1), fridgeLock);
            sprite.checkCrash(sprites.get(2), doorLock);
            sprite.checkCrash(sprites.get(3), keyTouch);
            sprite.checkCrash(sprites.get(4), finishLine);
            //if ((((MovableObject)sprites.get(2).get(0)).getLock()) == true) {
                GameMap.getInstance().getPosition().offset(-x, -y);
            //}
        }

        super.update(sprite);
    }
}
