package Megumin.Actions;

import Megumin.Nodes.Sprite;

class Event {
    private Sprite sprite;
    private Action action;

    Event() {
    }

    Event(Sprite sprite, Action action) {
        this.sprite = sprite;
        this.action = action;
    }

    Event(Event event) {
        sprite = event.sprite;
        action = event.action;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        return ((Event)o).getSprite().equals(sprite) && ((Event)o).getAction().equals(action);
    }
}