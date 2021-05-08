package org.nightshade.multiplayer;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.nightshade.animation.AnimationType;
import org.nightshade.game.Direction;
import org.nightshade.gui.Player;
import org.nightshade.networking.Client;
import org.nightshade.networking.PlayerMoveMsg;
import org.nightshade.renderer.Renderer;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

public class Game {

    private final int levelWidth = 120;
    private final int blockWidth = 60;
    private int xViewCoordinate = 0;
    private int animationIndex = 0;
    private final ArrayList<Image> lavaImages;
    private final ArrayList<String> input = new ArrayList<>();
    private final Renderer renderer;
    private final Sprite cloud;
    private final Parallax parallax;
    private Level level;
    private int gameTickCounter = 0;    private GameClient localGameClient;
    private ArrayList<GameClient> gameClients;
    private Client client;
    private ArrayList<PlayerMoveMsg> msgsList = new ArrayList<>();
    private int loopRun = 0;
    private final long startNanoTime = System.nanoTime();



    public Game(Stage stage, GameClient localGameClient , ArrayList<GameClient> gameClients, Level level, Client client) {

        this.level = level;
        this.localGameClient = localGameClient;
        this.gameClients = gameClients;
        this.client = client;
        for (int i = 0; i < gameClients.size(); i++) {
            if (localGameClient.getName().equals(gameClients.get(i).getName())) {
                localGameClient.setClientNumber(i);
                localGameClient.setCharacterColour(i);
                localGameClient.setAnimatedImage(AnimationType.IDLE, Direction.FORWARD, localGameClient.characterColour);
            }
            else {
                gameClients.get(i).setClientNumber(i);
                gameClients.get(i).setCharacterColour(i);
                gameClients.get(i).setAnimatedImage(AnimationType.IDLE, Direction.FORWARD, gameClients.get(i).characterColour);
            }
        }

        cloud = new Sprite(new Image("img/game/cloud.png"), -2300, 50);
        parallax = new Parallax();
        renderer = new Renderer();
        Pane pane = new Pane(renderer.getGroup());
        Scene scene = new Scene(pane, 1280, 720);
        cloud.setX(-1300);
        renderer.setHeight(720);
        renderer.setWidth(levelWidth * blockWidth);
        stage.setScene(scene);
        stage.show();
        lavaImages = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            lavaImages.add(new Image("img/game/lava/image " + (i + 1) + ".png"));
        }
        checkForInput(scene);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                loop(currentNanoTime);
            }
        }.start();
    }

    private void checkForInput(Scene scene) {
        scene.setOnKeyPressed(
                keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    if (!input.contains(code)) {
                        input.add(code);
                    }
                });
        scene.setOnKeyReleased(
                keyEvent -> {
                    String code = keyEvent.getCode().toString();
                    input.remove(code);
                });
    }

    private void moveClients() {

        ArrayList<Sprite> platformSprites = level.getPlatformSprites();
        ArrayList<Sprite> lavaSprites = level.getLavaSprites();
        ArrayList<Sprite> groundSprites = level.getGroundSprites();
        ArrayList<Enemy> enemies = level.getEnemies();
        ArrayList<MovingPlatform> movingPlatforms = level.getMovingPlatforms();
        if (localGameClient.isAlive()) {
            if (input.contains("UP") && localGameClient.getSprite().getY() >= 5) {
                localGameClient.jump();
            }
            if (input.contains("LEFT") && localGameClient.getSprite().getX() >= 5) {
                localGameClient.moveX(-5, platformSprites, enemies, groundSprites, movingPlatforms, level,gameClients);
            }
            if (input.contains("RIGHT") && localGameClient.getSprite().getX() <= (levelWidth * blockWidth) - 5) {
                localGameClient.moveX(5, platformSprites, enemies, groundSprites, movingPlatforms, level,gameClients);
            }
            if (localGameClient.getVelocity().getY() < 10) {
                localGameClient.setVelocity(localGameClient.getVelocity().add(0, 1));
            }
            localGameClient.moveY((int) localGameClient.getVelocity().getY(), platformSprites, lavaSprites, enemies, groundSprites, movingPlatforms, level,gameClients);
        }

        try {
            if ((loopRun%2 == 0) && !((localGameClient.getX() == localGameClient.getPreviousX())&&(localGameClient.getY() == localGameClient.getPreviousY()))) {
                client.getClientLogic().sendToServer(localGameClient.getName(), localGameClient.getX(), localGameClient.getY(), localGameClient.isAlive());
                System.out.println("sent " + localGameClient.getName() + " x: "+localGameClient.getX()+" y: "+localGameClient.getY());

                client.getClientLogic().receiveMoveMsgs();
                if(loopRun>2) {
                    client.getClientLogic().receiveMoveMsgs();
                }
                msgsList = client.getClientLogic().getMsgsList();
                for(PlayerMoveMsg moveMsg : msgsList) {
                    System.out.println("received " + moveMsg.getName() + " x: "+moveMsg.getX()+" y: "+moveMsg.getY() + " alive: "+moveMsg.isAlive());
                    if ((!(moveMsg.getName().equals(localGameClient.getName())))){
                        if(moveMsg.isAlive()) {
                            for (GameClient gameClient : gameClients) {
                                if (moveMsg.getName().equals(gameClient.getName())) {
                                    gameClient.setX(moveMsg.getX());
                                    gameClient.setY(moveMsg.getY());
                                    gameClient.setAlive(moveMsg.isAlive());
                                    break;
                                }
                            }
                        } else {
                            System.out.println("player dead: " + moveMsg.getName());
                        }
                    }
                }

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loopRun++;

    }

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

        int largestGameClientX =localGameClient.getX();
        for (Sprite lavaSprite : level.getLavaSprites()) {
            renderer.drawImage(lavaImages.get(animationIndex), lavaSprite.getX(), lavaSprite.getY());
        }
        for (GameClient gc : gameClients) {
            if(!(gc.getName().equals(client.getName()))) {
                Sprite gcSprite = gc.getSprite();
                renderer.drawImage(gcSprite.getAnimatedImage().getFrame(time), gcSprite.getX(), gcSprite.getY());
            }
            if (gc.getX() > largestGameClientX){
                largestGameClientX = gc.getX();
            }
        }
/*
        if (largestGameClientX - cloud.getX() > 2000) {
            cloud.setX(largestGameClientX - 2000);
        } else {
            cloud.setX(cloud.getX() + 2);
        }

 */

        renderer.drawImage(cloud.getImage(), cloud.getX(), 50);
        moveClients();
        if (localGameClient.isAlive()) {
            Sprite clientSprite = localGameClient.getSprite();
            renderer.drawImage(clientSprite.getAnimatedImage().getFrame(time), clientSprite.getX(), clientSprite.getY());
            boolean intersectsCloud = clientSprite.intersects(cloud.getX() - 90, cloud.getY(), (int) cloud.getWidth(), (int) cloud.getHeight());
            if (intersectsCloud) {
                localGameClient.kill();
            }
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

        double translateX = renderer.getCanvas().getTranslateX();
        if ((-1 *translateX) + 700 < localGameClient.getX() && (-1 * translateX) < (levelWidth * 60 - 1280)) {
            renderer.getCanvas().setTranslateX((int) (translateX + ((-1 * translateX) + 700 - localGameClient.getX())));
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

    private void renderSprites(ArrayList<Sprite> sprites) {
        for (Sprite sprite : sprites) {
            renderer.drawImage(sprite.getImage(), sprite.getX(), sprite.getY());
        }
    }

}
