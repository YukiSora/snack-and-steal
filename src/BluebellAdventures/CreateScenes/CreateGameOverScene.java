package BluebellAdventures.CreateScenes;

import java.io.IOException;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Graphics2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;

import BluebellAdventures.Actions.ChangeScene;
import BluebellAdventures.Characters.Character;
import BluebellAdventures.CreateScenes.CreateMenuScene;

import Megumin.Actions.Action;
import Megumin.Actions.Interact;
import Megumin.Actions.MouseCrash;
import Megumin.Database.Database;
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

        // [1] Submit - Highscore
        Sprite submitHighScore = new Sprite("resource/image/tag_highscore.png", new Point(600, 480));
        Action saveHighScore = new MouseCrash(new Action(){
            @Override
            public void update(Sprite sprite) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(new java.util.Date());

                try {
                    Database.getInstance().update("INSERT INTO Records (Score, Date_Time) VALUE('"+ player.getSnackScore() +"','"+ currentTime +"')");
                    System.out.println("Score Saved: " + player.getSnackScore());

                    Director.getInstance().setScene(CreateMenuScene.createMenuScene());
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (IOException e){
                    System.out.println(e);
                }

                
            }
        });

        if (result){
            interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, submitHighScore, saveHighScore, "save score");
        }

        // [2] Back to Main Menu
        Sprite mainMenu = new Sprite("resource/image/tag_winback.png", new Point(650, 610));
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
        if (result){
            loading.setName("save score");
        }
        loading.addLayer(backgroundLayer);
        loading.addLayer(tabLayer);

        return loading;
    }

}