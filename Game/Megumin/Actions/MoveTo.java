package Megumin.Actions;

import Megumin.Nodes.Sprite;

public class MoveTo extends Action {
    private int x;
    private int y;

    public MoveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(Sprite sprite) {
        sprite.getPosition().offset(x, y);
    }
}
