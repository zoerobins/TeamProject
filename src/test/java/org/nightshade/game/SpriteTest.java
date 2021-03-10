package org.nightshade.game;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class SpriteTest {

    Renderer renderer;
    LevelGen level;
    Game game;
    Stage tempStage;
    Sprite sprite;
    Image image;

    @Start
    public void start(Stage stage) {
        game = new Game();
        renderer=new Renderer();
        level = new LevelGen(120);
        tempStage = stage;
        Image image = new Image("Grass.png");
        sprite = new Sprite(image,20,20);

    }

    @Test
    public void test() {
        System.out.println("Hello World");
    }

    @Test
    public void drawSprite(){
        sprite.setPosition(0,0);
        renderer.drawImage(image,0,0);
    }

}
