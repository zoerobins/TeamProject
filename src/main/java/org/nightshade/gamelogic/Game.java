package org.nightshade.gamelogic;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Game {

    private final int levelWidth = 120;
    private Renderer renderer;
    private Cloud cloud;
    private int cloudXPos = 0;
    private final Image background = new Image ("view/background.png");
    private final ArrayList<String> input = new ArrayList<>();
    private Client client;

    public Game(){

    }


    public void initGame(Stage stage){

        cloud = new Cloud();
        LevelGen level = new LevelGen(levelWidth);
        renderer = new Renderer();
        renderer.setHeight(720);
        renderer.setWidth(1280);
        Scene scene = new Scene(renderer.getGroup());
        stage.setScene(scene);
        stage.show();
        ArrayList<Sprite> platformSprites = level.createPlatformSprites();
        client = new Client();
        client.createSprite();
        checkForInput(scene);
        Image grass = new Image("view/Grass.png");
        Image clientImg = new Image("view/Body.png");

        System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                cloudXPos++;
                gameLoop(cloudXPos,platformSprites,grass,clientImg);
            }
        }.start();

    }

    private void checkForInput(Scene scene){

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !input.contains(code) )
                        input.add( code );
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });
    }

    private void moveClient(ArrayList<Sprite> platformSprites){
        if(client.isLive()) {
            if (input.contains("UP") && client.getClientSprite().getPositionY() >= 5) {
                client.jump();
            }

            if (input.contains("LEFT") && client.getClientSprite().getPositionX() >= 5) {
                client.moveX(-5, platformSprites);
            }

            if (input.contains("RIGHT") && client.getClientSprite().getPositionX() <= (levelWidth*60) - 5) {
                client.moveX(5,platformSprites);
            }

            if (client.getVelocity().getY() < 10) {
                client.setVelocity(client.getVelocity().add(0,1));
            }

            /*
            //LEAVE COMMENTED UNTIL ENEMIES ARE ADDED
            for (Node enemy : enemies) {
                if (player.getClientNode().getBoundsInParent().intersects(enemy.getBoundsInParent()) && player.isLive()) {
                    player.kill(root,player.getClientNode());
                }
            }
            */
            client.moveY((int)client.getVelocity().getY(),platformSprites);

        }

    }


    private void gameLoop(int cloudXPos, ArrayList<Sprite> platformSprites, Image grass, Image clientImg){
        renderer.drawImage(background, 0,0);
        for (Sprite platformSprite : platformSprites) {
            renderer.drawImage(grass, platformSprite.getPositionX(), platformSprite.getPositionY());

        }
        cloud.showCloud(renderer,cloudXPos,30);
        moveClient(platformSprites);
        client.displaySprite(renderer,clientImg,client.getClientSprite());
    }

}
