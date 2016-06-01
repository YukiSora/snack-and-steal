package Megumin.Nodes;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class Scene {
    private LinkedList<Layer> layers;

    public Scene() {
        layers = new LinkedList<>();
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public void addLayer(Layer layer, int index) {
        layers.add(index, layer);
    }

    public void removeLayer(Layer layer) {
        layers.remove(layer);
    }

    public Sprite getSpriteByName(String name) {
        for (Layer layer : layers) {
            for (Sprite sprite : layer.getSprites()) {
                if (sprite.getName().equals(name)) {
                    return sprite;
                }
            }
        }

        return null;
    }

    public void render(Graphics2D g) {
        for (Layer layer : layers) {
            layer.render(g);
        }
    }
}
