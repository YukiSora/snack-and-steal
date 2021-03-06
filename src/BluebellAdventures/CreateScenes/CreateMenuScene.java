package BluebellAdventures.CreateScenes;

import java.io.IOException;

import Megumin.Nodes.Director;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class CreateMenuScene {
    public static Scene createMenuScene() throws IOException {
        //init sprite
        Sprite singlePlayer = new Sprite("resource/image/tag_single.png", new Point(0, 100));
        Sprite multiPlayer = new Sprite("resource/image/tag_multi.png", new Point(0, 250));
        Sprite setting = new Sprite("resource/image/tag_setting.png", new Point(00, 400));
        Sprite exit = new Sprite("resource/image/tag_quit.png", new Point(0, 550));
        Sprite background = new Sprite("resource/image/character_design.png");
        singlePlayer.setName("single player");
        exit.setName("exit");

        //init layer
        Layer tabLayer = new Layer();
        tabLayer.addSprite(singlePlayer);
        tabLayer.addSprite(multiPlayer);
        tabLayer.addSprite(setting);
        tabLayer.addSprite(exit);
        Layer backgroundLayer = new Layer();
        backgroundLayer.addSprite(background);

        //init scene
        Scene menu = new Scene();
        menu.setName("menu");
        menu.addLayer(tabLayer);
        menu.addLayer(backgroundLayer, 0);

        return menu;
    }
}
