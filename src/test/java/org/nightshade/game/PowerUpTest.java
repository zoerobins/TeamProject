package org.nightshade.game;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nightshade.renderer.Renderer;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class PowerUpTest {
    PowerUp powerUp;
    Image powerUpImage;
    Renderer renderer;

    @Start
    public void start(Stage stage) {
        Image powerUpImage = new Image("img/game/powerup.png");
        this.powerUpImage = powerUpImage;
        this.powerUp = new PowerUp(powerUpImage,50,50);
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testGetAbility(){
        Assertions.assertNotNull(powerUp.getAbility());
    }
    @Test
    public void testGetCollected(){
        Assertions.assertNotNull(powerUp.getCollected());
    }

    @Test
    public void testCollect(){
        Boolean collected = powerUp.getCollected();
        powerUp.collect();
        Assertions.assertNotEquals(collected,powerUp.getCollected());

    }

    @Test
    public void testAssignRandomAbility(){
        Ability originalAbility = powerUp.getAbility();
        for (int i = 0; i < 20; i ++){
            powerUp = new PowerUp(powerUpImage,50,50);
            if (powerUp.getAbility() != originalAbility){
                break;
            }else if((i == 19) && (powerUp.getAbility() == originalAbility)){
                Assertions.fail();
            }
        }
    }

}