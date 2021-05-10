package org.nightshade.multiplayer;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.networking.Client;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class GameHandlerTest {
        GameHandler gh;
    @Start
    public void start(Stage stage) throws IOException {
        gh =  new GameHandler(new Stage(),"A", new Client(),new ArrayList<>());

    }

    @Test
    public void testMakeLevel() {
        Assertions.assertNotNull(gh.makeLevel(20));
    }

}
