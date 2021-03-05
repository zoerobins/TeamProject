package org.nightshade.gamelogic;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.logging.Level;

@ExtendWith(ApplicationExtension.class)
public class LevelGenTest {

    Renderer renderer;
    Cloud cloud;
    LevelGen level;
    Game game;
    Stage tempStage;

    @Start
    public void start(Stage stage) {
        game = new Game();
        level = new LevelGen();
        tempStage = stage;
    }

    @Test
    public void test() {
        System.out.println("Hello World");
    }

    @Test
    public void drawPlatformTest(){
        level.drawPlatform(0,0,120,60, Color.BLACK);
    }

}
