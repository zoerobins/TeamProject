package org.nightshade.renderer;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class RendererTest {

    Renderer renderer;

    @Start
    public void start(Stage stage) {
        renderer = new Renderer();
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void test() {
        System.out.println("Hello World");
    }

    @Test
    public void testRenderImage() {
        Image cat = new Image("cat.png");
        Image circle = new Image("circle.png");
        renderer.renderImage(cat, 100, 100);
        renderer.renderImage(circle, 10, 10, 50, 50);
    }

    @Test
    public void testSetHeightWidth() {
        renderer.setHeight(1000);
        renderer.setWidth(100);
    }
}
