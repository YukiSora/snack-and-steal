package Megumin.Actions;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import Megumin.Nodes.Sprite;

public class Infinite {
    private static Infinite infinite;
    private CopyOnWriteArrayList<Event> events;

    public Infinite() {
        events = new CopyOnWriteArrayList<>();
    }

    public static Infinite getInstance() {
        if (infinite == null) {
            infinite = new Infinite();
        }

        return infinite;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addEvent(Sprite sprite, Action action) {
        events.add(new Event(sprite, action));
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void update() {
        Iterator it = events.iterator();
        while (it.hasNext()) {
            Event event = (Event)it.next();
            event.getSprite().runAction(event.getAction());
        }
    }
}
