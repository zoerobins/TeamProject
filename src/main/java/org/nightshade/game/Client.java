package org.nightshade.game;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.nightshade.animation.AnimatedImage;
import org.nightshade.animation.AnimationType;
import org.nightshade.audio.SpotEffects;
import org.nightshade.gui.GuiHandler;
import org.nightshade.gui.SettingsController;
import org.nightshade.renderer.Renderer;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Client {
    private boolean isAlive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite sprite;
    public AnimatedSprite animatedSprite;
    private SpotEffects spotEffects;

    public double volume;

    private AnimatedImage animatedImage;

    private Random random;
    //public float volume1;
    public Ability ability;
    public int powerUpTimer;

    /**
     * Client is the constructor of the whole client
     */
    public Client() {
        this.isAlive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        this.animatedImage = new AnimatedImage();
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image("img/game/player_idle_0.png");
        imageArray[1] = new Image("img/game/player_idle_1.png");
        animatedImage.setFrames(imageArray);
        animatedImage.setDuration(0.150);
        this.animatedSprite = new AnimatedSprite(animatedImage, 300,50);
        this.sprite = new Sprite(new Image("img/game/player.png"),300,50);
        this.spotEffects = new SpotEffects();
        this.random = new Random();
        this.ability = null;
        this.powerUpTimer = 0;
        this.volume = SettingsController.mSliderVal / 100;
    }

    /**
     * setVelocity setter method for setting the velocity of the character
     * @param velocity the velocity of the character/the speed of the character that is moving
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
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
     * getAnimatedSprite getter method returning the animated character
     * @return the animated character
     */
    public AnimatedSprite getAnimatedSprite() {
        return animatedSprite;
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

    /**
     * displayAnimatedSprite a method displaying the image of the animated character
     * @param renderer
     * @param animatedImage the image of the animated character
     * @param sprite the character
     * @param t position of the character
     */
    public void displayAnimatedSprite(Renderer renderer, AnimatedImage animatedImage, Sprite sprite, double t){
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
        this.powerUpTimer = 50;
    }

    /**
     * removeAbility method removing the special power/power up the character gained
     */
    public void removeAbility(){
        this.ability = null;
    }

    /**
     * jump method is the method that does the character jump
     */
    public void jump() {
        if (canJump) {
            File soundFile = new File("src/main/resources/audio/jump_0" + random.nextInt(6) + ".mp3");
            spotEffects.playSound(soundFile, true, volume);
            if (this.ability == Ability.JUMPBOOST){
                velocity = velocity.add(0, -40);
            }else {
                velocity = velocity.add(0, -30);
            }

            canJump = false;
        }
    }

    /**
     * kill method setting the isAlive variable to false
     * makes the character die
     */
    public void kill() {
        File soundFile = new File("src/main/resources/audio/die.mp3");
        spotEffects.playSoundUntilEnd(soundFile, true, volume);
        isAlive = false;
        GuiHandler.stage.setScene(GuiHandler.gameOverScreen);
    }

    /**
     * moveX method is the method moving the character in the x-axis
     * @param value the value of how much the character will move
     * @param level the level of the character is right now
     */
    public void moveX(int value, Level level){
        boolean isMovingRight = value > 0;
        if (isMovingRight) {
            animatedSprite.setAnimatedImage(AnimationType.RUNNING, Direction.FORWARD);
        } else {
            animatedSprite.setAnimatedImage(AnimationType.RUNNING, Direction.BACKWARD);
            //System.out.println(animatedSprite.getImage().getFrame(0).getUrl());
        }

        int speed =1;
        if (this.ability == Ability.SPEEDBOOST){
            speed = 2;
        }
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : level.getPlatformSprites()) {
                if (platform.intersects(animatedSprite)){
                    if(isMovingRight){
                        animatedSprite.setX(animatedSprite.getX() - 1);
                    } else {
                        animatedSprite.setX(animatedSprite.getX() + 1);
                    }
                    return;
                }
            }

            for (Sprite ground : level.getGroundSprites()) {
                if (ground.intersects(animatedSprite)){
                    File soundFile = new File("src/main/resources/audio/step.mp3");
                    spotEffects.playSoundUntilEnd(soundFile, true, volume);
                    if(isMovingRight){
                        animatedSprite.setX(animatedSprite.getX() - 1);
                    } else {
                        animatedSprite.setX(animatedSprite.getX() + 1);
                    }
                    return;
                }
            }

            for (PowerUp box : level.getPowerUps()) {
                if (box.intersects(animatedSprite)) {
                    box.collect();
                    this.ability = box.getAbility();
                    this.setPowerUpTimer();
                }
            }

            for (MovingPlatform movingPlatform : level.getMovingPlatforms()){
                if (movingPlatform.getSprite().intersects(animatedSprite)){
                    if(isMovingRight){
                        animatedSprite.setX(animatedSprite.getX() - 1);
                    } else {
                        animatedSprite.setX(animatedSprite.getX() + 1);
                    }
                    return;
                }
            }

            for (Enemy enemy : level.getEnemies()) {
                if (enemy.getSprite().intersects(animatedSprite)){
                    if (this.ability == Ability.SHIELD){
                        return;
                    }else {
                        kill();
                    }
                    return;
                }
            }

            double newX;
            if (isMovingRight) {
                newX = animatedSprite.getX() + speed;
            } else {
                newX = animatedSprite.getX() - speed;
            }

            animatedSprite.setX(newX);

        }
    }

    /**
     * moveY method is the method moving the character in the y-axis
     * @param value he value of how much the character will move
     * @param platformSprites position of the platforms around the track
     * @param waterSprites positions of the water around the track
     * @param enemies position of the enemies around the track
     * @param groundSprites position of the ground around the track
     * @param movingPlatforms positions of moving platforms around the track
     * @param powerUps the power up the character has if it has one
     */
    public void moveY(int value,ArrayList<Sprite> platformSprites,ArrayList<Sprite> waterSprites,ArrayList<Enemy> enemies,ArrayList<Sprite> groundSprites, ArrayList<MovingPlatform> movingPlatforms, ArrayList<PowerUp> powerUps){
        boolean movingDown = value > 0;
        //animatedSprite.setAnimatedImage(AnimationType.IDLE, Direction.FORWARD);
        // TODO: above line stops the animation from working, figure a way to integrate this properly
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(animatedSprite) && movingDown) {
                    animatedSprite.setY(animatedSprite.getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite ground : groundSprites) {
                if (ground.intersects(animatedSprite) && movingDown) {
                    animatedSprite.setY(animatedSprite.getY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            for (Sprite water : waterSprites) {
                if (water.intersects(animatedSprite)){
                    animatedSprite.setY(animatedSprite.getY() + 1);
                    return;
                }
            }

            for (PowerUp box : powerUps) {
                if (box.intersects(animatedSprite)) {
                    box.collect();
                    this.ability = box.getAbility();
                    this.setPowerUpTimer();

                }
            }

            for (MovingPlatform mPlatform : movingPlatforms) {
                if (mPlatform.getSprite().intersects(animatedSprite) && movingDown){
                    animatedSprite.setY(animatedSprite.getY() - 1);
                    setCanJump(true);
                    return;
                }
            }

            for (Enemy enemy : enemies) {
                if (enemy.getSprite().intersects(animatedSprite)) {
                    if (this.ability != Ability.SHIELD){
                        kill();
                        return;
                    }else if (movingDown) {
                        getSprite().setY(getSprite().getY() - 1);
                        setCanJump(true);
                    }
                }
            }

            double newY;
            if (movingDown) {
                newY = animatedSprite.getY() + 1;
            } else {
                newY = animatedSprite.getY() - 1;
            }

            animatedSprite.setY(newY);

        }
    }
}