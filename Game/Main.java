import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Megumin.Actions.Action;
import Megumin.Actions.Animate;
import Megumin.Actions.Effect;
import Megumin.Actions.MoveTo;
import Megumin.Actions.Infinite;
import Megumin.Actions.Interact;
import Megumin.Nodes.Director;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Main {
    public static void main(String[] args) {
        Director director = Director.getInstance();
        Infinite infinite = Infinite.getInstance();
        Interact interact = Interact.getInstance();

        //init window property
        director.setTitle("Poi");
        director.setResizable(false);
        director.setSize(1280, 720);
        director.setBackground(Color.white);
        director.setUndecorated(true);

        //init sprite
        Sprite nastu = null;
        Sprite machi = null;
        Sprite map = null;
        Sprite snack = null;
        try {
            nastu = new Sprite("resource/image/natsu1.png", new Point(100, 100));
            machi = new Sprite("resource/image/machi1.png", new Point(200, 200));
            map = new Sprite("resource/image/small_map.png");
            snack = new Sprite("resource/image/snack.png", new Point(200, 300));
        } catch (IOException e) {
            System.out.println(e);
        }

        //init layer
        Layer guardLayer = new Layer();
        guardLayer.addSprite(nastu);
        Layer ownPlayerLayer = new Layer();
        ownPlayerLayer.addSprite(machi);
        Layer snackLayer = new Layer();
        snackLayer.addSprite(snack);
        Layer mapLayer = new Layer();
        mapLayer.addSprite(map);

        //init scene
        Scene game = new Scene();
        game.addLayer(guardLayer);
        game.addLayer(ownPlayerLayer);
        game.addLayer(mapLayer, 0);
        game.addLayer(snackLayer, 1);
        //Scene menu = new Scene();
        //menu.addLayer(menu);

        //start
        director.setScene(game);
        director.start();

        //init key listener and action
        //Action moveW = new MoveTo(0, -5);
        Action moveW = new MoveTo(0, -5) {
            @Override
            public void update(Sprite sprite) {
                if (!sprite.checkCrash(snackLayer.getSprites(), new Effect() {
                    @Override
                    public void update(Sprite sprite) {
                        sprite.setSize(getSprite().getSize());
                    }
                })) {
                    sprite.getPosition().offset(getX(), getY());
                    for (Action action : getActions()) {
                        sprite.runAction(action);
                    }
                }
                else {
                    System.out.println("crash");
                }
            }
        };
        Action moveA = new MoveTo(-5, 0);
        Action moveS = new MoveTo(0, 5);
        Action moveD = new MoveTo(5, 0);
        Action animate = new Animate();
        try{
            ((Animate)animate).addImage(ImageIO.read(new File("resource/image/machi1.png")));
            ((Animate)animate).addImage(ImageIO.read(new File("resource/image/machi2.png")));
        } catch (IOException e) {
            System.out.println(e);
        }
        moveW.addAction(animate);
        moveA.addAction(animate);
        moveS.addAction(animate);
        moveD.addAction(animate);
        interact.addEvent(KeyEvent.VK_W, Interact.ON_KEY_PRESS, machi, moveW);
        interact.addEvent(KeyEvent.VK_A, Interact.ON_KEY_PRESS, machi, moveA);
        interact.addEvent(KeyEvent.VK_S, Interact.ON_KEY_PRESS, machi, moveS);
        interact.addEvent(KeyEvent.VK_D, Interact.ON_KEY_PRESS, machi, moveD);
        interact.addEvent(KeyEvent.VK_ESCAPE, Interact.ON_KEY_CLICK, map, new Action() {
            @Override
            public void update(Sprite sprite) {
                System.exit(1);
            }
        });

        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, map, new Action() {
            @Override
            public void update(Sprite sprite) {
                System.out.println("left mouse clicked");
            }
        });

        infinite.addEvent(nastu, new Action() {
            @Override
            public void update(Sprite sprite) {
                int x = sprite.getPosition().getX() + 5 > 1280 ? -1280 : 5;
                sprite.runAction(new MoveTo(x, 0));
            }
        });
    }
}
