package org.nightshade.game;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.nightshade.ai.AI;
import org.nightshade.ai.AILogic;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Game {

    private final int levelWidth = 120;
    private final int blockWidth = 60;
    private int xViewCoordinate = 0;
    private int animationIndex = 0;
    private int gameTickCounter = 0;

    private ArrayList<AI> aiPlayers;
    private ArrayList<Image> lavaImages;

    private final ArrayList<String> input = new ArrayList<>();
    private ArrayList<Sprite> platformSprites;
    private ArrayList<Sprite> lavaSprites;
    private ArrayList<Sprite> groundSprites;
    private ArrayList<Sprite> endSprites;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Renderer renderer;
    private Client client;
    private AILogic aiLogic;
    private Sprite cloud;
    private Parallax parallax;
    private final Image cloudImage = new Image("view/GameComponents/dark.png");

    public void initGame(Stage stage, int aiCount, ArrayList<String> aiDifficulty) {
        cloud = new Sprite(cloudImage, -2300, 50);
        parallax = new Parallax();
        renderer = new Renderer();
        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane, 1280, 720);
        LevelGen levelGen = new LevelGen(levelWidth);
        aiLogic = new AILogic();
        client = new Client();

        aiPlayers = new ArrayList<>();
        for (int i = 0; i < aiCount; i++) {
            String difficulty = aiDifficulty.get(i);
            if (difficulty.equals("EASY")) {
                aiPlayers.add(new AI(3));
            } else if (difficulty.equals("MEDIUM")) {
                aiPlayers.add(new AI(4));
            } else {
                aiPlayers.add(new AI(5));
            }
        }

        cloud.setX(-1300);
        renderer.setHeight(720);
        renderer.setWidth(levelWidth * blockWidth);
        platformSprites = levelGen.createPlatformSprites();
        lavaSprites = levelGen.createLavaSprites();
        groundSprites = levelGen.createGroundSprites();
        enemies = levelGen.createEnemies();
        endSprites = levelGen.createEndSprites();

        stage.setScene(scene);
        stage.show();

        Image grass = new Image("view/GameComponents/DarkGrass.png");
        Image ground = new Image("view/GameComponents/Dirt.png");
        Image clientImg = new Image("view/GameComponents/Body.png");
        Image aiImg = new Image("view/GameComponents/AIBody.png");
        Image enemy = new Image("view/GameComponents/enemy.png");
        Image end = new Image("view/GameComponents/EndNode.png");

        lavaImages = new ArrayList<>();

        for (int i = 0; i < 17; i++) {
            lavaImages.add(new Image("view/GameComponents/Water/image " + (i + 1) + ".png"));
        }

        checkForInput(scene);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

      /*  KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.017), // 60FPS
                actionEvent -> gameLoop(platformSprites, gameTickCounter++, grass, ground, enemy, end, clientImg, aiImg)
        );

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();*/
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gameLoop(platformSprites, gameTickCounter++, grass, ground, enemy, end, clientImg, aiImg);
            }
        }.start();
    }

    private void checkForInput(Scene scene) {
        scene.setOnKeyPressed(
                keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                });

        scene.setOnKeyReleased(
                keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    input.remove(code);
                });
    }

    private void moveClient(ArrayList<Sprite> platformSprites) {
        if (client.isLive()) {
            if (input.contains("UP") && client.getClientSprite().getY() >= 5) {
                client.jump();
            }

            if (input.contains("LEFT") && client.getClientSprite().getX() >= 5) {
                client.moveX(-5, platformSprites, enemies, groundSprites);
            }

            if (input.contains("RIGHT") && client.getClientSprite().getX() <= (levelWidth * blockWidth) - 5) {
                client.moveX(5, platformSprites, enemies, groundSprites);
            }

            if (client.getVelocity().getY() < 10) {
                client.setVelocity(client.getVelocity().add(0, 1));
            }

            client.moveY((int) client.getVelocity().getY(), platformSprites, lavaSprites, enemies, groundSprites);

        }
    }

    public void gameLoop(ArrayList<Sprite> platformSprites, int gameTickCounter, Image grass, Image ground, Image enemy, Image end, Image clientImg, Image aiImg) {

        parallax.moveParallax();
        parallax.drawParallax(renderer, xViewCoordinate);

        drawPlatformsAndLavaAndGroundAndEnd(grass, ground, end, setAnimationIndex(gameTickCounter));

        moveCloud();
        renderer.drawImage(cloudImage, cloud.getX(), 50);

        if (client.isLive()) {
            moveClient(platformSprites);
            client.displaySprite(renderer, clientImg, client.getClientSprite());
            if (client.getClientSprite().intersects(cloud.getX() - 90, cloud.getY(), (int) cloud.getWidth(), (int) cloud.getHeight())) {
                client.kill();
            }
        }

        for (AI ai : aiPlayers) {
            ArrayList<Sprite> sprites = new ArrayList<>();
            sprites.addAll(platformSprites);
            sprites.addAll(groundSprites);
            sprites.addAll(lavaSprites);
            aiLogic.moveSprite(ai, sprites);
            ai.displaySprite(renderer, aiImg, ai.getSprite());
        }

        for (Enemy thisEnemy : enemies) {
            thisEnemy.moveEnemy();
            thisEnemy.displaySprite(renderer, enemy, thisEnemy.getEnemySprite());
        }

        //Move camera
        if ((-1 * renderer.getTransLateX()) + 700 < client.getClientSprite().getX() && (-1 * renderer.getTransLateX()) < (levelWidth * 60 - 1280)) {
            renderer.setTransLateX((int) (renderer.getTransLateX() + ((-1 * renderer.getTransLateX()) + 700 - client.getClientSprite().getX())));
        } else {
            renderer.setTransLateX((int) (renderer.getTransLateX()));
        }

        xViewCoordinate = (int) (-1 * renderer.getTransLateX());
    }

    private void moveCloud() {
        int cloudXPosNew = cloud.getX() + 2;

        if (client.getClientSprite().getX() - cloud.getX() > 2000) {
            cloudXPosNew = client.getClientSprite().getX() - 2000;
        }
        cloud.setX(cloudXPosNew);
    }

    private int setAnimationIndex(int counter) {
        if (counter % 3 == 0) {
            animationIndex ++;
            if (animationIndex == 17) {
                animationIndex = 0;
            }
        }
        return animationIndex;
    }

    private void drawPlatformsAndLavaAndGroundAndEnd(Image grass, Image ground, Image end, int animationIndex) {
        for (Sprite platformSprite : platformSprites) {
            renderer.drawImage(platformSprite.getImage(), platformSprite.getX(), platformSprite.getY());
        }
        for (Sprite lavaSprite : lavaSprites) {
            renderer.drawImage(lavaImages.get(animationIndex), lavaSprite.getX(), lavaSprite.getY());
        }
        for (Sprite groundSprite : groundSprites) {
            renderer.drawImage(groundSprite.getImage(), groundSprite.getX(), groundSprite.getY());
        }
        for (Sprite endSprite : endSprites) {
            renderer.drawImage(endSprite.getImage(), endSprite.getX(), endSprite.getY());
        }
    }
}