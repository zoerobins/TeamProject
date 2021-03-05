package org.nightshade.gamelogic;


import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class LevelGenTest {

    Renderer renderer;
    LevelGen level;
    Game game;
    Stage tempStage;
    Sprite sprite;

    @Start
    public void start(Stage stage) {
        game = new Game();
        sprite = new Sprite(new Image("Grass.png"),20,20);
        level = new LevelGen(120);
        tempStage = stage;
        renderer = new Renderer();
    }

    @Test
    public void test() {
        System.out.println("Hello World");
    }

    @Test
    public void drawPlatformTest(){
        renderer.drawImage(new Image("Grass.png"), sprite.getPositionX(), sprite.getPositionY());
    }

}
