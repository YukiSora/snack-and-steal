package Megumin.Actions;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import Megumin.Actions.MouseCrash;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class Interact {
    public static final int ON_KEY_PRESS = 0;
    public static final int ON_KEY_CLICK = 1;
    public static final int ON_MOUSE_CLICK = 1;
    private static Interact interact;
    public static int tickId;
    private HashMap<Integer, HashMap<Integer, CopyOnWriteArrayList<Event>>> events;
    private HashMap<Integer, Boolean> keyStatus;

    private Interact() {
        tickId = 0;
        events = new HashMap<Integer, HashMap<Integer, CopyOnWriteArrayList<Event>>>();
        events.put(ON_KEY_PRESS, new HashMap<Integer, CopyOnWriteArrayList<Event>>());
        events.put(ON_KEY_CLICK, new HashMap<Integer, CopyOnWriteArrayList<Event>>());
        events.put(ON_MOUSE_CLICK, new HashMap<Integer, CopyOnWriteArrayList<Event>>());
        keyStatus = new HashMap<Integer, Boolean>();
    }

    public static Interact getInstance() {
        if (interact == null) {
            interact = new Interact();
        }

        return interact;
    }

    public void addEvent(int key, int method, Sprite sprite, Action action, String sceneName) {
        if (!events.get(method).containsKey(key)) {
            events.get(method).put(key, new CopyOnWriteArrayList<Event>());
        }
        events.get(method).get(key).add(new Event(sprite, action, sceneName));
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
            Iterator it = events.get(ON_KEY_CLICK).get(key).iterator();
            while (it.hasNext()) {
                Event event = (Event)it.next();
                if (event.getSceneName().equals("") || event.getSceneName().equals(Director.getInstance().getScene().getName())) {
                    event.getSprite().runAction(event.getAction());
                }
            }
        }
    }

    public void mouseClicked(int x, int y) {
        if (events.get(ON_MOUSE_CLICK).containsKey(MouseEvent.BUTTON1)) {
            Iterator it = events.get(ON_MOUSE_CLICK).get(MouseEvent.BUTTON1).iterator();
            while (it.hasNext()) {
                Event event = (Event)it.next();
                if (event.getSceneName().equals("") || event.getSceneName().equals(Director.getInstance().getScene().getName())) {
                    ((MouseCrash)event.getAction()).setX(x);
                    ((MouseCrash)event.getAction()).setY(y);
                    event.getSprite().runAction(event.getAction());
                }
            }
        }
    }

    public void update() {
        tickId = (tickId + 1) % 108000;
        for(Entry<Integer, Boolean> entry : keyStatus.entrySet()) {
            if (entry.getValue() && events.get(ON_KEY_PRESS).containsKey(entry.getKey())) {
                Iterator it = events.get(ON_KEY_PRESS).get(entry.getKey()).iterator();
                while (it.hasNext()) {
                    Event event = (Event)it.next();
                    if (event.getSceneName().equals("") || event.getSceneName().equals(Director.getInstance().getScene().getName())) {
                        event.getSprite().runAction(event.getAction());
                    }
                }
            }
        }
    }
}
