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
import BluebellAdventures.Characters.MovableObject;

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
		Sprite enemyRoom1 = new Enemy("resource/image/ladybug1.png", new Point(200, 330))
							.setSpeed(10);

		Sprite enemyLivingRoom = new Enemy("resource/image/ladybug1.png", new Point(2375, 1050))
							.setSpeed(10);

		Sprite player = new Character("resource/image/" + playerImageName + "1.png", new Point(600, 200))
							.setHp(3)
							.setSpeed(25)
							.setSnackScore(0);
                Sprite fridge = new MovableObject("resource/image/fridge.png", new Point(3387, 2520))
                                                        .setLock(true);
                fridge.setName("fridge");
		Sprite snackFridge = new Snack("resource/image/snack1.png", new Point(3531, 2590))
							.setScore(350);
		Sprite snackcounter1 = new Snack("resource/image/snack1.png", new Point(3679, 2095))
							.setScore(100);
		Sprite snackcounter2 = new Snack("resource/image/snack1.png", new Point(3243, 2095))
							.setScore(-25);
		Sprite snacktable1 = new Snack("resource/image/snack1.png", new Point(3709, 809))
							.setScore(100);
		Sprite snacktable2 = new Snack("resource/image/snack1.png", new Point(3457, 462))
							.setScore(50);
                Sprite snackcoffee1 = new Snack("resource/image/snack1.png", new Point(3457, 462))
							.setScore(-25);
                Sprite snackcoffee2 = new Snack("resource/image/snack1.png", new Point(2283, 470))
							.setScore(-10);
                Sprite snackcoffee3 = new Snack("resource/image/snack1.png", new Point(2280, 311))
							.setScore(50);
                Sprite snackcloset1 = new Snack("resource/image/snack1.png", new Point(1590, 333))
							.setScore(100);
                Sprite snackcloset2 = new Snack("resource/image/snack1.png", new Point(1588, 291))
							.setScore(100);
                Sprite snackcloset3 = new Snack("resource/image/snack1.png", new Point(432, 733))
							.setScore(-25);
                Sprite snackcloset4 = new Snack("resource/image/snack1.png", new Point(504, 675))
							.setScore(-25);
		Sprite map = GameMap.getInstance("resource/image/full_map.png")
							.setPath("resource/path");

		//init layer
		Layer guardLayer = new Layer();
		guardLayer.addSprite(enemyRoom1);
		guardLayer.addSprite(enemyLivingRoom);

		Layer ownPlayerLayer = new Layer();
		ownPlayerLayer.addSprite(player);
                
                Layer lockLayer = new Layer();
                lockLayer.setName("locks");
                lockLayer.addSprite(fridge);
                
		Layer snackLayer = new Layer();
		snackLayer.setName("snack");
		snackLayer.addSprite(snackFridge);
		snackLayer.addSprite(snackcounter1);
		snackLayer.addSprite(snackcounter2);
		snackLayer.addSprite(snacktable1);
		snackLayer.addSprite(snacktable2);
                snackLayer.addSprite(snackcoffee1);
                snackLayer.addSprite(snackcoffee2);
                snackLayer.addSprite(snackcoffee3);
                snackLayer.addSprite(snackcloset1);
                snackLayer.addSprite(snackcloset2);
                snackLayer.addSprite(snackcloset3);
                snackLayer.addSprite(snackcloset4);
                
		Layer mapLayer = new Layer();
		mapLayer.addSprite(map);

		//init scene
		Scene game = new Scene();
		game.addLayer(guardLayer);
		game.addLayer(ownPlayerLayer);
		game.addLayer(mapLayer, 0);
		game.addLayer(snackLayer, 1);
                game.addLayer(lockLayer, 2);

		//init key listener and action
		//player
		Action moveW = new CharacterMoveTo(0, -((Character)player).getSpeed());
                ((CharacterMoveTo)moveW).addSprites(snackLayer.getSprites());
                ((CharacterMoveTo)moveW).addSprites(lockLayer.getSprites());
		Action moveA = new CharacterMoveTo(-((Character)player).getSpeed(), 0);
                ((CharacterMoveTo)moveA).addSprites(snackLayer.getSprites());
                ((CharacterMoveTo)moveA).addSprites(lockLayer.getSprites());
		Action moveS = new CharacterMoveTo(0, ((Character)player).getSpeed());
                ((CharacterMoveTo)moveS).addSprites(snackLayer.getSprites());
                ((CharacterMoveTo)moveS).addSprites(lockLayer.getSprites());
		Action moveD = new CharacterMoveTo(((Character)player).getSpeed(), 0);
                ((CharacterMoveTo)moveD).addSprites(snackLayer.getSprites());
                ((CharacterMoveTo)moveD).addSprites(lockLayer.getSprites());
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

		Action enemyRoom1Move = new EnemyMove(((Enemy)enemyRoom1).getSpeed(), new Point(47, 47), new Point(925, 741));
		Action enemyLivingRoomMove = new EnemyMove(((Enemy)enemyRoom1).getSpeed(), new Point(1762, 47), new Point(1383, 2015));

		((EnemyMove)enemyRoom1Move).addSprites(ownPlayerLayer.getSprites());
		((EnemyMove)enemyLivingRoomMove).addSprites(ownPlayerLayer.getSprites());

		infinite.addEvent(enemyRoom1, enemyRoom1Move);
		infinite.addEvent(enemyLivingRoom, enemyLivingRoomMove);

		// Action nastuMove = new EnemyMove(((Enemy)nastu).getSpeed(), new Point(1764, 47), new Point(3137, 2045));
		// Action nastuAnimate = new Animate();
		// ((Animate)nastuAnimate).addImage(nastu.getImage());
		// ((Animate)nastuAnimate).addImage(ImageIO.read(new File("resource/image/natsu2.png")));
		// nastuMove.addAction(nastuAnimate);

		return game;
	}
}
