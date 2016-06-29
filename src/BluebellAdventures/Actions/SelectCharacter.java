package BluebellAdventures.Actions;

import java.io.IOException;

import BluebellAdventures.CreateScenes.CreateGameScene;
import BluebellAdventures.CreateScenes.CreateLoadingScene;

import Megumin.Actions.Action;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Sprite;

public class SelectCharacter extends Action {
    private String playerImageName;

    public SelectCharacter(String playerImageName) {
        this.playerImageName = playerImageName;
    }

    @Override
    public void update(Sprite sprite) {
        try {
            Director.getInstance().setScene(CreateLoadingScene.createLoadingScene("resource/image/loading_screen.png"));
        } catch (IOException e) {
            System.exit(1);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Director.getInstance().setScene(CreateGameScene.createGameScene(playerImageName));
                } catch (IOException e) {
                    System.exit(1);
                }
                AudioEngine.getInstance().loop("main");
            }
        }).start();
        super.update(sprite);
    }
}
