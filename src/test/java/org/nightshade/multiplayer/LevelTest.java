package org.nightshade.multiplayer;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.multiplayer.Game;
import org.nightshade.multiplayer.Level;
import org.nightshade.multiplayer.MovingPlatform;
import org.nightshade.multiplayer.Sprite;
import org.nightshade.networking.Client;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class LevelTest {

    Renderer renderer;
    org.nightshade.multiplayer.Level level;
    org.nightshade.multiplayer.Game game;
    Stage tempStage;
    org.nightshade.multiplayer.Sprite sprite;
    ArrayList<org.nightshade.multiplayer.Sprite> platformSprites;
    ArrayList<MovingPlatform> movingPlatforms;


    @Start
    public void start(Stage stage) throws IOException {
        game = new Game(stage, new GameClient("A"),new ArrayList<>(),new Level(120, new ArrayList<ArrayList<Node>>()),new Client());
        sprite = new Sprite(new Image("Grass.png"), 20, 20);
        level = new org.nightshade.multiplayer.Level(120, new ArrayList<ArrayList<Node>>());
        tempStage = stage;
        renderer = new Renderer(1280, 720);
        level = new Level(120, new ArrayList<ArrayList<Node>>() );

        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

}
