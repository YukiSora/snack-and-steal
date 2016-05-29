import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.io.IOException;

import Megumin.Actions.Action;
import Megumin.Actions.MoveTo;
import Megumin.Nodes.Director;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Interact;

public class Main {
    public static void main(String[] args) {
        //init window property
        Director director = Director.getInstance();
        director.setTitle("Poi");
        director.setResizable(false);
        director.setSize(1280, 720);
        director.setBackground(Color.white);
        director.setUndecorated(true);

        //init sprite
        Sprite nastu = null;
        Sprite machi = null;
        try {
            nastu = new Sprite("resource/image/natsu.png");
            machi = new Sprite("resource/image/machi.png");
        } catch (IOException e) {
            System.out.println(e);
        }

        //init layer
        Layer character = new Layer();
        character.addSprite(machi);
        Layer guard = new Layer();
        guard.addSprite(nastu);

        //init scene
        Scene scene1 = new Scene();
        scene1.addLayer(character);
        Scene scene2 = new Scene();
        scene2.addLayer(guard);
        director.setScene(scene1);

        //start
        director.start();

        //init key listener and action
        Interact interact = Interact.getInstance();
        interact.addEvent(KeyEvent.VK_W, Interact.ON_KEY_PRESS, machi, new MoveTo(0, -5));
        interact.addEvent(KeyEvent.VK_A, Interact.ON_KEY_PRESS, machi, new MoveTo(-5, 0));
        interact.addEvent(KeyEvent.VK_S, Interact.ON_KEY_PRESS, machi, new MoveTo(0, 5));
        interact.addEvent(KeyEvent.VK_D, Interact.ON_KEY_PRESS, machi, new MoveTo(5, 0));
    }
}
