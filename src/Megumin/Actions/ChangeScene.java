package Megumin.Actions;

import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

public class ChangeScene extends Action {
    private Scene scene;
    public ChangeScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void update(Sprite sprite) {
        Director.getInstance().setScene(scene);
        for (Action action : getActions()) {
            sprite.runAction(action);
        }
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
