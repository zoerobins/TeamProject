package org.nightshade.multiplayer;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.nightshade.ai.AI;
import org.nightshade.animation.AnimatedImage;
import org.nightshade.animation.AnimationType;
import org.nightshade.animation.CharacterColour;
import org.nightshade.audio.SpotEffects;
import org.nightshade.game.Direction;
import org.nightshade.gui.GuiHandler;
import org.nightshade.gui.SettingsController;
import org.nightshade.renderer.Renderer;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GameClient {
    private boolean isAlive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite sprite;
    private AnimatedImage animatedImage;
    private SpotEffects spotEffects;
    private Random random;
    private int clientNumber;
    public double volume;
    public String name;
    public int previousX;
    public int previousY;
    public CharacterColour characterColour;
    public boolean finished;

    public GameClient(String name) {
        this.isAlive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        this.characterColour = CharacterColour.GREEN;
        this.animatedImage = new AnimatedImage();
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image("img/game/green_character/run_right_0.png");
        imageArray[1] = new Image("img/game/green_character/run_right_2.png");
        animatedImage.setFrames(imageArray);
        animatedImage.setDuration(0.150);
        this.sprite = new Sprite(animatedImage,300,50);
        this.sprite.setAnimatedImage(AnimationType.IDLE, Direction.FORWARD, characterColour);
        this.spotEffects = new SpotEffects();
        this.random = new Random();
        this.volume = SettingsController.mSliderVal / 100;
        this.name = name;
        this.previousX =previousX;
        this.previousY =previousY;
    }

    public String getName(){
        return name;
    }
    public void setX(Double x){
        previousX = this.sprite.getX();
        this.sprite.setX(x);
    }
    public void setY(Double y){
        previousY = this.sprite.getY();
        this.sprite.setY(y);
    }
    public void setAnimatedImage(AnimationType animationType, Direction direction, CharacterColour characterColour) { this.sprite.setAnimatedImage(animationType, direction, characterColour);}
    public void setClientNumber(int clientNumber) { this.clientNumber = clientNumber; }
    public int getPreviousX(){
        return previousX;
    }
    public int getPreviousY(){
        return previousY;
    }
    public int getX(){
        return sprite.getX();
    }
    public int getY(){
        return sprite.getY();
    }
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
    public boolean getFinished() {
        return finished;
    }
    public void setFinished(boolean finished){
     this.finished = finished;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }
    public Point2D getVelocity() {
        return velocity;
    }
    public Sprite getSprite() {
        return sprite;
    }
    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
    }

    public void setCharacterColour(int clientNumber) {
        if (clientNumber == 0){
            characterColour = CharacterColour.GREEN;
        }
        else if (clientNumber == 1){
            characterColour = CharacterColour.RED;
        }
        else if (clientNumber == 2){
            characterColour = CharacterColour.BLUE;
        }
        else {
            characterColour = CharacterColour.PURPLE;
        }
    }
    /**
     * jump method is the method that makes the character jump
     */
    public void jump() {
        if (canJump) {
            File soundFile = new File("src/main/resources/audio/jump_0" + random.nextInt(6) + ".mp3");
            spotEffects.playSound(soundFile, true, volume);
            //spotEffects.playSound(soundFile, true);
            velocity = velocity.add(0, -30);
            canJump = false;
        }
    }

    /**
     * kill method ends the game after the player has touched a fatal object
     */
    public void kill(ArrayList<GameClient> gameClients) {
        isAlive = false;
        changeToGameOver(gameClients);
    }

    public void changeToGameOver(ArrayList<GameClient> gameClients){
        if ((gameClients.size() == 0)&&(isAlive == false)){
            GuiHandler.stage.setScene(GuiHandler.multiGameOverScreen);
        }
        if (gameClients.size() == 0){
            GuiHandler.stage.setScene(GuiHandler.multiGameOverScreenW);
            //level complete screen (no position)
        }
        int position = gameClients.size()+1;
        for (GameClient gc : gameClients){
            if (!gc.getFinished()){
                position-=1;
            }
        }
        this.finished = true;
        if (position == 1){
            GuiHandler.stage.setScene(GuiHandler.multiGameOverScreen1);
        }else if(position == 2){
            GuiHandler.stage.setScene(GuiHandler.multiGameOverScreen2);
        }else if(position == 3){
            GuiHandler.stage.setScene(GuiHandler.multiGameOverScreen3);
        }else if(position == 4){
            GuiHandler.stage.setScene(GuiHandler.multiGameOverScreen4);
        }else {
            GuiHandler.stage.setScene(GuiHandler.multiGameOverScreenW);
        }

        //SinglePlayerController.game.backgroundMusic.stopBackgroundMusic();
        //SinglePlayerController.game = null;


    }

    /**
     * Moves the character on the x-axis
     * @param value the value of how much the character will move
     * @param platformSprites list of all the platforms
     * @param enemies list of all the enemies
     * @param groundSprites list of all the ground
     * @param movingPlatforms list of all the moving platforms
     */
    public void moveX(int value, ArrayList<Sprite> platformSprites, ArrayList<Enemy> enemies, ArrayList<Sprite> groundSprites, ArrayList<MovingPlatform> movingPlatforms, Level level, ArrayList<GameClient> gameClients){
        boolean isMovingRight = value > 0;
        if (isMovingRight) {
            sprite.setAnimatedImage(AnimationType.RUNNING, Direction.FORWARD, characterColour);
        } else {
            sprite.setAnimatedImage(AnimationType.RUNNING, Direction.BACKWARD, characterColour);
        }
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(sprite)){
                    if(isMovingRight){
                        getSprite().setX(getSprite().getX() - 1);
                    } else {
                        getSprite().setX(getSprite().getX() + 1);
                    }
                    return;
                }
            }
            for (Sprite ground : groundSprites) {
                if (ground.intersects(sprite)){
                    File soundFile = new File("src/main/resources/audio/step.mp3");
                    spotEffects.playSoundUntilEnd(soundFile, true, volume);
                    if(isMovingRight){
                        getSprite().setX(getSprite().getX() - 1);
                    } else {
                        getSprite().setX(getSprite().getX() + 1);
                    }
                    return;
                }
            }
            for (MovingPlatform movingPlatform : movingPlatforms){
                if (movingPlatform.getSprite().intersects(sprite)){
                    if(isMovingRight){
                        getSprite().setX(getSprite().getX() - 1);
                    } else {
                        getSprite().setX(getSprite().getX() + 1);
                    }
                    return;
                }
            }
            for (Enemy enemy : enemies) {
                if (enemy.getSprite().intersects(sprite)){
                    kill(gameClients);
                    return;
                }
            }
            if (isMovingRight && !finished) {
                if ((this.getSprite().getX() + this.getSprite().getWidth()) >= (level.getWidth() * 60)) {
                    if (gameClients.size() == 0) {
                        //level complete screen (no position)
                    }
                    int position = gameClients.size() + 1;
                    for (GameClient gc : gameClients) {
                        if (!gc.getFinished()) {
                            position -= 1;
                        }
                    }
                    this.finished = true;
                    System.out.println("congratulations you survived and finished in position: " + position);
                    //level complete screen (with position)
                    GuiHandler.stage.setScene(GuiHandler.gameOverScreen);

                }
            }
        }
        getSprite().setX(getSprite().getX() + (isMovingRight ? 1 : -1));
    }


    /**
     * Moves the character on the y-axis
     * @param value the value of how much the character will move
     * @param platformSprites list of all the platforms
     * @param waterSprites list of all the water
     * @param enemies list of all the enemies
     * @param groundSprites list of all the ground
     * @param movingPlatforms list of all the moving platforms
     */
    public void moveY(int value, ArrayList<Sprite> platformSprites, ArrayList<Sprite> waterSprites, ArrayList<Enemy> enemies, ArrayList<Sprite> groundSprites, ArrayList<MovingPlatform> movingPlatforms, ArrayList<GameClient> gameClients){
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(sprite) && movingDown) {
                    getSprite().setY(getSprite().getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite ground : groundSprites) {
                if (ground.intersects(sprite) && movingDown) {
                    getSprite().setY(getSprite().getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite water : waterSprites) {
                if (water.intersects(sprite)){
                    getSprite().setY(getSprite().getY() + 1);
                    return;
                }
            }
            for (MovingPlatform mPlatform : movingPlatforms) {
                if (mPlatform.getSprite().intersects(sprite) && movingDown){
                    getSprite().setY(getSprite().getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Enemy enemy : enemies) {
                if (enemy.getSprite().intersects(sprite)) {
                    kill(gameClients);
                    return;
                }
            }
            getSprite().setY(getSprite().getY() + (movingDown ? 1 : -1));
        }
    }
}