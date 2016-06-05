package Megumin.Nodes;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Layer {
    private CopyOnWriteArrayList<Sprite> sprites;
    private String name;

    public Layer() {
        sprites = new CopyOnWriteArrayList<Sprite>();
        name = "";
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    public void render(Graphics2D g) {
        Iterator it = sprites.iterator();
        while (it.hasNext()) {
            Sprite sprite = (Sprite)it.next();
            sprite.render(g);
        }
    }

    public CopyOnWriteArrayList<Sprite> getSprites() {
        return sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
