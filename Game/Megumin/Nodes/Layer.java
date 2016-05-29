package Megumin.Nodes;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Layer {
    private ArrayList<Sprite> sprites;

    public Layer() {
        sprites = new ArrayList<>();
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    public ArrayList getSprites() {
        return sprites;
    }

    public void render(Graphics2D g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
    }
}
