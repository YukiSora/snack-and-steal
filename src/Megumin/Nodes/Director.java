package Megumin.Nodes;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Megumin.Actions.Infinite;
import Megumin.Actions.Interact;

public class Director extends Frame implements KeyListener, MouseListener, Runnable {
    private static Director director;
    private Infinite infinite;
    private Interact interact;
    private Scene scene;
    private Thread thread;

    private Director() {
        infinite = Infinite.getInstance();
        interact = Interact.getInstance();
        thread = new Thread(this);
        addKeyListener(this);
        addMouseListener(this);
    }

    public static Director getInstance() {
        if (director == null) {
            director = new Director();
        }

        return director;
    }

    public void start() {
        setVisible(true);
        thread.start();
    }

    //render
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        scene.render(g2);
    }

    @Override
    public void update(Graphics g) {
        //double buffer
        Image imageBuffer = createImage(this.getWidth(), this.getHeight());
        Graphics imageBufferGraphics = imageBuffer.getGraphics();
        paint(imageBufferGraphics);
        imageBufferGraphics.dispose();
        g.drawImage(imageBuffer, 0, 0, this);
    }

    //main loop
    @Override
    public void run () {
        while(true) {
            interact.update();
            infinite.update();
            repaint();
            try{
                Thread.sleep(33);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }

    //key listener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        interact.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        interact.keyReleased(e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        interact.mouseClicked(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
