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
import javax.sound.sampled.Clip;

import BluebellAdventures.Actions.ChangeScene;
import BluebellAdventures.Actions.CharacterMoveTo;
import BluebellAdventures.Actions.EnemyMove;
import BluebellAdventures.Actions.Quit;
import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.Enemy;
import BluebellAdventures.Characters.GameMap;
import BluebellAdventures.Characters.Snack;

import Megumin.Actions.Action;
import Megumin.Actions.Animate;
import Megumin.Actions.Effect;
import Megumin.Actions.MoveTo;
import Megumin.Actions.MouseCrash;
import Megumin.Actions.Infinite;
import Megumin.Actions.Interact;
import Megumin.Audio.Audio;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class Main {
	private static Director director;
	private static Infinite infinite;
	private static Interact interact;
	private static AudioEngine audioEngine;
	private static Character player;

	public static void main(String[] args) throws IOException {
		director = Director.getInstance();
		infinite = Infinite.getInstance();
		interact = Interact.getInstance();
		audioEngine = AudioEngine.getInstance();

		//init window property
		director.setTitle("Bluebell's Adventures");
		director.setResizable(false);
		director.setSize(1280, 720);
		director.setBackground(Color.black);
		director.setUndecorated(true);

		//init audio
		audioEngine.addAudio("eating", new Audio("resource/audio/eating.wav"));
		audioEngine.addAudio("attacking", new Audio("resource/audio/attacking.wav"));
		audioEngine.addAudio("slurping", new Audio("resource/audio/slurping.wav"));
		audioEngine.addAudio("walking", new Audio("resource/audio/walking.wav"));
		audioEngine.addAudio("menu", new Audio("resource/audio/menu.wav"));
		audioEngine.addAudio("main", new Audio("resource/audio/main.wav"));
		audioEngine.addAudio("nervous", new Audio("resource/audio/nervous.wav"));
		audioEngine.addAudio("victory", new Audio("resource/audio/victory.wav"));

		//system action
		Sprite system = new Sprite();
		interact.addEvent(KeyEvent.VK_ESCAPE, Interact.ON_KEY_CLICK, system, new Quit());

		//create scene
		Scene menu = createMenuScene();
		Scene characterSelection = createCharacterSelectionScene();
		Scene game = createGameScene();

		//menu action
		Sprite single = menu.getSpriteByName("single player");
		interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, single, new MouseCrash(new ChangeScene(characterSelection, "main")));
		Sprite exit = menu.getSpriteByName("exit");
		interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, exit, new MouseCrash(new Quit()));

		//character selection action
		Sprite character = characterSelection.getSpriteByName("rat");
		Action startGame = new MouseCrash(new ChangeScene(game, "main"));
		interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, character, startGame);

		Sprite back = characterSelection.getSpriteByName("back");
		Action backToMenu = new MouseCrash(new ChangeScene(menu, "menu"));
		interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, back, backToMenu);

		//start
		director.setScene(menu);
		director.start();
		audioEngine.loop("menu", Clip.LOOP_CONTINUOUSLY);
		try {
			director.getThread().join();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public static Scene createMenuScene() throws IOException {
		//init sprite
		Sprite singlePlayer = new Sprite("resource/image/tag_single.png", new Point(200, 100));
		Sprite multiPlayer = new Sprite("resource/image/tag_multi.png", new Point(200, 250));
		Sprite setting = new Sprite("resource/image/tag_setting.png", new Point(200, 400));
		Sprite exit = new Sprite("resource/image/tag_quit.png", new Point(200, 550));
		Sprite background = new Sprite("resource/image/menu_bg.jpeg");
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

	public static Scene createCharacterSelectionScene() throws IOException {
		//init Sprite
		Sprite rat = new Sprite("resource/image/rat.png", new Point(40, 300));
		Sprite raccoon = new Sprite("resource/image/raccoon.png", new Point(150, 350));
		Sprite dog = new Sprite("resource/image/dog.png", new Point(450, 300));
		Sprite cat = new Sprite("resource/image/cat.png", new Point(650, 300));
		Sprite fox = new Sprite("resource/image/fox.png", new Point(900, 300));
		Sprite roach = new Sprite("resource/image/roach.png", new Point(1000, 300));
		Sprite back = new Sprite("resource/image/tab_back.png", new Point(1100, 570));
		Sprite background = new Sprite("resource/image/menu_bg.jpeg");
		rat.setName("rat");
		raccoon.setName("raccoon");
		dog.setName("dog");
		cat.setName("cat");
		fox.setName("fox");
		roach.setName("roach");
		back.setName("back");

		//init layers
		Layer tabLayer = new Layer();
		tabLayer.addSprite(rat);
		tabLayer.addSprite(raccoon);
		tabLayer.addSprite(dog);
		tabLayer.addSprite(cat);
		tabLayer.addSprite(fox);
		tabLayer.addSprite(roach);
		tabLayer.addSprite(back);

		Layer mapLayer = new Layer();
		mapLayer.addSprite(background);

		//init scene
		Scene characterSelection = new Scene();
		characterSelection.addLayer(tabLayer);
		characterSelection.addLayer(mapLayer, 0);

		return characterSelection;
	}

	public static Scene createGameScene() throws IOException  {
		//init sprite
		Sprite nastu = new Enemy("resource/image/natsu1.png", new Point(200, 200))
							.setSpeed(10);
		Sprite machi = new Character("resource/image/machi1.png", new Point(600, 200))
							.setSpeed(5)
							.setSnackScore(0);
		Sprite snack1 = new Snack("resource/image/snack1.png", new Point(400, 300))
							.setScore(1);
		Sprite snack2 = new Snack("resource/image/snack1.png", new Point(500, 400))
							.setScore(10);
		Sprite snack3 = new Snack("resource/image/snack1.png", new Point(600, 500))
							.setScore(100);
		Sprite snack4 = new Snack("resource/image/snack1.png", new Point(700, 600))
							.setScore(1000);
		Sprite snack5 = new Snack("resource/image/snack1.png", new Point(800, 700))
							.setScore(10000);
		Sprite map = GameMap.getInstance("resource/image/full_map.png")
							.setPath("resource/path");

		//init layer
		Layer guardLayer = new Layer();
		guardLayer.addSprite(nastu);
		Layer ownPlayerLayer = new Layer();
		ownPlayerLayer.addSprite(machi);
		Layer snackLayer = new Layer();
		snackLayer.setName("snack");
		snackLayer.addSprite(snack1);
		snackLayer.addSprite(snack2);
		snackLayer.addSprite(snack3);
		snackLayer.addSprite(snack4);
		snackLayer.addSprite(snack5);
		Layer mapLayer = new Layer();
		mapLayer.addSprite(map);

		//init scene
		Scene game = new Scene();
		game.addLayer(guardLayer);
		game.addLayer(ownPlayerLayer);
		game.addLayer(mapLayer, 0);
		game.addLayer(snackLayer, 1);

		//init key listener and action
		//machi
		Action moveW = new CharacterMoveTo(0, -((Character)machi).getSpeed(), snackLayer.getSprites());
		Action moveA = new CharacterMoveTo(-((Character)machi).getSpeed(), 0, snackLayer.getSprites());
		Action moveS = new CharacterMoveTo(0, ((Character)machi).getSpeed(), snackLayer.getSprites());
		Action moveD = new CharacterMoveTo(((Character)machi).getSpeed(), 0, snackLayer.getSprites());
		Action machiAnimate = new Animate();
		((Animate)machiAnimate).addImage(machi.getImage());
		((Animate)machiAnimate).addImage(ImageIO.read(new File("resource/image/machi2.png")));
		moveW.addAction(machiAnimate);
		moveA.addAction(machiAnimate);
		moveS.addAction(machiAnimate);
		moveD.addAction(machiAnimate);
		interact.addEvent(KeyEvent.VK_W, Interact.ON_KEY_PRESS, machi, moveW);
		interact.addEvent(KeyEvent.VK_A, Interact.ON_KEY_PRESS, machi, moveA);
		interact.addEvent(KeyEvent.VK_S, Interact.ON_KEY_PRESS, machi, moveS);
		interact.addEvent(KeyEvent.VK_D, Interact.ON_KEY_PRESS, machi, moveD);

		//nastu
		Action nastuMove = new EnemyMove(((Enemy)nastu).getSpeed(), EnemyMove.ANTICLOCKWISE);
		Action nastuAnimate = new Animate();
		((Animate)nastuAnimate).addImage(nastu.getImage());
		((Animate)nastuAnimate).addImage(ImageIO.read(new File("resource/image/natsu2.png")));
		nastuMove.addAction(nastuAnimate);
		infinite.addEvent(nastu, nastuMove);

		return game;
	}
}
