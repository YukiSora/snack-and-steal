package BluebellAdventures.CreateScenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

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
        Sprite enemyBedRoom = new Enemy("resource/image/enemy_man1.png", new Point(350, 400))
                            .setSpeed(5);

        Sprite enemyLivingRoom = new Enemy("resource/image/enemy_woman1.png", new Point(2360, 980))
                            .setSpeed(5);

        Sprite enemyDiningRoom = new Enemy("resource/image/enemy_girl1.png", new Point(3600, 1200))
                            .setSpeed(5);

        Sprite player = new Character("resource/image/" + playerImageName + "1.png")
                            .setHp(3)
                            .setSpeed(25)
                            .setSnackScore(0)
                            .setKey(0);
        player.setName("player");
        player.setPosition(new Point((1280 - player.getImage().getWidth()) / 2, (720 - player.getImage().getHeight()) / 2));

        //Set character advantages
        if (playerImageName == "fox"){
            ((Character)player).setSpeed(35);
        }
        if (playerImageName == "cat"){
            ((Character)player).setHp(9);
        }
        if(playerImageName == "rat"){
            ((Character)player).setKey(2);
        }

        //init movable object sprites
        Sprite fridge = new MovableObject("resource/image/fridge.png", new Point(3387, 2520))
                            .setLock(true)
                            .setOpen(false);
        Sprite stove = new MovableObject("resource/image/cooker.png", new Point(3880, 2311))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard1 = new MovableObject("resource/image/cupboard.png", new Point(1548, 64))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard2 = new MovableObject("resource/image/cupboard.png", new Point(800, 64))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard3 = new MovableObject("resource/image/cupboard.png", new Point(3863, 1127))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard4 = new MovableObject("resource/image/closet.png", new Point(1333, 1895))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard5 = new MovableObject("resource/image/closet.png", new Point(2334, 1278))
                            .setLock(false)
                            .setOpen(false);

        //init immovable object sprites
        Sprite table = new MovableObject("resource/image/diningtable.png", new Point(3359, 178))
                            .setLock(false)
                            .setOpen(false);
        Sprite tree = new MovableObject("resource/image/tree.png", new Point(24, 2105))
                            .setLock(false)
                            .setOpen(false);
        Sprite fridgeCover = new MovableObject("resource/image/fridge_top.png", new Point(3387, 2520))
                            .setLock(false)
                            .setOpen(false);
        Sprite stoveCover = new MovableObject("resource/image/cooker.png", new Point(3880, 2311))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard1Cover = new MovableObject("resource/image/cupboard.png", new Point(1548, 64))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard2Cover = new MovableObject("resource/image/cupboard.png", new Point(800, 64))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard3Cover = new MovableObject("resource/image/cupboard.png", new Point(3863, 1127))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard4Cover = new MovableObject("resource/image/closet.png", new Point(1333, 1895))
                            .setLock(false)
                            .setOpen(false);
        Sprite cupboard5Cover = new MovableObject("resource/image/closet.png", new Point(2334, 1278))
                            .setLock(false)
                            .setOpen(false);

        //init door sprites
        Sprite storeDoor = new MovableObject("resource/image/door.png", new Point(2705, 1415))
                            .setLock(true)
                            .setOpen(false);
        Sprite roomDoor1 = new MovableObject("resource/image/door.png", new Point(689, 774))
                            .setLock(true)
                            .setOpen(false);
        Sprite roomDoor2 = new MovableObject("resource/image/door.png", new Point(1063, 774))
                            .setLock(true)
                            .setOpen(false);
        Sprite roomDoor3 = new MovableObject("resource/image/door.png", new Point(1050, 1125))
                            .setLock(false)
                            .setOpen(false);
        Sprite mainDoor = new MovableObject("resource/image/door.png", new Point(1942, 1849))
                            .setLock(false)
                            .setOpen(false);
        Sprite backDoor = new MovableObject("resource/image/door.png", new Point(2848, 2692))
                            .setLock(true)
                            .setOpen(false);
        Sprite toiletDoor1 = new MovableObject("resource/image/verticaldoor.png", new Point(540, 861))
                            .setLock(false)
                            .setOpen(false);
        Sprite toiletDoor2 = new MovableObject("resource/image/verticaldoor.png", new Point(459, 1602))
                            .setLock(false)
                            .setOpen(false);

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
        Sprite snackdining1 = new Snack("resource/image/snack1.png", new Point(3525, 687))
                             .setScore(50);
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
        Sprite key1 = new Snack("resource/image/key.png", new Point(2836, 1763))
                            .setScore(0);
        Sprite key2 = new Snack("resource/image/key.png", new Point(82, 1519))
                            .setScore(0);
        Sprite key3 = new Snack("resource/image/key.png", new Point(863, 101))
                            .setScore(0);
        Sprite key4 = new Snack("resource/image/key.png", new Point(3938, 2338))
                            .setScore(0);
        Sprite key5 = new Snack("resource/image/key.png", new Point(3623, 319))
                            .setScore(0);

        //init finish line
        Sprite finishLine = new Snack("resource/image/natsu1.png", new Point(3705,2850));

        //init map sprite
        Sprite map = GameMap.getInstance("resource/image/full_map copy.jpg")
                            .setPath("resource/path");

        //set start point
        GameMap.getInstance().setPosition(-705, -2600);

        //draw score infomation
        Sprite score = new Character() {
            @Override
            public void render(Graphics2D g) {
                if (getVisible()) {
                    g.setFont(new Font("TimesRoman", Font.BOLD, 35));

                    String hpString = "❤❤❤❤❤❤❤❤❤❤";
                    g.setColor(Color.white);
                    g.drawString("Health: ", 100, 50);
                    g.setColor(Color.red);
                    g.drawString(hpString.substring(0, ((Character)player).getHp()), 250, 50);

                    String keyString = "⚩⚩⚩⚩⚩⚩⚩⚩⚩⚩";
                    g.setColor(Color.white);
                    g.drawString("Key: ", 400, 50);
                    g.setColor(Color.yellow);
                    g.drawString(keyString.substring(0, ((Character)player).getKey()), 500, 50);

                    g.setColor(Color.white);
                    g.drawString("Score: " + ((Character)player).getSnackScore(), 700, 50);
                }
            }
        };
        score.setVisible(true);

        //init layer
        Layer scoreLayer = new Layer();
        scoreLayer.addSprite(score);

        Layer finishLayer = new Layer();
        finishLayer.addSprite(finishLine);

        Layer guardLayer = new Layer();
        guardLayer.addSprite(enemyBedRoom);
        guardLayer.addSprite(enemyLivingRoom);
        guardLayer.addSprite(enemyDiningRoom);

        Layer ownPlayerLayer = new Layer();
        ownPlayerLayer.addSprite(player);

        Layer fridgeLayer = new Layer();
        fridgeLayer.setName("fridges");
        fridgeLayer.addSprite(fridge);

        Layer cupboardLayer = new Layer();
        cupboardLayer.setName("cupboards");
        cupboardLayer.addSprite(cupboard1);
        cupboardLayer.addSprite(cupboard2);
        cupboardLayer.addSprite(cupboard3);
        cupboardLayer.addSprite(cupboard4);
        cupboardLayer.addSprite(cupboard5);
        cupboardLayer.addSprite(stove);

        Layer doorLayer = new Layer();
        doorLayer.setName("doors");
        doorLayer.addSprite(storeDoor);
        doorLayer.addSprite(mainDoor);
        doorLayer.addSprite(backDoor);
        doorLayer.addSprite(roomDoor1);
        doorLayer.addSprite(roomDoor2);
        doorLayer.addSprite(roomDoor3);
        doorLayer.addSprite(toiletDoor1);
        doorLayer.addSprite(toiletDoor2);

        Layer keyLayer = new Layer();
        keyLayer.setName("keys");
        keyLayer.addSprite(key1);
        keyLayer.addSprite(key2);
        keyLayer.addSprite(key3);
        keyLayer.addSprite(key4);
        keyLayer.addSprite(key5);

        Layer snackLayer = new Layer();
        snackLayer.setName("snacks");
        snackLayer.addSprite(snackFridge);
        snackLayer.addSprite(snackkitchen1);
        snackLayer.addSprite(snackkitchen2);
        snackLayer.addSprite(snackkitchen3);
        snackLayer.addSprite(snackkitchen4);
        snackLayer.addSprite(snackdining1);
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

        Layer coverLayer = new Layer();
        coverLayer.addSprite(table);
        coverLayer.addSprite(tree);
        coverLayer.addSprite(stoveCover);
        coverLayer.addSprite(cupboard1Cover);
        coverLayer.addSprite(cupboard2Cover);
        coverLayer.addSprite(cupboard3Cover);
        coverLayer.addSprite(cupboard4Cover);
        coverLayer.addSprite(cupboard5Cover);
        coverLayer.addSprite(fridgeCover);

        Layer mapLayer = new Layer();
        mapLayer.addSprite(map);

        //init scene
        Scene game = new Scene();
        game.setName("game");
        game.addLayer(mapLayer);
        game.addLayer(snackLayer);
        game.addLayer(fridgeLayer);
        game.addLayer(doorLayer);
        game.addLayer(cupboardLayer);
        game.addLayer(keyLayer);
        game.addLayer(guardLayer);
        game.addLayer(finishLayer);
        game.addLayer(ownPlayerLayer);
        game.addLayer(coverLayer);
        game.addLayer(scoreLayer);

        //init key listener and action
        //player
        //control action
        Action moveW = new CharacterMoveTo(0, -((Character)player).getSpeed());
        ((CharacterMoveTo)moveW).addSprites(snackLayer.getSprites());
        ((CharacterMoveTo)moveW).addSprites(fridgeLayer.getSprites());
        ((CharacterMoveTo)moveW).addSprites(doorLayer.getSprites());
        ((CharacterMoveTo)moveW).addSprites(cupboardLayer.getSprites());
        ((CharacterMoveTo)moveW).addSprites(keyLayer.getSprites());
        ((CharacterMoveTo)moveW).addSprites(finishLayer.getSprites());

        Action moveA = new CharacterMoveTo(-((Character)player).getSpeed(), 0);
        ((CharacterMoveTo)moveA).addSprites(snackLayer.getSprites());
        ((CharacterMoveTo)moveA).addSprites(fridgeLayer.getSprites());
        ((CharacterMoveTo)moveA).addSprites(doorLayer.getSprites());
        ((CharacterMoveTo)moveA).addSprites(cupboardLayer.getSprites());
        ((CharacterMoveTo)moveA).addSprites(keyLayer.getSprites());
        ((CharacterMoveTo)moveA).addSprites(finishLayer.getSprites());

        Action moveS = new CharacterMoveTo(0, ((Character)player).getSpeed());
        ((CharacterMoveTo)moveS).addSprites(snackLayer.getSprites());
        ((CharacterMoveTo)moveS).addSprites(fridgeLayer.getSprites());
        ((CharacterMoveTo)moveS).addSprites(doorLayer.getSprites());
        ((CharacterMoveTo)moveS).addSprites(cupboardLayer.getSprites());
        ((CharacterMoveTo)moveS).addSprites(keyLayer.getSprites());
        ((CharacterMoveTo)moveS).addSprites(finishLayer.getSprites());

        Action moveD = new CharacterMoveTo(((Character)player).getSpeed(), 0);
        ((CharacterMoveTo)moveD).addSprites(snackLayer.getSprites());
        ((CharacterMoveTo)moveD).addSprites(fridgeLayer.getSprites());
        ((CharacterMoveTo)moveD).addSprites(doorLayer.getSprites());
        ((CharacterMoveTo)moveD).addSprites(cupboardLayer.getSprites());
        ((CharacterMoveTo)moveD).addSprites(keyLayer.getSprites());
        ((CharacterMoveTo)moveD).addSprites(finishLayer.getSprites());

        //animate
        Action playerAnimate = new Animate();
        ((Animate)playerAnimate).addImage(player.getImage());
        ((Animate)playerAnimate).addImage("resource/image/" + playerImageName + "2.png");
        moveW.addAction(playerAnimate);
        moveA.addAction(playerAnimate);
        moveS.addAction(playerAnimate);
        moveD.addAction(playerAnimate);
        //insert action
        interact.addEvent(KeyEvent.VK_W, Interact.ON_KEY_PRESS, player, moveW, "game");
        interact.addEvent(KeyEvent.VK_A, Interact.ON_KEY_PRESS, player, moveA, "game");
        interact.addEvent(KeyEvent.VK_S, Interact.ON_KEY_PRESS, player, moveS, "game");
        interact.addEvent(KeyEvent.VK_D, Interact.ON_KEY_PRESS, player, moveD, "game");

        //reset direction to 0
        infinite.addEvent(player, new Action() {
            @Override
            public void update(Sprite sprite) {
                sprite.getDirection()[0] = 0;
                sprite.getDirection()[1] = 0;
            }
        });

        // Finshing Line
        Action finishLineAnimate = new Animate();
        ((Animate)finishLineAnimate).addImage(finishLine.getImage());
        ((Animate)finishLineAnimate).addImage("resource/image/natsu2.png");
        infinite.addEvent(finishLine, finishLineAnimate);

        // Enemy 1 - Bedroom
        //move action
        Action enemyBedRoomMove = new EnemyMove(((Enemy)enemyBedRoom).getSpeed(), new Point(47, 47), new Point(925, 741));
        ((EnemyMove)enemyBedRoomMove).addSprites(ownPlayerLayer.getSprites());
        //animate
        Action enemyBedRoomAnimate = new Animate();
        ((Animate)enemyBedRoomAnimate).addImage(enemyBedRoom.getImage());
        ((Animate)enemyBedRoomAnimate).addImage("resource/image/enemy_man2.png");
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
        ((Animate)enemyLivingRoomAnimate).addImage("resource/image/enemy_woman2.png");
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
        ((Animate)enemyDiningRoomAnimate).addImage("resource/image/enemy_girl2.png");
        enemyDiningRoomMove.addAction(enemyDiningRoomAnimate);
        //insert
        infinite.addEvent(enemyDiningRoom, enemyDiningRoomMove);

        return game;
    }
}
