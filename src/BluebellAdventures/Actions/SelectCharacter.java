package BluebellAdventures.Actions;

import java.io.IOException;
import javax.sound.sampled.Clip;

import BluebellAdventures.CreateScenes.CreateGameScene;
import BluebellAdventures.Main;

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
            Director.getInstance().setScene(Main.createLoadingScene("resource/image/menu_bg.jpeg"));
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
                AudioEngine.getInstance().loop("main", Clip.LOOP_CONTINUOUSLY);
            }
        }).start();
        super.update(sprite);
    }
}
