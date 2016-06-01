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
        for (Action action : getActions()) {
            sprite.runAction(action);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
