package org.nightshade.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.nightshade.ai.AI;
import org.nightshade.ai.AILogic;
import org.nightshade.audio.BackgroundMusic;
import org.nightshade.gui.SettingsController;
import org.nightshade.renderer.Renderer;

import java.io.File;
import java.util.ArrayList;

public class Game {
    private final Scene scene;
    private final int LEVEL_WIDTH = 20;
    private final int BLOCK_WIDTH = 60;
    private int xViewCoordinate = 0;
    private int animationIndex = 0;
    private int gameTickCounter = 0;
    private final ArrayList<AI> aiPlayers;
    private final ArrayList<Image> lavaImages;
    private final ArrayList<KeyCode> keyCodes;
    private final Renderer renderer;
    private final Client client;
    private final AILogic aiLogic;
    private final Sprite cloudSprite;
    private final Parallax parallax;
    private final Level level;
    private final Image powerupTextBackground;

    private final long startNanoTime = System.nanoTime();


    /**
     *
     * @param stage
     */
    public Game(Stage stage) {
        renderer = new Renderer();
        renderer.setHeight(720);
        renderer.setWidth(LEVEL_WIDTH * BLOCK_WIDTH);
        renderer.getGraphicsContext().setFont(Font.font("Arial", 32));
        renderer.getGraphicsContext().setFill(Color.INDIANRED);
        Pane pane = new Pane(renderer.getGroup());
        int SCENE_WIDTH = 1280;
        int SCENE_HEIGHT = 720;
        scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
/*
        double volume = SettingsController.bgSliderVal / 100;
        BackgroundMusic backgroundMusic = new BackgroundMusic();
        backgroundMusic.startBackgroundMusic(new File("src/main/resources/audio/background_music.mp3"), volume);
*/
        Image cloudImage = new Image("img/game/cloud.png");
        powerupTextBackground = new Image("img/game/powerup_text_background.png");
        cloudSprite = new Sprite(cloudImage, -2300, 50);
        cloudSprite.setX(-1300);
        parallax = new Parallax();
        level = new Level(LEVEL_WIDTH);
        aiLogic = new AILogic();
        client = new Client();
        aiPlayers = new ArrayList<>();
        lavaImages = new ArrayList<>();

        for (int i = 1; i < 18; i++) {
            Image lavaImage = new Image("img/game/lava/lava-" + i + ".png");
            lavaImages.add(lavaImage);
        }
        keyCodes = new ArrayList<>();
        listen();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                loop(currentNanoTime);
            }
        }.start();
    }

    /**
     * listen method listens for KeyEvents and keeps track of keys pressed
     */
    // listens for KeyEvents and keeps track of keys pressed
    private void listen() {
        scene.setOnKeyPressed(
                keyEvent -> {
                    KeyCode keyCode = keyEvent.getCode();
                    if (!keyCodes.contains(keyCode)) {
                        keyCodes.add(keyCode);
                    }
                });
        scene.setOnKeyReleased(
                keyEvent -> {
                    KeyCode keyCode = keyEvent.getCode();
                    keyCodes.remove(keyCode);
                });
    }

    /**
     * moveClient method moves everything in the client
     * moves animated character, platforms, enemies, water and lava
     */
    private void moveClient() {
        if (client.isAlive()) {
            if (keyCodes.contains(KeyCode.UP) && client.getSprite().getY() >= 5) {
                client.jump();
            }
            if (keyCodes.contains(KeyCode.LEFT) && client.getSprite().getX() >= 5) {
                client.moveX(-5, level,aiPlayers);
            }
            if (keyCodes.contains(KeyCode.RIGHT) && client.getSprite().getX() <= (LEVEL_WIDTH * BLOCK_WIDTH) - 5) {
                client.moveX(5, level,aiPlayers);
            }
            if (client.getVelocity().getY() < 10) {
                client.setVelocity(client.getVelocity().add(0, 1));
            }
            client.moveY((int) client.getVelocity().getY(), level);
        }
    }

    /**
     * addAiPlayer adds an ai player to the game
     * @param ai ai player, opponent
     */
    public void addAiPlayer(AI ai) {
        aiPlayers.add(ai);
    }

    /**
     * loop method is a method running over and over so the game flows
     * @param currentNanoTime current time in nano seconds
     */
    public void loop(long currentNanoTime) {
        double time = (currentNanoTime - startNanoTime) / 1000000000.0;
/*
        parallax.move();
        parallax.render(renderer, xViewCoordinate);

 */
        renderSprites(level.getPlatformSprites());
        renderSprites(level.getGroundSprites());
        renderSprites(level.getEndSprites());
        gameTickCounter++;
        animationIndex = setAnimationIndex(gameTickCounter);

        for (Sprite lavaSprite : level.getLavaSprites()) {
            renderer.drawImage(lavaImages.get(animationIndex), lavaSprite.getX(), lavaSprite.getY());
        }

        // move cloud
        if (client.getSprite().getX() - cloudSprite.getX() > 2000) {
            cloudSprite.setX(client.getSprite().getX() - 2000);
        } else {
            cloudSprite.setX(cloudSprite.getX() + 2);
        }

        renderer.drawImage(cloudSprite.getImage(), cloudSprite.getX(), 50);

        if (client.isAlive()) {
            moveClient();
            Sprite clientSprite = client.getSprite();
            renderer.drawImage(clientSprite.getAnimatedImage().getFrame(time), clientSprite.getX(), clientSprite.getY());
            boolean intersectsCloud = clientSprite.intersects(cloudSprite.getX() - 90, cloudSprite.getY(), (int) cloudSprite.getWidth(), (int) cloudSprite.getHeight());
            if (intersectsCloud) {
                client.kill();
            }
        }

        for (AI ai : aiPlayers) {
            for (Sprite endSprite : level.getEndSprites()){
                if (ai.getSprite().intersects(endSprite)){
                    ai.setFinished(true);
                }
            }
            ArrayList<Sprite> sprites = new ArrayList<>();
            sprites.addAll(level.getPlatformSprites());
            sprites.addAll(level.getGroundSprites());
            sprites.addAll(level.getLavaSprites());
            aiLogic.moveSprite(ai, sprites);
            Sprite aiSprite = ai.getSprite();
            renderer.drawImage(aiSprite.getAnimatedImage().getFrame(time), aiSprite.getX(), aiSprite.getY());
        }

        for (Enemy enemy : level.getEnemies()) {
            enemy.moveEnemy();
            Sprite enemySprite = enemy.getSprite();
            renderer.drawImage(enemySprite.getImage(), enemySprite.getX(), enemySprite.getY());
        }

        for (MovingPlatform movingPlatform : level.getMovingPlatforms()) {
            movingPlatform.movePlatform();
            movingPlatform.displaySprite(renderer, movingPlatform.getSprite().getImage(), movingPlatform.getSprite());
        }

        for (PowerUp powerUp : level.getPowerUps()) {
            if (powerUp.getCollected()) {
                renderer.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY());
            }
        }

        if (client.powerUpTimer > 0) {
            client.reducePowerUpTimer();
            renderer.drawImage(powerupTextBackground, (int) (-renderer.getCanvas().getTranslateX() + 20), 5, 305, 70);
            if (client.ability == Ability.SHIELD) {
                renderer.getGraphicsContext().fillText("      " + client.ability + " " + client.powerUpTimer, -renderer.getCanvas().getTranslateX() + 36, 50);
            } else if (client.ability == Ability.SPEEDBOOST) {
                renderer.getGraphicsContext().fillText(" " + client.ability + " " + client.powerUpTimer, -renderer.getCanvas().getTranslateX() + 36, 50);
            } else {
                renderer.getGraphicsContext().fillText("    " + client.ability, -renderer.getCanvas().getTranslateX() + 36, 50);
            }
        } else {
            client.removeAbility();
        }

        //Move camera
        double translateX = renderer.getCanvas().getTranslateX();
        if ((-1 * translateX) + 700 < client.getSprite().getX() && (-1 * translateX) < (LEVEL_WIDTH * 60 - 1280)) {
            renderer.getCanvas().setTranslateX((int) (translateX + ((-1 * translateX) + 700 - client.getSprite().getX())));
        } else {
            renderer.getCanvas().setTranslateX((int) (translateX));
        }
        xViewCoordinate = (int) (-1 * translateX);
    }

    /**
     * setAnimationIndex
     * @param counter
     * @return
     */
    private int setAnimationIndex(int counter) {
        if (counter % 3 == 0) {
            animationIndex++;
            if (animationIndex == 17) {
                animationIndex = 0;
            }
        }
        return animationIndex;
    }

    /**
     * rendererSprites used to draw the sprites
     * @param sprites
     */
    private void renderSprites(ArrayList<Sprite> sprites) {
        for (Sprite sprite : sprites) {
            renderer.drawImage(sprite.getImage(), sprite.getX(), sprite.getY());
        }
    }
}