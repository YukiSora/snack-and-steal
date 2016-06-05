package Megumin.Actions;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import Megumin.Nodes.Sprite;

abstract public class Action {
    private CopyOnWriteArrayList<Action> actions;

    public Action() {
        actions = new CopyOnWriteArrayList<>();
    }

    public void update(Sprite sprite) {
        Iterator it = actions.iterator();
        while (it.hasNext()) {
            sprite.runAction((Action)it.next());
        }
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void removeAction(Action action) {
        actions.remove(action);
    }

    public CopyOnWriteArrayList<Action> getActions() {
        return actions;
    }
}
