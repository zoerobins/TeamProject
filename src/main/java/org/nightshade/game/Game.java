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
    public static int xViewCoordinate = 0;

    private final ArrayList<String> input = new ArrayList<>();
    private ArrayList<Sprite> platformSprites;
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    private Renderer renderer;
    private Client client;
    private AI ai;
    private AILogic AILogic;
    private Sprite cloud;
    private Parallax background;

    private final Image cloudImage = new Image("view/dark.png");

    public void initGame(Stage stage){
        cloud = new Sprite(cloudImage,-300,50);
        cloud.setPositionX(-400);
        background = new Parallax();
        LevelGen levelGen = new LevelGen(levelWidth);
        renderer = new Renderer();
        renderer.setHeight(720);
        renderer.setWidth(levelWidth*blockWidth);
        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane,1280,720);
        stage.setScene(scene);
        stage.show();
        platformSprites = levelGen.createPlatformSprites(renderer);
        AILogic=new AILogic();
        client = new Client();
        ai = new AI(3);
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
                client.moveX(-5, platformSprites,enemies);
            }

            if (input.contains("RIGHT") && client.getClientSprite().getPositionX() <= (levelWidth*blockWidth) - 5) {
                client.moveX(5,platformSprites,enemies);
            }

            if (client.getVelocity().getY() < 10) {
                client.setVelocity(client.getVelocity().add(0,1));
            }

            client.moveY((int)client.getVelocity().getY(),platformSprites,enemies);

        }
    }

    private void gameLoop(ArrayList<Sprite> platformSprites, Image grass,Image enemy, Image clientImg){
        background.moveParallax();
        background.drawParallax(renderer);
        for (Sprite platformSprite : platformSprites) {
            renderer.drawImage(grass, platformSprite.getPositionX(), platformSprite.getPositionY());
        }

        moveCloud();
        renderer.drawImage(cloudImage,cloud.getPositionX(),0);

        if(client.isLive()) {
            moveClient(platformSprites);
            client.displaySprite(renderer, clientImg, client.getClientSprite());
            if (client.getClientSprite().intersects(cloud.getPositionX()-200,cloud.getPositionY(),(int)cloud.getWidth(),(int)cloud.getHeight())){
                client.kill();
            }
        }

        ai.displaySprite(renderer,clientImg,ai.getAISprite());
        for (Enemy thisEnemy : enemies) {
            thisEnemy.moveEnemy();
            thisEnemy.displaySprite(renderer,enemy, thisEnemy.getEnemySprite());
        }
        if ((-1*renderer.getTransLateX())+700<client.getClientSprite().getPositionX()){
            renderer.setTransLateX((int) (renderer.getTransLateX()+((-1*renderer.getTransLateX())+700-client.getClientSprite().getPositionX())));
        } else{
            renderer.setTransLateX((int) (renderer.getTransLateX()));
        }
        xViewCoordinate= (int) (-1*renderer.getTransLateX());
        AILogic.moveChar(ai, platformSprites);
    }

    private void moveCloud(){
        int cloudXPosNew=cloud.getPositionX()+2;

        if (client.getClientSprite().getPositionX()-cloud.getPositionX()>1000){
            cloudXPosNew = client.getClientSprite().getPositionX()-1000;
        }
        cloud.setPositionX(cloudXPosNew);
    }






}
