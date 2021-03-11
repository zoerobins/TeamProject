package org.nightshade.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Game {

    private final int levelWidth = 120;
    private final int blockWidth = 60;
    private int cloudXPos;
    private Parallax background;
    private final ArrayList<String> input = new ArrayList<>();
    private ArrayList<Sprite> platformSprites;
    private ArrayList<Sprite> enemySprites;
    private Renderer renderer;
    private Client client;
    private AI ai;
    private AILogic AILogic;
    private Sprite cloud;
    private final Image cloudImage = new Image("view/dark.png");

    public void initGame(Stage stage){
        cloudXPos=-400;
        cloud = new Sprite(new Image("view/dark.png"),-300,50);
        background = new Parallax();
        LevelGen level = new LevelGen(levelWidth);
        renderer = new Renderer();
        renderer.setHeight(720);
        renderer.setWidth(levelWidth*blockWidth);
        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane,1280,720);
        stage.setScene(scene);
        stage.show();
        platformSprites = level.createPlatformSprites();
        enemySprites = level.getEnemySprites();
        AILogic=new AILogic();
        client = new Client();
        ai = new AI(3);
        client.createSprite();
        ai.createSprite();
        checkForInput(scene);
        Image grass = new Image("view/Grass.png");
        Image clientImg = new Image("view/Body.png");
        Image enemy = new Image("view/enemy.png");

        System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gameLoop(platformSprites,grass,enemy,clientImg);

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

            if (input.contains("RIGHT") && client.getClientSprite().getPositionX() <= (levelWidth*blockWidth) - 5) {
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

    private void gameLoop(ArrayList<Sprite> platformSprites, Image grass,Image enemy, Image clientImg){
        background.moveParallax();
        background.drawParallax(renderer);
        for (Sprite platformSprite : platformSprites) {
            renderer.drawImage(grass, platformSprite.getPositionX(), platformSprite.getPositionY());
        }
        for (Sprite enemySprite : enemySprites) {
            renderer.drawImage(enemy,enemySprite.getPositionX(),enemySprite.getPositionY());
        }


        cloudXPos=moveCloud(cloudXPos);
        renderer.drawImage(cloudImage,cloudXPos,0);

        moveClient(platformSprites);
        client.displaySprite(renderer,clientImg,client.getClientSprite());
        ai.displaySprite(renderer,clientImg,ai.getAISprite());
        if ((-1*renderer.getTransLateX())+700<client.getClientSprite().getPositionX()){
            renderer.setTransLateX((int) (renderer.getTransLateX()+((-1*renderer.getTransLateX())+700-client.getClientSprite().getPositionX())));
        } else{
            renderer.setTransLateX((int) (renderer.getTransLateX()));
        }
        AILogic.moveChar(ai, platformSprites);
    }

    private int moveCloud(int cloudXPos){
        int cloudXPosNew=cloudXPos+2;

        if (client.getClientSprite().getPositionX()-cloudXPos>1000){
            cloudXPosNew = client.getClientSprite().getPositionX()-1000;
        }

        return cloudXPosNew;

    }


}
