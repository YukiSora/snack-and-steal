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

    public void removeLayer(Layer layer) {
        layers.remove(layer);
    }

    public void render(Graphics2D g) {
        for (Layer layer : layers) {
            layer.render(g);
        }
    }
}
