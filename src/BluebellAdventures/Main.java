package BluebellAdventures;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;

import BluebellAdventures.Actions.ChangeScene;
import BluebellAdventures.Actions.Quit;
import BluebellAdventures.Actions.SnackScore;

import Megumin.Actions.Action;
import Megumin.Actions.Animate;
import Megumin.Actions.Effect;
import Megumin.Actions.MoveTo;
import Megumin.Actions.MouseCrash;
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

		Sprite system = new Sprite();

		interact.addEvent(KeyEvent.VK_ESCAPE, Interact.ON_KEY_CLICK, system, new Action() {
			@Override
			public void update(Sprite sprite) {
				System.exit(1);
			}
		});

		Scene menu = createMenuScene();
		Scene game = createGameScene();

		Sprite single = menu.getSpriteByName("single");
		Action changeScene = new MouseCrash(new ChangeScene(game));
		interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, single, changeScene);

		Sprite exit = menu.getSpriteByName("exit");
		Action quitGame = new MouseCrash(new Quit());
		interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, exit, quitGame);

		//start
		director.setScene(menu);
		director.start();
		try {
			director.getThread().join();
		} catch (InterruptedException e) {
			System.out.println(e);
		}

		director.setScene(game);
	}

	public static Scene createMenuScene() {
		//init sprite
		Sprite single = null;
		Sprite multi = null;
		Sprite setting = null;
		Sprite exit = null;
		Sprite bg = null;
		try {
			single = new Sprite("resource/image/tag_single.png", new Point(200, 100));
			multi = new Sprite("resource/image/tag_multi.png", new Point(200, 250));
			setting = new Sprite("resource/image/tag_setting.png", new Point(200, 400));
			exit = new Sprite("resource/image/tag_quit.png", new Point(200, 550));
			bg = new Sprite("resource/image/menu_bg.jpeg");
		} catch (IOException e) {
			System.out.println(e);
		}
		single.setName("single");
		exit.setName("exit");

		//init layer
		Layer tabLayer = new Layer();
		tabLayer.addSprite(single);
		tabLayer.addSprite(multi);
		tabLayer.addSprite(setting);
		tabLayer.addSprite(exit);
		Layer mapLayer = new Layer();
		mapLayer.addSprite(bg);

		//init scene
		Scene menu = new Scene();
		menu.addLayer(tabLayer);
		menu.addLayer(mapLayer, 0);

		return menu;
	}
	
	public static Scene createGameScene() {
		Infinite infinite = Infinite.getInstance();
		Interact interact = Interact.getInstance();

		//init sprite
		Sprite nastu = null;
		Sprite machi = null;
		Sprite map = null;
		Sprite snack = null;
		try {
			nastu = new Sprite("resource/image/natsu1.png", new Point(200, 200));
			machi = new Sprite("resource/image/machi1.png", new Point(200, 200));
			map = new Sprite("resource/image/small_map.png");
			snack = new Sprite("resource/image/snack1.png", new Point(400, 300));
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
		snackLayer.setName("snack");
		Layer mapLayer = new Layer();
		mapLayer.addSprite(map);

		//init scene
		Scene game = new Scene();
		game.addLayer(guardLayer);
		game.addLayer(ownPlayerLayer);
		game.addLayer(mapLayer, 0);
		game.addLayer(snackLayer, 1);

		//init key listener and action
		//Action moveW = new MoveTo(0, -5);

		Action moveW = new SnackScore(0, -5, snackLayer.getSprites());
		Action moveA = new SnackScore(-5, 0, snackLayer.getSprites());
		Action moveS = new SnackScore(0, 5, snackLayer.getSprites());
		Action moveD = new SnackScore(5, 0, snackLayer.getSprites());
		Action animate = new Animate();
		try{
			((Animate)animate).addImage(ImageIO.read(new File("resource/image/machi1.png")));
			((Animate)animate).addImage(ImageIO.read(new File("resource/image/machi2.png")));
		} catch (IOException e) {
			System.out.println(e);
		}

		Action animate2 = new Animate();
		try{
			((Animate)animate2).addImage(ImageIO.read(new File("resource/image/natsu1.png")));
			((Animate)animate2).addImage(ImageIO.read(new File("resource/image/natsu2.png")));
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

		Action poi = new Action() {
			@Override
			public void update(Sprite sprite) {
				// int x = sprite.getPosition().getX() + 5 > 1280 ? -1280 : 5;
				// int y = sprite.getPosition().getY() + 5 > 720 ? -720 : 5;
				
				int x = 0;
				int y = 0;

				int[] dir = sprite.getDirection();

				if (sprite.getPosition().getY() <= 0  && sprite.getPosition().getX() < 1070){
					// Right
					x = 15;
					y = 0;

					dir[0] = 1;
					dir[1] = 0;

				} 

				if (sprite.getPosition().getX() >= 1070){
					// Down
					x = 0;
					y = 15;

					dir[0] = -1;
					dir[1] = 0;
				}

				if (sprite.getPosition().getY() >= 440){
					 // Left
					 x = (-15);
					 y = 0;

					dir[0] = -1;
					dir[1] = 0;
				}

				if (sprite.getPosition().getX() <= 0 && sprite.getPosition().getY() > 0){
					 // Up
					 x = 0;
					 y =(-15);

					dir[0] = 0;
					dir[1] = -1;
				}

				if (x == 0 && y == 0){
					// Automatically Head Down When Out of Position
					x = 0;
					y = 5;

					dir[0] = -1;
					dir[1] = 0;
				}

				// System.out.println(dir[0] + "," + dir[1]);

				sprite.runAction(new MoveTo(x, y));
				super.update(sprite);
			}
		};

		poi.addAction(animate2);
		infinite.addEvent(nastu, poi);

		return game;
	}
}
