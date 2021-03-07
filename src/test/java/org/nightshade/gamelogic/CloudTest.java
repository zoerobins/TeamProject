package org.nightshade.gamelogic;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class CloudTest {

    Renderer renderer;
    Cloud cloud;
    Game game;
    Client client;

    @Start
    public void start(Stage stage) {
        client = new Client();
        renderer = new Renderer();
        game = new Game();
        cloud = new Cloud();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void test() {
        System.out.println("Hello World");
    }

    @Test
    public void testShowCloud() {
        cloud.showCloud(renderer,client,0,0);
    }

}
