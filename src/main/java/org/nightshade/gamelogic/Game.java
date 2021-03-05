package org.nightshade.gamelogic;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Game {

    private Renderer renderer;
    private Cloud cloud;
    private LevelGen level;
    private int cloudXPos = 0;
    private Image background = new Image ("view/background.png");
    public Game(){

    }


    public void initGame(Stage stage){
        cloud = new Cloud();
        level = new LevelGen(120);
        renderer = new Renderer();
        renderer.setHeight(720);
        renderer.setWidth(1280);
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
        ArrayList<Sprite> platformSprites = level.createPlatformSprites();
        Image img = new Image("view/Grass.png");



        System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                cloudXPos++;
                gameLoop(cloudXPos,platformSprites,img);
            }
        }.start();

    }

    private void gameLoop(int cloudXPos,ArrayList<Sprite> platformSprites,Image img){
        renderer.drawImage(background, 0,0);
        for (Sprite platformSprite : platformSprites) {
            renderer.drawImage(img, platformSprite.getPositionX(), platformSprite.getPositionY());
        }
        cloud.showCloud(renderer,cloudXPos,30);
    }

}
