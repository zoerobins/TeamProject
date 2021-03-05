package org.nightshade.gamelogic;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.nightshade.renderer.Renderer;

public class Game {

    private Renderer renderer;
    private Cloud cloud;
    private int cloudXPos = 0;
    private Image background = new Image ("view/background.jpg");
    public Game(Stage stage){

    }


    public void initGame(Stage stage){
        cloud = new Cloud();
        renderer = new Renderer();
        renderer.setHeight(720);
        renderer.setWidth(1280);
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();

        System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                cloudXPos++;
                gameLoop(cloudXPos);
            }
        }.start();

    }

    private void gameLoop(int cloudXPos){
        renderer.drawImage(background, 0,0);
        cloud.showCloud(renderer,cloudXPos,30);
    }



}
