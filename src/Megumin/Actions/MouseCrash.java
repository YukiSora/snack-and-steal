package Megumin.Actions;

import Megumin.Nodes.Sprite;

public class MouseCrash extends Action {
    private Action action;
    private int x;
    private int y;

    public MouseCrash(Action action) {
        this.action = action;
    }

    @Override
    public void update(Sprite sprite) {
        int x = sprite.getPosition().getX();
        int y = sprite.getPosition().getY();
        int w = sprite.getSize().getX();
        int h = sprite.getSize().getY();
        if (x <= this.x && this.x <= x + w && y <= this.y && this.y <= y + h) {
            sprite.runAction(action);
        }
        super.update(sprite);
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
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
