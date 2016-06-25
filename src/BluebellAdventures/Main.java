package BluebellAdventures;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.sound.sampled.Clip;

import BluebellAdventures.Actions.ChangeScene;
import BluebellAdventures.Actions.SelectCharacter;
import BluebellAdventures.Actions.Quit;
import BluebellAdventures.CreateScenes.CreateCharacterSelectionScene;
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
        Scene characterSelection = CreateCharacterSelectionScene.createCharacterSelectionScene();

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
}
