package org.nightshade.multiplayer;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
public class GameTest {

    Renderer renderer;
    org.nightshade.multiplayer.Game game;
    ArrayList<org.nightshade.multiplayer.Sprite> platformSprites;
    ArrayList<Sprite> movingPlatformSprites;
    org.nightshade.multiplayer.Level level;


    @Start
    public void start(Stage stage) throws IOException {
        game = new Game(stage, new GameClient("A"),new ArrayList<>(), new Level(120, new ArrayList<ArrayList<Node>>()), new Client());
        level = new Level(120, new ArrayList<ArrayList<Node>>());
        renderer = new Renderer(1280,720);
    }

    @Test
    public void testFullGame() {

        Image enemy = new Image("img/game/enemy.png");
        Image clientImg = new Image("img/game/player.png");
        Image grass = new Image("Grass.png");
        Image ground = new Image("Dirt.png");
        Image end = new Image("EndNode.png");
        Image aiImg = new Image("AIBody.png");
        //  platformSprites = level.createPlatformSprites();


        System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                //   game.loop();
            }
        }.start();
    }

}

