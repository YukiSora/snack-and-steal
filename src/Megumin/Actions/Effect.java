package Megumin.Actions;

import java.util.ArrayList;

import Megumin.Nodes.Sprite;

abstract public class Effect extends Action {
    private Sprite sprite;

    public Effect() {
    }

    public Effect(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update(Sprite sprite) {
        super.update(sprite);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
