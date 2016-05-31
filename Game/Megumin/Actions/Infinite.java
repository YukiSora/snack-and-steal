package Megumin.Actions;

import java.util.ArrayList;

import Megumin.Nodes.Sprite;

public class Infinite {
    private static Infinite infinite;
    private ArrayList<Event> events;

    public Infinite() {
        events = new ArrayList<>();
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
        for (Event event : events) {
            event.getSprite().runAction(event.getAction());
        }
    }
}
