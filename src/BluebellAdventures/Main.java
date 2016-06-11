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
import BluebellAdventures.Actions.CharacterMoveTo;
import BluebellAdventures.Actions.EnemyMove;
import BluebellAdventures.Actions.Quit;

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
	private static Director director;
	private static Infinite infinite;
	private static Interact interact;

	public static void main(String[] args) {
		director = Director.getInstance();
		infinite = Infinite.getInstance();
		interact = Interact.getInstance();

		//init window property
		director.setTitle("Bluebell's Adventures");
		director.setResizable(false);
		director.setSize(1280, 720);
		director.setBackground(Color.white);
		director.setUndecorated(true);

		//system action
		Sprite system = new Sprite();
		interact.addEvent(KeyEvent.VK_ESCAPE, Interact.ON_KEY_CLICK, system, new Quit());

		//create scene
		Scene menu = createMenuScene();
		Scene game = createGameScene();

		//add menu action
		Sprite single = menu.getSpriteByName("single player");
		interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, single, new MouseCrash(new ChangeScene(game)));
		Sprite exit = menu.getSpriteByName("exit");
		interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, exit, new MouseCrash(new Quit()));

		//start
		director.setScene(menu);
		director.start();
		try {
			director.getThread().join();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public static Scene createMenuScene() {
		//init sprite
		Sprite singlePlayer = null;
		Sprite multiPlayer = null;
		Sprite setting = null;
		Sprite exit = null;
		Sprite background = null;
		try {
			singlePlayer = new Sprite("resource/image/tag_single.png", new Point(200, 100));
			multiPlayer = new Sprite("resource/image/tag_multi.png", new Point(200, 250));
			setting = new Sprite("resource/image/tag_setting.png", new Point(200, 400));
			exit = new Sprite("resource/image/tag_quit.png", new Point(200, 550));
			background = new Sprite("resource/image/menu_bg.jpeg");
		} catch (IOException e) {
			System.out.println(e);
		}
		singlePlayer.setName("single player");
		exit.setName("exit");

		//init layer
		Layer tabLayer = new Layer();
		tabLayer.addSprite(singlePlayer);
		tabLayer.addSprite(multiPlayer);
		tabLayer.addSprite(setting);
		tabLayer.addSprite(exit);
		Layer mapLayer = new Layer();
		mapLayer.addSprite(background);

		//init scene
		Scene menu = new Scene();
		menu.addLayer(tabLayer);
		menu.addLayer(mapLayer, 0);

		return menu;
	}
	
	public static Scene createGameScene() {
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
		Action moveW = new CharacterMoveTo(0, -5, snackLayer.getSprites());
		Action moveA = new CharacterMoveTo(-5, 0, snackLayer.getSprites());
		Action moveS = new CharacterMoveTo(0, 5, snackLayer.getSprites());
		Action moveD = new CharacterMoveTo(5, 0, snackLayer.getSprites());
		Action machiAnimate = new Animate();
		try{
			((Animate)machiAnimate).addImage(machi.getImage());
			((Animate)machiAnimate).addImage(ImageIO.read(new File("resource/image/machi2.png")));
		} catch (IOException e) {
			System.out.println(e);
		}
		moveW.addAction(machiAnimate);
		moveA.addAction(machiAnimate);
		moveS.addAction(machiAnimate);
		moveD.addAction(machiAnimate);
		interact.addEvent(KeyEvent.VK_W, Interact.ON_KEY_PRESS, machi, moveW);
		interact.addEvent(KeyEvent.VK_A, Interact.ON_KEY_PRESS, machi, moveA);
		interact.addEvent(KeyEvent.VK_S, Interact.ON_KEY_PRESS, machi, moveS);
		interact.addEvent(KeyEvent.VK_D, Interact.ON_KEY_PRESS, machi, moveD);

		Action nastuMove = new EnemyMove(15, EnemyMove.ANTICLOCKWISE);
		Action nastuAnimate = new Animate();
		try{
			((Animate)nastuAnimate).addImage(nastu.getImage());
			((Animate)nastuAnimate).addImage(ImageIO.read(new File("resource/image/natsu2.png")));
		} catch (IOException e) {
			System.out.println(e);
		}

		nastuMove.addAction(nastuAnimate);
		infinite.addEvent(nastu, nastuMove);

		return game;
	}
}
