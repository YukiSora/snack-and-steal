package Megumin.Actions;

import java.util.ArrayList;

import Megumin.Nodes.Sprite;

abstract public class Action {
    private ArrayList<Action> actions;

    public Action() {
        actions = new ArrayList<>();
    }

    abstract public void update(Sprite sprite);

    public void addAction(Action action) {
        actions.add(action);
    }

    public void removeAction(Action action) {
        actions.remove(action);
    }

    public ArrayList<Action> getActions() {
        return actions;
    }
}
