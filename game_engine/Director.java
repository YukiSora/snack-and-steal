import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Director extends Frame implements Runnable {
    private Image imageBuffer;
    private Graphics imageGraphics;
    private Sprite map;
    private ArrayList<Sprite> snacks;
    private ArrayList<Sprite> characters;
    private Thread thread;

    public Director() {
        //windows init
        setTitle("Poi");
        setResizable(false);
        setSize(1280, 720);
        setBackground(Color.black);
        setUndecorated(true);
        Image icon = null;
        try {
            icon = ImageIO.read(new File("resource/image/icon.gif"));
        } catch (IOException e) {
            System.out.println(e);
        }
        setIconImage(icon);
        setVisible(true);

        //open thread
        thread = new Thread(this);

        //variable init
        characters = new ArrayList<>(10);
        snacks = new ArrayList<>(10);
    }

    public void addSprite(Sprite sprite, int type) {
        if (type == Sprite.SNACK) {
            snacks.add(sprite);
        }
        else if (type == Sprite.CHARACTER) {
            characters.add(sprite);
        }
        else {
            map = sprite;
        }
        addKeyListener(sprite);
    }

    public void start() {
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if (map != null) {
            map.draw(g2);
        }
        for (Sprite sprite : snacks) {
            sprite.draw(g2);
        }
        for (Sprite sprite : characters) {
            sprite.draw(g2);
        }
    }

    @Override
    public void update(Graphics g) {
        //double buffer
        imageBuffer = createImage(this.getWidth(), this.getHeight());
        imageGraphics = imageBuffer.getGraphics();
        paint(imageGraphics);
        imageGraphics.dispose();
        g.drawImage(imageBuffer, 0, 0, this);
    }

    @Override
    public void run () {
        while(true) {
            for (Sprite character : characters) {
                character.update();
                for (Sprite snack : snacks) {
                    ((Snack)snack).check(character);
                }
            }
            repaint();
            try{
                Thread.sleep(33);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
