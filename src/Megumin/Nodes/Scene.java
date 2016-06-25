package Megumin.Nodes;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {
    private CopyOnWriteArrayList<Layer> layers;
    private String name;

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

    public void removeSpriteByName(String name) {
        Iterator it = layers.iterator();
        while (it.hasNext()) {
            ((Layer)it.next()).removeSpriteByName(name);
        }
    }

    public Sprite getSpriteByName(String name) {
        Iterator it = layers.iterator();
        while (it.hasNext()) {
            Sprite sprite = ((Layer)it.next()).getSpriteByName(name);
            if (sprite != null) {
                return sprite;
            }
        }

        return null;
    }

    public Layer getLayerByName(String name) {
        Iterator layerIt = layers.iterator();
        while (layerIt.hasNext()) {
            Layer layer = (Layer)layerIt.next();
            if (layer.getName().equals(name)) {
                return layer;
            }
        }

        return null;
    }

    public void render(Graphics2D g) {
        Iterator it = layers.iterator();
        while (it.hasNext()) {
            ((Layer)it.next()).render(g);
        }
    }

    public CopyOnWriteArrayList<Layer> getLayers() {
        return layers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
