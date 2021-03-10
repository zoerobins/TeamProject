package org.nightshade.game;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class GameTest {

    Renderer renderer;
    Cloud cloud;
    Game game;
    Stage tempStage;

    @Start
    public void start(Stage stage) {
        game=new Game();
        tempStage=stage;
    }

    @Test
    public void test() {
        System.out.println("Hello World");
    }


}
