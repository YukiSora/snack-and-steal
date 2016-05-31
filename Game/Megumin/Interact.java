package Megumin;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Megumin.Actions.Action;
import Megumin.Nodes.Sprite;

public class Interact {
    public static final int ON_KEY_PRESS = 0;
    public static final int ON_KEY_CLICK = 1;
    private static Interact interact;
    public static int tickId;
    private HashMap<Integer, HashMap<Integer, ArrayList<Event>>> events;
    private HashMap<Integer, Boolean> keyStatus;

    private Interact() {
        tickId = 0;
        events = new HashMap<Integer, HashMap<Integer, ArrayList<Event>>>();
        events.put(ON_KEY_PRESS, new HashMap<Integer, ArrayList<Event>>());
        events.put(ON_KEY_CLICK, new HashMap<Integer, ArrayList<Event>>());
        keyStatus = new HashMap<Integer, Boolean>();
    }

    public static Interact getInstance() {
        if (interact == null) {
            interact = new Interact();
        }

        return interact;
    }

    public void addEvent(int key, int method, Sprite sprite, Action action) {
        if (!events.get(method).containsKey(key)) {
            events.get(method).put(key, new ArrayList<Event>());
        }
        events.get(method).get(key).add(new Event(sprite, action));
    }

    public void removeEvent(int key, int method, Sprite sprite, Action action) {
        if (events.get(method).containsKey(key)) {
            events.get(method).get(key).remove(new Event(sprite, action));
        }
    }

    public void keyPressed(int key) {
        keyStatus.put(key, true);
    }

    public void keyReleased(int key) {
        keyStatus.put(key, false);
        if (events.get(ON_KEY_CLICK).containsKey(key)) {
            for (Event event : events.get(ON_KEY_CLICK).get(key)) {
                event.sprite.runAction(event.action);
            }
        }
    }

    public void update() {
        tickId = (tickId + 1) % 108000;
        for(Entry<Integer, Boolean> entry : keyStatus.entrySet()) {
            if (entry.getValue() && events.get(ON_KEY_PRESS).containsKey(entry.getKey())) {
                for (Event event : events.get(ON_KEY_PRESS).get(entry.getKey())) {
                    event.sprite.runAction(event.action);
                }
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

        @Override
        public boolean equals(Object o) {
            return ((Event)o).sprite.equals(sprite) && ((Event)o).action.equals(action);
        }
    }
}
