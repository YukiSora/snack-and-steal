package Megumin.Nodes;

import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class Layer {
    private CopyOnWriteArrayList<Sprite> sprites;

    public Layer() {
        sprites = new CopyOnWriteArrayList<Sprite>();
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    public void render(Graphics2D g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
    }

    public CopyOnWriteArrayList<Sprite> getSprites() {
        return sprites;
    }
}
