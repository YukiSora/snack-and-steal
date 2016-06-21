package BluebellAdventures.Actions;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.Enemy;
import BluebellAdventures.Characters.GameMap;
import BluebellAdventures.Characters.Snack;

import Megumin.Actions.Action;
import Megumin.Actions.Animate;
import Megumin.Actions.Infinite;
import Megumin.Actions.Interact;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class SelectCharacter extends Action {
	private Director director;
	private Infinite infinite;
	private Interact interact;
	private String playerImageName;
	private static AudioEngine audioEngine;

	public SelectCharacter(String playerImageName) {
		director = Director.getInstance();
		infinite = Infinite.getInstance();
		interact = Interact.getInstance();
		audioEngine = AudioEngine.getInstance();
		this.playerImageName = playerImageName;
	}

	@Override
	public void update(Sprite sprite) {
		Scene game = null;
		try {
			game = createGameScene();
		} catch (IOException e) {
			System.exit(1);
		}
		director.setScene(game);
		audioEngine.loop("main", Clip.LOOP_CONTINUOUSLY);
		super.update(sprite);
	}

	private Scene createGameScene() throws IOException  {
		//init sprite
		Sprite nastu = new Enemy("resource/image/natsu1.png", new Point(200, 200))
							.setSpeed(10);
		Sprite player = new Character("resource/image/" + playerImageName + "1.png", new Point(600, 200))
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
		ownPlayerLayer.addSprite(player);
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
		//player
		Action moveW = new CharacterMoveTo(0, -((Character)player).getSpeed(), snackLayer.getSprites());
		Action moveA = new CharacterMoveTo(-((Character)player).getSpeed(), 0, snackLayer.getSprites());
		Action moveS = new CharacterMoveTo(0, ((Character)player).getSpeed(), snackLayer.getSprites());
		Action moveD = new CharacterMoveTo(((Character)player).getSpeed(), 0, snackLayer.getSprites());
		Action playerAnimate = new Animate();
		((Animate)playerAnimate).addImage(player.getImage());
		((Animate)playerAnimate).addImage(ImageIO.read(new File("resource/image/" + playerImageName + "2.png")));
		moveW.addAction(playerAnimate);
		moveA.addAction(playerAnimate);
		moveS.addAction(playerAnimate);
		moveD.addAction(playerAnimate);
		interact.addEvent(KeyEvent.VK_W, Interact.ON_KEY_PRESS, player, moveW);
		interact.addEvent(KeyEvent.VK_A, Interact.ON_KEY_PRESS, player, moveA);
		interact.addEvent(KeyEvent.VK_S, Interact.ON_KEY_PRESS, player, moveS);
		interact.addEvent(KeyEvent.VK_D, Interact.ON_KEY_PRESS, player, moveD);

		//nastu
		Action nastuMove = new EnemyMove(((Enemy)nastu).getSpeed(), EnemyMove.ANTICLOCKWISE, new Point(50, 50), new Point(800, 500));
		Action nastuAnimate = new Animate();
		((Animate)nastuAnimate).addImage(nastu.getImage());
		((Animate)nastuAnimate).addImage(ImageIO.read(new File("resource/image/natsu2.png")));
		nastuMove.addAction(nastuAnimate);
		infinite.addEvent(nastu, nastuMove);

		return game;
	}
}
