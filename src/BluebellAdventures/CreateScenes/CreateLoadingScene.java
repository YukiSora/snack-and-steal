package BluebellAdventures.CreateScenes;

import java.io.IOException;

import Megumin.Nodes.Director;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

public class CreateLoadingScene {
    public static Scene createLoadingScene(String backgroundImage) throws IOException {
        //init sprite
        Sprite background = new Sprite(backgroundImage);

        //init layer
        Layer backgroundLayer = new Layer();
        backgroundLayer.addSprite(background);

        //init scene
        Scene loading = new Scene();
        loading.setName("loading");
        loading.addLayer(backgroundLayer);

        return loading;
    }
}
