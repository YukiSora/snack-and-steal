package BluebellAdventures.Actions;

import java.io.IOException;

import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

import BluebellAdventures.CreateScenes.CreateGameOverScene;

public class GameOver extends Effect{
	private int score;

	GameOver(int score){
		this.score = score;
	}

    @Override
    public void update(Sprite sprite){
    	AudioEngine.getInstance().stopAll();
        AudioEngine.getInstance().play("victory");

        try{
        	Scene gameOver = CreateGameOverScene.createGameOverScene("resource/image/win.png", score);
        	Director.getInstance().setScene(gameOver);
        } catch(IOException e){
        	e.printStackTrace();
        }
  
    }
}