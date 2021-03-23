package org.nightshade.game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class MovingPlatformTest {

    Renderer renderer;
    MovingPlatform movingPlatform;
    int speed;

    @Start
    public void start(Stage stage) {
        speed=5;
        movingPlatform = new MovingPlatform(50, 50, speed, true);
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testGetMPSprite() {
        Assertions.assertNotNull(movingPlatform.getmvSprite());
    }

    @Test
    public void testDisplaySprite() {
        movingPlatform.displaySprite(renderer, new Image("cat.png"), movingPlatform.getmvSprite());
    }

    @Test
    public void testMoveMP(){
        int oldPosition = movingPlatform.getmvSprite().getPositionX();
        movingPlatform.movePlatform();
        int newPosition = movingPlatform.getmvSprite().getPositionX();
        Assertions.assertEquals(speed,newPosition-oldPosition);
    }

}
