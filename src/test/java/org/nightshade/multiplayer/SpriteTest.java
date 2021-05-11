package org.nightshade.multiplayer;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.multiplayer.Game;
import org.nightshade.multiplayer.Level;
import org.nightshade.multiplayer.Sprite;
import org.nightshade.networking.Client;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class SpriteTest {

    Renderer renderer;
    org.nightshade.multiplayer.Level level;
    org.nightshade.multiplayer.Game game;

    @Start
    public void start(Stage stage) throws IOException {
        game = new Game(stage,new GameClient("A"),new ArrayList<>(),new Level(120,new ArrayList<>()),new Client());
        renderer = new Renderer();
        level = new Level(120,new ArrayList<>());
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void test() {
        System.out.println("Hello World");
    }

    @Test
    public void testDrawSprite(){
        Image image = new Image("Grass.png");
        org.nightshade.multiplayer.Sprite sprite = new org.nightshade.multiplayer.Sprite(image,40,40);
        sprite.setPosition(100,100);
        renderer.drawImage(image, sprite.getX(), sprite.getY());
    }

    @Test
    public void testIntersects(){
        Image image = new Image("Grass.png");
        org.nightshade.multiplayer.Sprite spriteOne = new org.nightshade.multiplayer.Sprite(image,40,40);
        org.nightshade.multiplayer.Sprite spriteTwo = new org.nightshade.multiplayer.Sprite(image,70,40);
        renderer.drawImage(image,0,0);
        Assertions.assertTrue(spriteOne.intersects(spriteTwo));
        spriteOne.setX(800);
        Assertions.assertFalse(spriteOne.intersects(spriteTwo));
    }

    @Test
    public void testGetHeight(){
        Image image = new Image("Grass.png");
        //image is of height 60
        org.nightshade.multiplayer.Sprite sprite = new org.nightshade.multiplayer.Sprite(image,0,0);
        Assertions.assertEquals(60,sprite.getHeight());
    }

    @Test
    public void testGetWidth(){
        Image image = new Image("Grass.png");
        //image is of width 60
        org.nightshade.multiplayer.Sprite sprite = new org.nightshade.multiplayer.Sprite(image,0,0);
        Assertions.assertEquals(60,sprite.getWidth());
    }

    @Test
    public void testGetPositionX(){
        Image image = new Image("Grass.png");
        org.nightshade.multiplayer.Sprite sprite = new org.nightshade.multiplayer.Sprite(image,0,0);
        Assertions.assertEquals(0,sprite.getX());
        sprite.setX(50);
        Assertions.assertEquals(50,sprite.getX());
    }

    @Test
    public void testGetPositionY(){
        Image image = new Image("Grass.png");
        org.nightshade.multiplayer.Sprite sprite = new org.nightshade.multiplayer.Sprite(image,0,0);
        Assertions.assertEquals(0,sprite.getY());
        sprite.setY(50);
        Assertions.assertEquals(50,sprite.getY());
    }

    @Test
    public void testGetAnimatedImageUsingImageConstructor() {
        Image image = new Image("cat.png");
        org.nightshade.multiplayer.Sprite sprite = new Sprite(image, 0, 0);
        Assertions.assertNull(sprite.getAnimatedImage());
    }
}

