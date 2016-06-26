package BluebellAdventures.CreateScenes;

import Megumin.Nodes.Scene;

public class CreateGameOverScene{
	public static Scene createLoadingScene(String backgroundImage) throws IOException {
        //init sprite
        Sprite background = new Sprite(backgroundImage);

        //init layer
        Layer mapLayer = new Layer();
        mapLayer.addSprite(background);

        //init scene
        Scene loading = new Scene();
        loading.setName("game_over");
        loading.addLayer(mapLayer);

        return loading;
    }
}