package org.nightshade.game;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.nightshade.Main;
import org.nightshade.ai.AI;
import org.nightshade.animation.AnimatedImage;
import org.nightshade.animation.AnimationType;
import org.nightshade.animation.CharacterColour;
import org.nightshade.audio.SpotEffects;
import org.nightshade.gui.GameOverController;
import org.nightshade.gui.GuiHandler;
import org.nightshade.gui.SettingsController;
import org.nightshade.gui.SinglePlayerController;
import org.nightshade.renderer.Renderer;


import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Client {
    private boolean isAlive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite sprite;
    private SpotEffects spotEffects;
    private boolean deathSoundPlayed;

    public double volume;
    public CharacterColour characterColour;

    private AnimatedImage animatedImage;

    private Random random;
    public Ability ability;
    public int powerUpTimer;

    public boolean finished;

    public int position;

    /**
     * Client is the constructor of the whole client
     */
    public Client() {
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
        this.ability = null;
        this.powerUpTimer = 0;
        this.volume = SettingsController.mSliderVal / 100;
        this.deathSoundPlayed = false;
    }

    /**Changes the scene to the appropriate game over screen and ends game
     * @param aiPlayers
     */
    public void changeToGameOver(ArrayList<AI> aiPlayers){
        if ((aiPlayers.size() == 0)&&(isAlive == false)){
            GuiHandler.stage.setScene(GuiHandler.gameOverScreen);
        }

        if (aiPlayers.size() == 0){
            GuiHandler.stage.setScene(GuiHandler.gameOverScreenW);
            //level complete screen (no position)
        }

        int position = aiPlayers.size() + 1;

        if(isAlive) {
            for (AI ai : aiPlayers) {
                if (!ai.getFinished()) {
                    position -= 1;
                }
            }
        }else{
            position = 1;
            for (AI ai : aiPlayers) {
                if (ai.isAlive) {
                    position += 1;
                }
            }
        }

        this.finished = true;
        if (position == 1) {
            GuiHandler.stage.setScene(GuiHandler.gameOverScreen1);
        } else if (position == 2) {
            GuiHandler.stage.setScene(GuiHandler.gameOverScreen2);
        } else if (position == 3) {
            GuiHandler.stage.setScene(GuiHandler.gameOverScreen3);
        } else if (position == 4) {
            GuiHandler.stage.setScene(GuiHandler.gameOverScreen4);
        } else {
            GuiHandler.stage.setScene(GuiHandler.gameOverScreenW);
        }

        //SinglePlayerController.game.backgroundMusic.stopBackgroundMusic();
        SinglePlayerController.game = null;


    }

    /**
     * setVelocity setter method for setting the velocity of the character
     * @param velocity the velocity of the character/the speed of the character that is moving
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    /**
     * setFinished setter method for setting the finished state of the character
     * @param finished boolean holding whether the game client has finished the level or not
     */
    public void setFinished(boolean finished){
        this.finished = finished;
    }

    /**
     * getFinished getter method returning the finished state of the character
     * @return finished state of the character
     */
    public boolean getFinished() {
        return finished;
    }

    /**
     * isAlive a method returning whether the character is alive or not
     * @return boolean if the character is alive or not
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * setCanJump setter method setting the character to jump
     * @param canJump boolean if the character can jump or not
     */
    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    /**
     * getVelocity getter method returning the velocity of the character
     * @return velocity of the character
     */
    public Point2D getVelocity() {
        return velocity;
    }

    /**
     * getSprite getter method returning the character
     * @return the character
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * displaySprite a method displaying the image of the character
     * @param renderer
     * @param image the image of the character
     * @param sprite the character
     */
    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
    }
    
    public void displaysprite(Renderer renderer, AnimatedImage animatedImage, Sprite sprite, double t){
        renderer.drawImage(animatedImage.getFrame(t), sprite.getX(), sprite.getY());
    }

    /**
     * reducePowerUpTimer method reducing by one unit the time of the power up the character has
     */
    public void reducePowerUpTimer(){
        this.powerUpTimer = powerUpTimer-1;
    }

    /**
     * setPowerUpTimer setter method for the power up the character can have
     * setting it to 50 units
     */
    private void setPowerUpTimer(){
        this.powerUpTimer = 99;
    }

    /**
     * removeAbility method removes the power up the character gained
     */
    public void removeAbility(){
        this.ability = null;
    }

    /**
     * jump method is the method that makes the character jump
     */
    public void jump() {
        if (canJump) {
            File soundFile = new File("src/main/resources/audio/jump_0" + random.nextInt(6) + ".mp3");
            //spotEffects.playSound(soundFile, true, volume);
            if (this.ability == Ability.JUMPBOOST){
                velocity = velocity.add(0, -40);
            }else {
                velocity = velocity.add(0, -30);
            }

            canJump = false;
        }
    }

    /**
     * kill method ends the game after the player has touched a fatal object
     */
    public void kill(ArrayList<AI> aiPlayers) {
        if (!deathSoundPlayed) {
            File soundFile = new File("src/main/resources/audio/die.mp3");
            //spotEffects.playSoundUntilEnd(soundFile, true, volume);
        }


        isAlive = false;
        changeToGameOver(aiPlayers);
    }

    /**
     * moveX method is the method moving the character in the x-axis
     * @param value the value of how much the character will move
     * @param level used to retrieve sprites
     */
    public void moveX(int value, Level level, ArrayList<AI> aiPlayers){
        boolean isMovingRight = value > 0;
        if (isMovingRight) {
            sprite.setAnimatedImage(AnimationType.RUNNING, Direction.FORWARD, characterColour);
        } else {
            sprite.setAnimatedImage(AnimationType.RUNNING, Direction.BACKWARD, characterColour);
//            System.out.println(sprite.getImage().getFrame(0).getUrl());
        }

        int speed =1;
        if (this.ability == Ability.SPEEDBOOST){
            speed = 2;
        }
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : level.getPlatformSprites()) {
                if (platform.intersects(sprite)){
                    if(isMovingRight){
                        sprite.setX(sprite.getX() - 1);
                    } else {
                        sprite.setX(sprite.getX() + 1);
                    }
                    return;
                }
            }
            for (Sprite ground : level.getGroundSprites()) {
                if (ground.intersects(sprite)){
                    File soundFile = new File("src/main/resources/audio/step.mp3");
                    //spotEffects.playSoundUntilEnd(soundFile, true, volume);
                    if(isMovingRight){
                        sprite.setX(sprite.getX() - 1);
                    } else {
                        sprite.setX(sprite.getX() + 1);
                    }
                    return;
                }
            }
            for (PowerUp powerUp : level.getPowerUps()) {
                if (powerUp.intersects(sprite)) {
                    if (powerUp.getCollected()) {
                        System.out.println(2);
                        this.ability = powerUp.getAbility();
                        this.setPowerUpTimer();
                        powerUp.collect();
                    }
                }
            }

            for (MovingPlatform movingPlatform : level.getMovingPlatforms()){
                if (movingPlatform.getSprite().intersects(sprite)){
                    if(isMovingRight){
                        sprite.setX(sprite.getX() - 1);
                    } else {
                        sprite.setX(sprite.getX() + 1);
                    }
                    return;
                }
            }

            for (Enemy enemy : level.getEnemies()) {
                if (enemy.getSprite().intersects(sprite)){
                    if (this.ability == Ability.SHIELD){
                        return;
                    }else {
                        kill(aiPlayers);
                    }
                    return;
                }
            }

            if ((this.getSprite().getX() + this.getSprite().getWidth()) >= ((level.getWidth()-1)*60) ) {
                changeToGameOver(aiPlayers);
            }

            for (Sprite endSprite : level.getEndSprites()) {
                if (endSprite.intersects(sprite)) {
                    System.out.println("finish");
                }
            }

            double newX;
            if (isMovingRight) {
                newX = sprite.getX() + speed;
            } else {
                newX = sprite.getX() - speed;
            }

            sprite.setX(newX);
        }

    }

    /**
     * moveY method is the method moving the character in the y-axis
     * @param value the value of how much the character will move
     * @param level used to retrieve sprites
     */
    public void moveY(int value, Level level, ArrayList<AI> aiPlayers) {
        boolean movingDown = value > 0;
//        sprite.setAnimatedImage(AnimationType.IDLE, Direction.FORWARD);
        // TODO: above line stops the animation from working, figure a way to integrate this properly
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : level.getPlatformSprites()) {
                if (platform.intersects(sprite) && movingDown) {
                    sprite.setY(sprite.getY() - 7);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite ground : level.getGroundSprites()) {
                if (ground.intersects(sprite) && movingDown) {
                    sprite.setY(sprite.getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite lava : level.getLavaSprites()) {
                if (lava.intersects(sprite)){
                    sprite.setY(sprite.getY() + 1);
                    if(lava.intersects(sprite.getX(), sprite.getY()-60, (int) Math. round(sprite.getWidth()), (int) Math. round(sprite.getHeight()))){
                        kill(aiPlayers);
                        deathSoundPlayed = true;
                    }
                    return;
                }
            }

            for (PowerUp powerUp : level.getPowerUps()) {
                if (powerUp.intersects(sprite)) {

                    if (powerUp.getCollected()) {

                        this.ability = powerUp.getAbility();
                        this.setPowerUpTimer();
                        powerUp.collect();
                    }
                }
            }

            for (MovingPlatform mPlatform : level.getMovingPlatforms()) {
                if (mPlatform.getSprite().intersects(sprite) && movingDown){
                    sprite.setY(sprite.getY() - 7);
                    setCanJump(true);
                    return;
                }
            }

            for (Enemy enemy : level.getEnemies()) {
                if (enemy.getSprite().intersects(sprite)) {
                    if (this.ability != Ability.SHIELD){
                        kill(aiPlayers);
                        return;
                    }else if (movingDown) {
                        getSprite().setY(getSprite().getY() - 1);
                        setCanJump(true);
                    }
                }
            }

            for (Sprite endSprite : level.getEndSprites()) {
                if (endSprite.intersects(sprite)) {
                    System.out.println("finish");
                }
            }

            double newY;
            if (movingDown) {
                newY = sprite.getY() + 1;
            } else {
                newY = sprite.getY() - 1;
            }

            sprite.setY(newY);
        }
    }
}


