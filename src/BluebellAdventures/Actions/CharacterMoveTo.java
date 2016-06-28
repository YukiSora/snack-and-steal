package BluebellAdventures.Actions;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import BluebellAdventures.Characters.GameMap;

import Megumin.Actions.Action;
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
            Action snackpick = new CrashSnack();
            Action fridgeLock = new CrashFridge();
            Action doorLock = new CrashDoor();
            Action keyTouch = new CrashKey();
            Action cupboardLock = new CrashCupboard();
            Action finishLine = new GameOver(true);

            sprite.checkCrash(sprites.get(0), snackpick);
            sprite.checkCrash(sprites.get(3), cupboardLock);
            sprite.checkCrash(sprites.get(4), keyTouch);
            sprite.checkCrash(sprites.get(5), finishLine);
            
            sprite.setPosition(sprite.getPosition().offset(x, y));
            if (!sprite.checkCrash(sprites.get(1), fridgeLock) &&
                !sprite.checkCrash(sprites.get(2), doorLock)) {
                GameMap.getInstance().getPosition().offset(-x, -y);
            }
            sprite.setPosition(sprite.getPosition().offset(-x, -y));
        }

        super.update(sprite);
    }
}
