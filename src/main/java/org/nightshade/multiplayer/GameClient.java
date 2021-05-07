package org.nightshade.multiplayer;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
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
    private boolean deathSoundPlayed;
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
        this.deathSoundPlayed = false;

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
    public void kill() {
        if (!deathSoundPlayed) {
            File soundFile = new File("src/main/resources/audio/die.mp3");
            spotEffects.playSoundUntilEnd(soundFile, true, volume);
        }
        File soundFile = new File("src/main/resources/audio/die.mp3");
        spotEffects.playSoundUntilEnd(soundFile, true, volume);
        //spotEffects.playSoundUntilEnd(soundFile, true);
        isAlive =false;
        GuiHandler.stage.setScene(GuiHandler.gameOverScreen);
    }

    /**
     * Moves the character on the x-axis
     * @param value the value of how much the character will move
     * @param platformSprites list of all the platforms
     * @param enemies list of all the enemies
     * @param groundSprites list of all the ground
     * @param movingPlatforms list of all the moving platforms
     */
    public void moveX(int value, ArrayList<Sprite> platformSprites, ArrayList<Enemy> enemies, ArrayList<Sprite> groundSprites, ArrayList<MovingPlatform> movingPlatforms, Level level){
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
                    kill();
                    return;
                }
            }
            for (Sprite endSprite : level.getEndSprites()) {
                if (endSprite.intersects(sprite)) {
                    System.out.println("finish");
                }
            }
            getSprite().setX(getSprite().getX() + (isMovingRight ? 1 : -1));
        }
    }

    /**
     * Moves the character on the y-axis
     * @param value the value of how much the character will move
     * @param platformSprites list of all the platforms
     * @param lavaSprites list of all the water
     * @param enemies list of all the enemies
     * @param groundSprites list of all the ground
     * @param movingPlatforms list of all the moving platforms
     */
    public void moveY(int value, ArrayList<Sprite> platformSprites, ArrayList<Sprite> lavaSprites, ArrayList<Enemy> enemies, ArrayList<Sprite> groundSprites, ArrayList<MovingPlatform> movingPlatforms, Level level){
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
            for (Sprite lava : lavaSprites) {
                if (lava.intersects(sprite)){
                    getSprite().setY(getSprite().getY() + 1);
                    if(lava.intersects(sprite.getX(), sprite.getY()-60, (int) Math. round(sprite.getWidth()), (int) Math. round(sprite.getHeight()))){
                        kill();
                        deathSoundPlayed = true;
                    }
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
                    kill();
                    return;
                }
            }
            for (Sprite endSprite : level.getEndSprites()) {
                if (endSprite.intersects(sprite)) {
                    System.out.println("finish");
                }
            }
            getSprite().setY(getSprite().getY() + (movingDown ? 1 : -1));
        }
    }
}