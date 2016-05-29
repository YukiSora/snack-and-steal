package Megumin;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import Megumin.Actions.Action;
import Megumin.Nodes.Sprite;

public class Interact {
    private static Interact interact;
    private HashMap<Integer, ArrayList<Event>> events;

    private Interact() {
        events = new HashMap<Integer, ArrayList<Event>>();
    }

    public static Interact getInstance() {
        if (interact == null) {
            interact = new Interact();
        }

        return interact;
    }

    public void addEvent(Sprite sprite, Action action, int key) {
        if (events.containsKey(key)) {
            events.get(key).add(new Event(sprite, action));
        }
        else {
            events.put(key, new ArrayList<Event>());
            events.get(key).add(new Event(sprite, action));
        }
    }

    public void keyPressed(KeyEvent e) {
        if (events.containsKey(e.getKeyCode())) {
            for (Event event : events.get(e.getKeyCode())) {
                event.sprite.runAction(event.action);
            }
        }
    }

    class Event {
        public Sprite sprite;
        public Action action;
        Event(Sprite sprite, Action action) {
            this.sprite = sprite;
            this.action = action;
        }
    }
}
