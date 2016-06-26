package BluebellAdventures.CreateScenes;

import java.io.IOException;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Graphics2D;

import BluebellAdventures.Characters.Character;
import BluebellAdventures.Actions.ChangeScene;

import Megumin.Actions.Action;
import Megumin.Actions.Interact;
import Megumin.Actions.MouseCrash;
import Megumin.Nodes.Director;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;


public class CreateGameOverScene{
    private static Interact interact;

	public static Scene createGameOverScene(String backgroundImage, Character player, Boolean result) throws IOException {
        interact = Interact.getInstance();

        //init sprite
        Sprite background = new Sprite(backgroundImage);

        Sprite submitHighScore = new Sprite("resource/image/tag_single.png", new Point(470, 450));
        
        
        Sprite mainMenu = new Sprite("resource/image/tag_single.png", new Point(470, 580));
        Scene menu = CreateMenuScene.createMenuScene();
        Action backToMenu = new MouseCrash(new ChangeScene(menu, "menu"));

        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, mainMenu, backToMenu, "game over");

        Sprite printStats = new Sprite() {
            public void render(Graphics2D g) {
                g.setFont(new Font("TimesRoman", Font.BOLD, 35));
                g.setColor(Color.white);
                g.drawString("Score: " + player.getSnackScore(), 560, 430);
                super.render(g);
            }
        };

        //init layer
        Layer backgroundLayer = new Layer();
        backgroundLayer.addSprite(background);

        Layer tabLayer = new Layer();
        if (result){
            tabLayer.addSprite(submitHighScore);
        }
        tabLayer.addSprite(mainMenu);
        tabLayer.addSprite(printStats);

        //init scene
        Scene loading = new Scene();
        loading.setName("game over");
        loading.addLayer(backgroundLayer);
        loading.addLayer(tabLayer);

        return loading;
    }
}