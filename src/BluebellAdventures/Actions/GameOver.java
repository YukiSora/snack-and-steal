package BluebellAdventures.Actions;

import java.io.IOException;


import Megumin.Actions.Action;
import Megumin.Actions.Effect;
import Megumin.Audio.AudioEngine;
import Megumin.Nodes.Director;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.CreateScenes.CreateGameOverScene;

public class GameOver extends Effect{

    @Override
    public void update(Sprite sprite){
    	AudioEngine.getInstance().stopAll();
        AudioEngine.getInstance().play("victory");

        //Character player = (Character) getSprite();

        String resultDirectory;
        Boolean result;

        Character player = ((Character) Director.getInstance().getScene().getSpriteByName("player"));

        if(player.getHp() < 2){
            result = false;
            resultDirectory = "resource/image/lose.png";
        } else {
            result = true;
            resultDirectory = "resource/image/win.png";
        }

        try{
        	Scene gameOver = CreateGameOverScene.createGameOverScene(resultDirectory, player, result);
        	Director.getInstance().setScene(gameOver);
        } catch(IOException e){
        	e.printStackTrace();
        }
  
    }
}