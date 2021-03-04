package org.nightshade.audio;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.effect.Light;
import org.junit.jupiter.api.Test;


import java.net.URISyntaxException;

public class SpotEffectsTest {

    @Test
    public void testJumpSound() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new JFXPanel();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run(){
                        SpotEffects spotfx = new SpotEffects();
                        try {
                            spotfx.jumpSound();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });


    }
}
