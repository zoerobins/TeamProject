package org.nightshade.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import org.nightshade.animation.AnimatedImage;
import org.nightshade.animation.AnimationType;

/**
 * AnimatedSprite class is a class containing all processes needed for the animated character
 */
public class AnimatedSprite {

    private AnimatedImage image;
    private double x;
    private double y;
    private final double width;
    private final double height;

    /**
     * AnimatedSprite is the constructor of the animated character
     * Sets the variable values using the arguments provided
     * @param image Image of the character
     * @param x x-coordinate of the character
     * @param y y-coordinate of the character
     */
    public AnimatedSprite(AnimatedImage image, int x, int y) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the image of the animated character
     * @return image of the animated character
     */
    public AnimatedImage getImage() {
        return this.image;
    }

    /**
     * setAnimatedImage Setter Method for the animated character and
     * displaying all the animated moves the character makes depending on how
     * the user moves the character
     * @param animationType the type of the animation user requires from the character
     * @param direction the direction of the character is facing at the specific time
     */
    public void setAnimatedImage(AnimationType animationType, Direction direction) {
        if (animationType.equals(AnimationType.RUNNING) && direction.equals(Direction.FORWARD)) {
            Image[] imageArray = new Image[5];
            for (int i = 0; i < 5; i++) {
                imageArray[i] = new Image("img/game/player_run_right/player_run_right_" + i + ".png");
            }
            image.setFrames(imageArray);
            image.setDuration(0.150);
        }
        else if (animationType.equals(AnimationType.RUNNING) && direction.equals(Direction.BACKWARD)) {
            Image[] imageArray = new Image[5];
            for (int i = 0; i < 5; i++) {
                imageArray[i] = new Image("img/game/player_run_left/player_run_left_" + i + ".png");
            }
            image.setFrames(imageArray);
            image.setDuration(0.150);
//            System.out.println(image.getFrames()[0].getUrl());
        }
        if (animationType.equals(AnimationType.IDLE)) {
            Image[] imageArray = new Image[2];
            for (int i = 0; i < 2; i++) {
                imageArray[i] = new Image("img/game/player_idle_" + i + ".png");
            }
            image.setFrames(imageArray);
            image.setDuration(0.200);
        }
    }

    /**
     * getHeight getter method for getting the height of the character
     * @return the height of the character
     */
    public double getHeight() {
        return height;
    }

    /**
     * getWidth getter method for getting the width of the character
     * @return the width of the character
     */
    public double getWidth() {
        return width;
    }

    /**
     * setPosition setter method for setting the position of the character
     * @param x x-coordinate of the character
     * @param y y-coordinate of the character
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * setX setter method for setting x-coordinate of the character
     * @param x x-coordinate of the character
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * setY setter method for setting y-coordinate of the character
     * @param y y-coordinate of the character
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * getX getter method for getting x-coordinate of the character
     * @return x-coordinate of the character
     */
    public int getX() {
        return (int) x;
    }

    /**
     * getY getter method for getting y-coordinate of the character
     * @return y-coordinate of the character
     */
    public int getY() {
        return (int) y;
    }

    /**
     * getBoundary method creating a 2D rectangle with boundaries the height and the width of the character
     * and the coordinates of the character
     * @return a 2D rectangle size of the character
     */
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }

    /**
     * intersects function returning if the sprite is out of bounce
     * @param sprite character
     * @return a boolean whether the character intersects the boundaries
     */
    public boolean intersects(Sprite sprite) {
        return sprite.getBoundary().intersects(this.getBoundary());
    }

    /**
     * intersects function returning if the animated sprite is out of bounce
     * @param animatedSprite animated character
     * @return a boolean whether the character intersects the boundaries
     */
    public boolean intersects(AnimatedSprite animatedSprite) {
        return animatedSprite.getBoundary().intersects(this.getBoundary());
    }

    /**
     * intersects function returning if the animated sprite is out of bounce
     * @param x x-coordinate of the character
     * @param y y-coordinate of the character
     * @param w width of the character
     * @param h height of the character
     * @return boolean whether the character intersects the boundaries
     */
    public boolean intersects(int x, int y, int w, int h) {
        return this.getBoundary().intersects(x,y,w,h);
    }

    /**
     * moveLeft moves the character horizontally to the left by one unit
     */
    public void moveLeft() {
        this.x += 1;
    }

    /**
     * moveRight moves the character horizontally to the right by one unit
     */
    public void moveRight() {
        this.x -= 1;
    }

}
