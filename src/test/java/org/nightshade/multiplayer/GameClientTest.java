package org.nightshade.multiplayer;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.game.Client;
import org.nightshade.game.Game;
import org.nightshade.game.Level;
import org.nightshade.game.Sprite;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class GameClientTest {
    Renderer renderer;
    org.nightshade.game.Level level;
    org.nightshade.game.Game game;
    GameClient client;
    int speed;
    ArrayList<org.nightshade.game.Sprite> platformSprites;

    @Start
    public void start(Stage stage) {
        speed=10;
        client = new GameClient("A");
        game = new Game(stage);
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
        level = new Level(100);
    }


    @Test
    public void testGetVelocity(){
        Assertions.assertNotNull(client.getVelocity());
    }

    @Test
    public void testSetVelocity(){
        client.setVelocity(new Point2D(50,20));
        Assertions.assertNotNull(client.getVelocity());
    }

    @Test
    public void testGetClientSprite(){
        Assertions.assertNotNull(client.getSprite());
    }

    @Test
    public void testJump(){
        GameClient clientNoJump = new GameClient("A");
        GameClient clientJump = new GameClient("B");
        clientJump.jump();
        Assertions.assertNotEquals(clientJump.getVelocity(),clientNoJump.getVelocity());
    }

    @Test
    public void testIsLive(){
        Assertions.assertTrue(client.isAlive());
    }


    @Test
    public void testSetCanJump(){
        client.setCanJump(false);
        client.setCanJump(true);
    }

}

