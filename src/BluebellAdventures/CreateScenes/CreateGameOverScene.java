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

public class CreateGameOverScene {
    public static Scene createGameOverScene(String backgroundImage, Sprite sprite, boolean victory) throws IOException {
        Interact interact = Interact.getInstance();
        Character player = (Character)sprite;

        //init sprite
        Sprite background = new Sprite(backgroundImage);

        Sprite printStats = new Sprite() {
            @Override
            public void render(Graphics2D g) {
                g.setFont(new Font("TimesRoman", Font.BOLD, 35));
                g.setColor(Color.white);
                g.drawString("Score: " + player.getSnackScore(), 560, 430);
            }
        };

        // [1] Submit - Highscore
        Sprite submitHighScore = null;
        if (victory) {
            submitHighScore = new Sprite("resource/image/tag_highscore.png", new Point(600, 480));
            Action saveHighScore = new MouseCrash(new Action() {
                @Override
                public void update(Sprite sprite) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = sdf.format(new java.util.Date());

                    try {
                        Database.getInstance().update("INSERT INTO Records (Score, Date_Time) VALUE('" + player.getSnackScore() + "', '" + currentTime + "')");
                        new ChangeScene(CreateMenuScene.createMenuScene(), "menu").update(null);
                    } catch (SQLException e) {
                        System.out.println(e);
                        System.exit(1);
                    } catch (IOException e){
                        System.exit(1);
                    }
                }
            });
            interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, submitHighScore, saveHighScore, "game over");
        }

        // [2] Back to Main Menu
        Sprite mainMenu = new Sprite("resource/image/tag_winback.png", new Point(650, 610));
        Scene menu = CreateMenuScene.createMenuScene();
        Action backToMenu = new MouseCrash(new ChangeScene(menu, "menu"));
        interact.addEvent(MouseEvent.BUTTON1, Interact.ON_MOUSE_CLICK, mainMenu, backToMenu, "game over");

        //init layer
        Layer backgroundLayer = new Layer();
        backgroundLayer.addSprite(background);

        Layer tabLayer = new Layer();
        tabLayer.addSprite(printStats);
        if (victory) {
            tabLayer.addSprite(submitHighScore);
        }
        tabLayer.addSprite(mainMenu);

        //init scene
        Scene gameover = new Scene();
        gameover.setName("game over");
        gameover.addLayer(backgroundLayer);
        gameover.addLayer(tabLayer);

        return gameover;
    }
}
