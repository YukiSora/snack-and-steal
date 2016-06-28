package BluebellAdventures.Actions;

import java.io.IOException;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.CreateScenes.CreateGameOverScene;

import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

public class GameOver extends Effect {
    private boolean victory;

    GameOver(boolean victory) {
        this.victory = victory;
    }

    @Override
    public void update(Sprite sprite){
        String backgroundImage;
        String audio;
        if (victory) {
            audio = "victory";
            backgroundImage = "resource/image/win.png";
        } else {
            audio = "nervous";
            backgroundImage = "resource/image/lose.png";
        }

        try{
            new ChangeScene(CreateGameOverScene.createGameOverScene(backgroundImage, sprite, victory), audio).update(null);
        } catch(IOException e){
            System.exit(1);
        }
    }
}
