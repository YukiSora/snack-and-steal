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
        Director director = Director.getInstance();
        director.setTitle("Poi");
        director.setResizable(false);
        director.setSize(1280, 720);
        director.setBackground(Color.white);
        director.setUndecorated(true);

        Sprite nastu = null;
        Sprite machi = null;
        try {
            nastu = new Sprite("resource/image/natsu.png");
            machi = new Sprite("resource/image/machi.png");
        } catch (IOException e) {
            System.out.println(e);
        }

        Layer character = new Layer();
        character.addSprite(machi);
        Layer guard = new Layer();
        guard.addSprite(nastu);

        Scene game1 = new Scene();
        game1.addLayer(guard);
        //Scene game2 = new Scene();
        //game2.addLayer(character);
        director.setScene(game1);

        director.start();

        Action w = new MoveTo(0, -5);
        Action a = new MoveTo(-5, 0);
        Action s = new MoveTo(0, 5);
        Action d = new MoveTo(5, 0);
        Interact interact = Interact.getInstance();
        interact.addEvent(nastu, w, KeyEvent.VK_W);
        interact.addEvent(nastu, a, KeyEvent.VK_A);
        interact.addEvent(nastu, s, KeyEvent.VK_S);
        interact.addEvent(nastu, d, KeyEvent.VK_D);
        /*
        while (true) {
            java.util.Scanner in = new java.util.Scanner(System.in);
            in.nextLine();
            nastu.runAction(action);
        }
        */
    }
}
