package Megumin.Actions;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Megumin.Nodes.Sprite;

public class Animate extends Action {
    private ArrayList<BufferedImage> images;
    private int index;
    private int tick;
    private int tickId;

    public Animate() {
        images = new ArrayList<>();
        index = 1;
        tick = 0;
        tickId = 0;
    }

    public void addImage(BufferedImage image) {
        images.add(image);
    }

    public void removeImage(BufferedImage image) {
        images.remove(image);
    }

    @Override
    public void update(Sprite sprite) {
        if (tickId != Interact.tickId) {
            tickId = Interact.tickId;
            if (++tick % 16 == 0) {
                index = (index + 1) % images.size();
                tick = 0;
            }
            sprite.setImage(images.get(index));
        }
    }
}
