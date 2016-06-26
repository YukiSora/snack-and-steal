package BluebellAdventures.Actions;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Sprite;

public class GameOver extends Effect{
    @Override
    public void update(Sprite sprite) {
    	AudioEngine.getInstance().stopAll();
        AudioEngine.getInstance().play("victory");

        System.out.println("Game Over and You Suck Balls!");
    }
}