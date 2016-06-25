package BluebellAdventures.CreateScenes;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import BluebellAdventures.Actions.CharacterMoveTo;
import BluebellAdventures.Actions.EnemyMove;
import BluebellAdventures.Characters.Character;
import BluebellAdventures.Characters.Enemy;
import BluebellAdventures.Characters.GameMap;
import BluebellAdventures.Characters.Snack;
import BluebellAdventures.Characters.MovableObject;

import Megumin.Actions.Action;
import Megumin.Actions.Animate;
import Megumin.Actions.Infinite;
import Megumin.Actions.Interact;
import Megumin.Nodes.Director;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class CreateGameScene {
    public static Scene createGameScene(String playerImageName) throws IOException {
        Director director = Director.getInstance();
        Infinite infinite = Infinite.getInstance();
        Interact interact = Interact.getInstance();

        //init AI & player sprites
        Sprite enemyBedRoom = new Enemy("resource/image/ladybug1.png", new Point(350, 400))
                            .setSpeed(5);

        Sprite enemyLivingRoom = new Enemy("resource/image/ladybug1.png", new Point(2360, 980))
                            .setSpeed(10);

        Sprite enemyDiningRoom = new Enemy("resource/image/ladybug1.png", new Point(3600, 1200))
                            .setSpeed(10);

        Sprite player = new Character("resource/image/" + playerImageName + "1.png", new Point(600, 200))
                            .setHp(3)
                            .setSpeed(25)
                            .setSnackScore(0)
                            .setKey(0);

        //Fox has advantage in speed
        if (playerImageName == "fox"){
            ((Character)player).setSpeed(40);
        }
        if (playerImageName == "cat"){
            ((Character)player).setHp(9);
        }
        if(playerImageName == "rat"){
            ((Character)player).setKey(10);
        }

        //init movable object sprites
        Sprite fridge = new MovableObject("resource/image/fridge.png", new Point(3387, 2520))
                            .setLock(true);
        fridge.setName("fridge");
        Sprite storeDoor = new MovableObject("resource/image/door.png", new Point(2698, 1441))
                            .setLock(true);
        storeDoor.setName("door");

        //init snacks sprites
        Sprite snackFridge = new Snack("resource/image/snack1.png", new Point(3531, 2590))
                            .setScore(350);
        Sprite snackkitchen1 = new Snack("resource/image/badsnack.png", new Point(3175, 2084))
                            .setScore(-25);
        Sprite snackkitchen2 = new Snack("resource/image/snack1.png", new Point(3767, 2042))
                            .setScore(50);
        Sprite snackkitchen3 = new Snack("resource/image/snack1.png", new Point(3890, 2404))
                            .setScore(100);
        Sprite snackkitchen4 = new Snack("resource/image/snack1.png", new Point(3977, 2338))
                            .setScore(50);
        Sprite snackdining1 = new Snack("resource/image/badsnack.png", new Point(3525, 687))
                             .setScore(-50);
        Sprite snackdining2 = new Snack("resource/image/snack1.png", new Point(3623, 319))
                            .setScore(20);
        Sprite snackrack1 = new Snack("resource/image/snack1.png", new Point(3933, 1166))
                            .setScore(40);
        Sprite snackrack2 = new Snack("resource/image/snack1.png", new Point(3935, 1274))
                            .setScore(40);
        Sprite snackcoffee1 = new Snack("resource/image/snack1.png", new Point(2312, 482))
                            .setScore(20);
        Sprite snackcoffee2 = new Snack("resource/image/badsnack.png", new Point(2549, 340))
                            .setScore(-25);
        Sprite snackstore1 = new Snack("resource/image/snack1.png", new Point(2409, 1501))
                            .setScore(50);
        Sprite snackstore2 = new Snack("resource/image/snack1.png", new Point(2406, 1958))
                            .setScore(80);
        Sprite snackstore3 = new Snack("resource/image/snack1.png", new Point(2495, 1958))
                            .setScore(50);
        Sprite snackcloset1 = new Snack("resource/image/snack1.png", new Point(2591, 1336))
                            .setScore(50);
        Sprite snackcloset2 = new Snack("resource/image/snack1.png", new Point(1380, 1948))
                            .setScore(100);
        Sprite snackroom1 = new Snack("resource/image/badsnack.png", new Point(487, 653))
                            .setScore(-25);
        Sprite snackroom2 = new Snack("resource/image/snack1.png", new Point(864, 316))
                            .setScore(20);
        Sprite snackroom3 = new Snack("resource/image/snack1.png", new Point(864, 236))
                            .setScore(30);
        Sprite snackroom4 = new Snack("resource/image/snack1.png", new Point(1259, 497))
                            .setScore(20);
        Sprite snackroom5 = new Snack("resource/image/snack1.png", new Point(1292, 429))
                            .setScore(20);
        Sprite snackroom6 = new Snack("resource/image/badsnack.png", new Point(1603, 105))
                            .setScore(-50);
 
        //init snacks sprites
        Sprite key1 = new Snack("resource/image/key.png", new Point(2900, 1717))
                            .setScore(0);
        //init map sprite
        Sprite map = GameMap.getInstance("resource/image/full_map.png")
                            .setPath("resource/path");

        GameMap.getInstance().setPosition(-2752, -2619);

        //init layer
        Layer guardLayer = new Layer();
        guardLayer.addSprite(enemyBedRoom);
        guardLayer.addSprite(enemyLivingRoom);
        guardLayer.addSprite(enemyDiningRoom);

        Layer ownPlayerLayer = new Layer();
        ownPlayerLayer.addSprite(player);

        Layer lockLayer = new Layer();
        lockLayer.setName("locks");
        lockLayer.addSprite(fridge);
        lockLayer.addSprite(storeDoor);

        Layer keyLayer = new Layer();
        keyLayer.setName("keys");
        keyLayer.addSprite(key1);

        Layer snackLayer = new Layer();
        snackLayer.setName("snack");
        snackLayer.addSprite(snackFridge);
        snackLayer.addSprite(snackkitchen1);
        snackLayer.addSprite(snackkitchen2);
        snackLayer.addSprite(snackkitchen3);
        snackLayer.addSprite(snackkitchen4);
        snackLayer.addSprite(snackdining1);
        snackLayer.addSprite(snackdining2);
        snackLayer.addSprite(snackrack1);
        snackLayer.addSprite(snackrack2);
        snackLayer.addSprite(snackcoffee1);
        snackLayer.addSprite(snackcoffee2);
        snackLayer.addSprite(snackstore1);
        snackLayer.addSprite(snackstore2);
        snackLayer.addSprite(snackstore3);
        snackLayer.addSprite(snackcloset1);
        snackLayer.addSprite(snackcloset2);
        snackLayer.addSprite(snackroom1);
        snackLayer.addSprite(snackroom2);
        snackLayer.addSprite(snackroom3);
        snackLayer.addSprite(snackroom4);
        snackLayer.addSprite(snackroom5);
        snackLayer.addSprite(snackroom6);

        Layer mapLayer = new Layer();
        mapLayer.addSprite(map);

        //init scene
        Scene game = new Scene();
        game.addLayer(mapLayer);
        game.addLayer(snackLayer);
        game.addLayer(lockLayer);
        game.addLayer(keyLayer);
        game.addLayer(guardLayer);
        game.addLayer(ownPlayerLayer);

        //init key listener and action
        //player
        //control action
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
        //animate
        Action playerAnimate = new Animate();
        ((Animate)playerAnimate).addImage(player.getImage());
        ((Animate)playerAnimate).addImage(ImageIO.read(new File("resource/image/" + playerImageName + "2.png")));
        moveW.addAction(playerAnimate);
        moveA.addAction(playerAnimate);
        moveS.addAction(playerAnimate);
        moveD.addAction(playerAnimate);
        //insert action
        interact.addEvent(KeyEvent.VK_W, Interact.ON_KEY_PRESS, player, moveW);
        interact.addEvent(KeyEvent.VK_A, Interact.ON_KEY_PRESS, player, moveA);
        interact.addEvent(KeyEvent.VK_S, Interact.ON_KEY_PRESS, player, moveS);
        interact.addEvent(KeyEvent.VK_D, Interact.ON_KEY_PRESS, player, moveD);

        // Enemy 1 - Bedroom
        //move action
        Action enemyBedRoomMove = new EnemyMove(((Enemy)enemyBedRoom).getSpeed(), new Point(47, 47), new Point(925, 741));
        ((EnemyMove)enemyBedRoomMove).addSprites(ownPlayerLayer.getSprites());
        //animate
        Action enemyBedRoomAnimate = new Animate();
        ((Animate)enemyBedRoomAnimate).addImage(enemyBedRoom.getImage());
        ((Animate)enemyBedRoomAnimate).addImage(ImageIO.read(new File("resource/image/ladybug2.png")));
        enemyBedRoomMove.addAction(enemyBedRoomAnimate);
        //insert
        infinite.addEvent(enemyBedRoom, enemyBedRoomMove);

        // Enemy 2 - Living Room
        //move action
        Action enemyLivingRoomMove = new EnemyMove(((Enemy)enemyLivingRoom).getSpeed(), new Point(1762, 47), new Point(1383, 2015));
        ((EnemyMove)enemyLivingRoomMove).addSprites(ownPlayerLayer.getSprites());
        //animate
        Action enemyLivingRoomAnimate = new Animate();
        ((Animate)enemyLivingRoomAnimate).addImage(enemyLivingRoom.getImage());
        ((Animate)enemyLivingRoomAnimate).addImage(ImageIO.read(new File("resource/image/ladybug2.png")));
        enemyLivingRoomMove.addAction(enemyLivingRoomAnimate);
        //insert
        infinite.addEvent(enemyLivingRoom, enemyLivingRoomMove);

        // Enemy 3 - Dining Room
        //move action
        Action enemyDiningRoomMove = new EnemyMove(((Enemy)enemyDiningRoom).getSpeed(), new Point(2724, 47), new Point(1324, 2640));
        ((EnemyMove)enemyDiningRoomMove).addSprites(ownPlayerLayer.getSprites());
        //animate
        Action enemyDiningRoomAnimate = new Animate();
        ((Animate)enemyDiningRoomAnimate).addImage(enemyDiningRoom.getImage());
        ((Animate)enemyDiningRoomAnimate).addImage(ImageIO.read(new File("resource/image/ladybug2.png")));
        enemyDiningRoomMove.addAction(enemyDiningRoomAnimate);
        //insert
        //infinite.addEvent(enemyDiningRoom, enemyDiningRoomMove);

        return game;
    }
}