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
    private int coordinate;
    private int direction;


    public CharacterMoveTo(int x, int y) throws IOException{
        this.x = x;
        this.y = y;
        coordinate = x == 0 ? 1 : 0;
        direction = x > 0 || y > 0 ? 1 : -1;
        sprites = new CopyOnWriteArrayList<>();
    }

    public void addSprites(CopyOnWriteArrayList<Sprite> sprites) {
        this.sprites.add(sprites);
    }

    @Override
    public void update(Sprite sprite) {
        sprite.getDirection()[coordinate] = direction;
        if (!GameMap.characterCollision(sprite, x, y)) {
            Action snackpick = new SnackCollision();
            Action fridgeLock = new FridgeCollision();
            Action doorLock = new DoorCollision();
            Action keyTouch = new KeyCollision();
            Action cupboardLock = new CupboardCollision();
            Action finishLine = new GameOver(true);

            sprite.checkCollision(sprites.get(0), snackpick);
            sprite.checkCollision(sprites.get(3), cupboardLock);
            sprite.checkCollision(sprites.get(4), keyTouch);
            sprite.checkCollision(sprites.get(5), finishLine);

            sprite.setPosition(sprite.getPosition().offset(x, y));
            if (!sprite.checkCollision(sprites.get(1), fridgeLock) &&
                !sprite.checkCollision(sprites.get(2), doorLock)) {
                GameMap.getInstance().getPosition().offset(-x, -y);
            }
            sprite.setPosition(sprite.getPosition().offset(-x, -y));
        }

        super.update(sprite);
    }
}
