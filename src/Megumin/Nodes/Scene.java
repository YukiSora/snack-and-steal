package Megumin.Nodes;

import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {
    private CopyOnWriteArrayList<Layer> layers;

    public Scene() {
        layers = new CopyOnWriteArrayList<Layer>();
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

    public CopyOnWriteArrayList<Layer> getLayers() {
        return layers;
    }
}
