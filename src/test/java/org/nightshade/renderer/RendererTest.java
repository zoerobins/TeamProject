package org.nightshade.renderer;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
    public void testDrawImage() {
        Image cat = new Image("cat.png");
        Image circle = new Image("circle.png");
        renderer.drawImage(cat, 100, 100);
        renderer.drawImage(circle, 10, 10, 50, 50);
    }

    @Test
    public void testDrawRectangle() {
        renderer.setWidth(1000);
        renderer.setHeight(1000);
        renderer.drawRectangle(100, 100, 200, 200, Color.BLUE);
    }

    @Test
    public void testDrawCircle() {
        renderer.drawCircle(100, 100, 200, Color.RED);
    }

    @Test
    public void testSetHeightWidth() {
        renderer.setHeight(1000);
        renderer.setWidth(100);
    }
}
