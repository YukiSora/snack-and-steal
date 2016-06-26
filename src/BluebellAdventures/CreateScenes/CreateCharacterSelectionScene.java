package BluebellAdventures.CreateScenes;

import java.awt.event.MouseEvent;
import java.io.IOException;

import BluebellAdventures.Actions.SelectCharacter;

import Megumin.Actions.Action;
import Megumin.Actions.Interact;
import Megumin.Actions.MouseCrash;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class CreateCharacterSelectionScene {
    public static Scene createCharacterSelectionScene() throws IOException {
        Interact interact = Interact.getInstance();
        //init Sprite
        Sprite rat = new Sprite("resource/image/rat.png", new Point(40, 250));
        Sprite raccoon = new Sprite("resource/image/raccoon.png", new Point(180, 250));
        Sprite dog = new Sprite("resource/image/dog.png", new Point(415, 250));
        Sprite cat = new Sprite("resource/image/cat.png", new Point(630, 250));
        Sprite fox = new Sprite("resource/image/fox.png", new Point(830, 250));
        Sprite roach = new Sprite("resource/image/roach.png", new Point(1045, 250));

        Sprite back = new Sprite("resource/image/tag_back.png", new Point(950, 650));
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
        characterSelection.setName("character selection");
        characterSelection.addLayer(tabLayer);
        characterSelection.addLayer(mapLayer, 0);

        //init action
        Action selectRat = new MouseCrash(new SelectCharacter("rat"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, rat, selectRat, "character selection");

        Action selectRaccoon = new MouseCrash(new SelectCharacter("raccoon"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, raccoon, selectRaccoon, "character selection");

        Action selectDog = new MouseCrash(new SelectCharacter("dog"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, dog, selectDog, "character selection");

        Action selectCat = new MouseCrash(new SelectCharacter("cat"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, cat, selectCat, "character selection");

        Action selectFox = new MouseCrash(new SelectCharacter("fox"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, fox, selectFox, "character selection");

        Action selectRoach = new MouseCrash(new SelectCharacter("roach"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, roach, selectRoach, "character selection");

        return characterSelection;
    }
}