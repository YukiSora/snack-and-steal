package Megumin.Actions;

import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

public class Quit extends Action {
    public Quit() {
    }

    @Override
    public void update(Sprite sprite) {
        System.exit(1);
        super.update(sprite);
    }
}
