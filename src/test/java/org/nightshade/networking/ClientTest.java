package org.nightshade.networking;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.game.Game;
import org.nightshade.game.Level;
import org.nightshade.game.Sprite;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class ClientTest {

    Client client1;
    Client client2;
    //Server server;

    @Start
    public void start(Stage stage)  throws IOException {
        //server = new Server(2222);
        client1 = new Client();
        client2 = new Client("client2", "127.0.0.1", 2222);
    }

    @Test
    public void testGetname(){
        Assertions.assertEquals("client2", client2.getName());
    }

    @Test
    public void testGetlogic(){
        Assertions.assertNotEquals(null, client1.getClientLogic());
        Assertions.assertNotEquals(null, client1.getClientLogic());
    }

}
