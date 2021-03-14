package org.nightshade.game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class AILogicTest {
    Renderer renderer;
    LevelGen levelGen;
    AI ai;
    AILogic aiLogic;
    int speed = 5;
    ArrayList<Sprite> platformSprites;

    @Start
    public void start(Stage stage) {
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
        aiLogic = new AILogic();
        ai = new AI(speed);
        levelGen = new LevelGen(120);
    }

    @Test
    public void testMoveChar(){
       platformSprites = levelGen.createPlatformSprites(renderer);
       aiLogic.moveChar(ai,platformSprites);
    }
}
