package BluebellAdventures.CreateScenes;

import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import BluebellAdventures.Characters.Character;
import Megumin.Nodes.Director;
import Megumin.Nodes.Layer;
import Megumin.Nodes.Scene;
import Megumin.Nodes.Sprite;
import Megumin.Point;

public class CreateGameOverScene{
	public static Scene createGameOverScene(String backgroundImage, int score) throws IOException {
        //init sprite
        Sprite background = new Sprite(backgroundImage);
        Sprite submitHighScore = new Sprite("resource/image/tag_single.png", new Point(470, 450));
        Sprite mainMenu = new Sprite("resource/image/tag_single.png", new Point(470, 580));

        Sprite printStats = new Sprite() {
            public void render(Graphics2D g) {
                g.setFont(new Font("TimesRoman", Font.BOLD, 35));
                g.setColor(Color.white);
                g.drawString("Score: " + score, 560, 430);
                System.out.println("Score: " + score);
                super.render(g);
            }
        };

        //init layer
        Layer backgroundLayer = new Layer();
        backgroundLayer.addSprite(background);

        Layer tabLayer = new Layer();
        tabLayer.addSprite(submitHighScore);
        tabLayer.addSprite(mainMenu);
        tabLayer.addSprite(printStats);

        //init scene
        Scene loading = new Scene();
        loading.setName("game_over");
        loading.addLayer(backgroundLayer);
        loading.addLayer(tabLayer);

        return loading;
    }
}