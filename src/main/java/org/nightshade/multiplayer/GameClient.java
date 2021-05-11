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
    public boolean finished;

    /**
     * GameClient is the constructor of the whole gameClient
     * @param name defines the name of the game client
     */
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

    /**getter method for name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**setter method for x
     * @param x
     */
    public void setX(Double x){
        previousX = this.sprite.getX();
        this.sprite.setX(x);
    }

    /**setter method for y
     * @param y
     */
    public void setY(Double y){
        previousY = this.sprite.getY();
        this.sprite.setY(y);
    }

    /** setter method for animated image
     * @param animationType
     * @param direction
     * @param characterColour
     */
    public void setAnimatedImage(AnimationType animationType, Direction direction, CharacterColour characterColour) { this.sprite.setAnimatedImage(animationType, direction, characterColour);}
    /**setter method for client number
     * @param clientNumber
     */
    public void setClientNumber(int clientNumber) { this.clientNumber = clientNumber; }
    /**getter method for previous x
     * @return previousX
     */
    public int getPreviousX(){
        return previousX;
    }
    /**getter method for previous x
     * @return previousY
     */
    public int getPreviousY(){
        return previousY;
    }
    /**getter method for x
     * @return x
     */
    public int getX(){
        return sprite.getX();
    }
    /**getter method for y
     * @return Y
     */
    public int getY(){
        return sprite.getY();
    }
    /**setter method for velocity
     * @param velocity
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
    /**getter method for isAlive
     * @return isAlive
     */
    public boolean isAlive() {
        return isAlive;
    }
    /**setter method for canJump
     * @param canJump
     */
    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }
    /**setter method for alive
     * @param alive
     */
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    /**getter method for velocity
     * @return velocity
     */
    public Point2D getVelocity() {
        return velocity;
    }
    /**getter method for sprite
     * @return sprite
     */
    public Sprite getSprite() {
        return sprite;
    }

    /** method to render sprite
     * @param renderer
     * @param image
     * @param sprite
     */
    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
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
            //spotEffects.playSound(soundFile, true, volume);
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
            //spotEffects.playSoundUntilEnd(soundFile, true, volume);
        }
        File soundFile = new File("src/main/resources/audio/die.mp3");
        //spotEffects.playSoundUntilEnd(soundFile, true, volume);
        //spotEffects.playSoundUntilEnd(soundFile, true);
        isAlive =false;
        //GuiHandler.stage.setScene(GuiHandler.gameOverScreen);
    }

    /** changes scene to appropriate end screen
     * @param gameClients to calculate final position
     */
    public void changeToGameOver(ArrayList<GameClient> gameClients){
        if ((gameClients.size() == 1) && (!isAlive)){
            GuiHandler.stage.setScene(GuiHandler.gameOverScreen);
        }

        if (gameClients.size() == 1){
            GuiHandler.stage.setScene(GuiHandler.gameOverScreenW);
            //level complete screen (no position)
        }

        int position = 1;

        if(isAlive) {
            for (GameClient gc : gameClients) {
                if(gc.getName().equals(this.name)){
                    //
                }else if (gc.getFinished()) {
                    position += 1;
                }
            }
        }else{
            for (GameClient gc : gameClients) {
                if(gc.getName().equals(this.getName())){
                    //
                }else if (gc.isAlive && (gc.getX() > this.getX())) {
                    position += 1;
                }
            }
        }
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
        //SinglePlayerController.game = null;
    }


    /**
     * moveX method is the method moving the character in the x-axis
     * @param value the value of how much the character will move
     * @param level used to retrieve sprites
     * @param gameClients used to determine position
     */
    public void moveX(int value, Level level, ArrayList<GameClient> gameClients){
        ArrayList<MovingPlatform> movingPlatforms = level.getMovingPlatforms();
        ArrayList<Sprite> platformSprites = level.getPlatformSprites();
        ArrayList<Enemy> enemies = level.getEnemies();
        ArrayList<Sprite> groundSprites = level.getGroundSprites();

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
                    //spotEffects.playSoundUntilEnd(soundFile, true, volume);
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
                    gameClients = findIfFinished(gameClients, level);
                    changeToGameOver(gameClients);
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


    /** finds if the current opponents have finished the level,
     *  and returns the updated game client array
     * @param gameClients
     * @param level
     * @return new array of game clients
     */
    public ArrayList<GameClient> findIfFinished(ArrayList<GameClient> gameClients,Level level){
        ArrayList<Sprite> endSprites = level.getEndSprites();
        for (GameClient gc : gameClients){
            for (Sprite es:endSprites) {
                if (gc.getSprite().getX()+gc.getSprite().getWidth()>= (level.width-1)*60) {
                    gc.setFinished(true);
                }
            }
        }
        return gameClients;
    }


    /**
     * moveY method is the method moving the character in the y-axis
     * @param value the value of how much the character will move
     * @param level used to retrieve sprites
     * @param gameClients used to determine position
     */
    public void moveY(int value, Level level,ArrayList<GameClient> gameClients){
        ArrayList<MovingPlatform> movingPlatforms = level.getMovingPlatforms();
        ArrayList<Sprite> platformSprites = level.getPlatformSprites();
        ArrayList<Sprite> lavaSprites= level.getLavaSprites();
        ArrayList<Enemy> enemies = level.getEnemies();
        ArrayList<Sprite> groundSprites = level.getGroundSprites();
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
                        gameClients = findIfFinished(gameClients, level);
                        changeToGameOver(gameClients);
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
                    gameClients = findIfFinished(gameClients, level);
                    changeToGameOver(gameClients);
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