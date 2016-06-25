package BluebellAdventures;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.sound.sampled.Clip;

import BluebellAdventures.Actions.ChangeScene;
import BluebellAdventures.Actions.SelectCharacter;
import BluebellAdventures.Actions.Quit;
import BluebellAdventures.CreateScenes.CreateLoadingScene;
import BluebellAdventures.CreateScenes.CreateMenuScene;

import Megumin.Actions.Action;
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

    public static void main(String[] args) throws IOException {
        //init instances
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

        //start loading page
        director.setScene(CreateLoadingScene.createLoadingScene("resource/image/splash_screen.png"));
        director.start();

        //init audio
        audioEngine.addAudio("eating", new Audio("resource/audio/eating.wav"));
        audioEngine.addAudio("attacking", new Audio("resource/audio/attacking.wav"));
        audioEngine.addAudio("slurping", new Audio("resource/audio/slurping.wav"));
        audioEngine.addAudio("walking", new Audio("resource/audio/walking.wav"));
        audioEngine.addAudio("menu", new Audio("resource/audio/menu.wav"));
        audioEngine.addAudio("main", new Audio("resource/audio/main.wav"));
        audioEngine.addAudio("nervous", new Audio("resource/audio/nervous.wav"));
        audioEngine.addAudio("victory", new Audio("resource/audio/victory.wav"));
        audioEngine.addAudio("door", new Audio("resource/audio/door.wav"));
        audioEngine.addAudio("fridge", new Audio("resource/audio/fridge.wav"));
        audioEngine.addAudio("unlock", new Audio("resource/audio/unlock.wav"));

        //system action
        Sprite system = new Sprite();
        interact.addEvent(KeyEvent.VK_ESCAPE, Interact.ON_KEY_CLICK, system, new Quit());

        //create scene
        Scene menu = CreateMenuScene.createMenuScene();
        Scene characterSelection = createCharacterSelectionScene();

        //menu action
        Sprite single = menu.getSpriteByName("single player");
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, single, new MouseCrash(new ChangeScene(characterSelection, "main")));
        Sprite exit = menu.getSpriteByName("exit");
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, exit, new MouseCrash(new Quit()));

        //character selection action
        Sprite back = characterSelection.getSpriteByName("back");
        Action backToMenu = new MouseCrash(new ChangeScene(menu, "menu"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, back, backToMenu);

        //after loading start game
        audioEngine.loop("menu", Clip.LOOP_CONTINUOUSLY);
        director.setScene(menu);
        try {
            director.getThread().join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static Scene createCharacterSelectionScene() throws IOException {
        //init Sprite
        Sprite rat = new Sprite("resource/image/rat.png", new Point(40, 250));
        Sprite raccoon = new Sprite("resource/image/raccoon.png", new Point(180, 250));
        Sprite dog = new Sprite("resource/image/dog.png", new Point(390, 250));
        Sprite cat = new Sprite("resource/image/cat.png", new Point(630, 250));
        Sprite fox = new Sprite("resource/image/fox.png", new Point(830, 250));
        Sprite roach = new Sprite("resource/image/roach.png", new Point(1045, 250));

        Sprite back = new Sprite("resource/image/tab_back.png", new Point(1150, 600));
        Sprite background = new Sprite("resource/image/character_design.png");

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

        //init action
        Action selectRat = new MouseCrash(new SelectCharacter("machi"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, rat, selectRat);

        Action selectRaccoon = new MouseCrash(new SelectCharacter("natsu"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, raccoon, selectRaccoon);

        Action selectDog = new MouseCrash(new SelectCharacter("machi"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, dog, selectDog);

        Action selectCat = new MouseCrash(new SelectCharacter("cat"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, cat, selectCat);

        Action selectFox = new MouseCrash(new SelectCharacter("machi"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, fox, selectFox);

        Action selectRoach = new MouseCrash(new SelectCharacter("ladybug"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, roach, selectRoach);

        return characterSelection;
    }
}
