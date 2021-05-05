package org.nightshade.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import org.nightshade.animation.AnimatedImage;
import org.nightshade.animation.AnimationType;
import org.nightshade.animation.CharacterColour;

public class Sprite {

    private Image image;
    private double x;
    private AnimatedImage animatedImage;
    private double y;
    private final double width;
    private final double height;

    /**
     * Sprite method constructor method for sprite
     * @param image image of the sprite
     * @param x x-coordinate of the sprite
     * @param y y-coordinate of the sprite
     */
    public Sprite(Image image, int x, int y) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = x;
        this.y = y;
    }

    /**
     * getImage getter method returning the image
     * @return the image of the sprite
     */
    public Sprite(AnimatedImage animatedImage, int x, int y) {
        this.animatedImage = animatedImage;
        this.width = animatedImage.getWidth();
        this.height = animatedImage.getHeight();
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return this.image;
    }

    public AnimatedImage getAnimatedImage() {
        return this.animatedImage;
    }

    /**
     * getHeight getter method returning height of the sprite
     * @return height of sprite
     */
    public double getHeight() {
        return height;
    }

    /**
     * getWidth getter method returning width of the sprite
     * @return width of the sprite
     */
    public double getWidth() {
        return width;
    }

    /**
     * setPosition setter method setting the coordinates of the sprite
     * @param x x-coordinate of the sprite
     * @param y y-coordinate of the sprite
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * setX setter method setting the x-coordinate of the sprite
     * @param x x-coordinate of sprite
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * setY setter method setting the y-coordinate of the sprite
     * @param y y-coordinate of sprite
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * getX getter method returning the y-coordinate of sprite
     * @return y-coordinate of sprite
     */
    public int getY() {
        return (int) y;
    }

    /**
     * getX getter method returning the x-coordinate of sprite
     * @return x-coordinate of sprite
     */
    public int getX() {
        return (int) x;
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
     * moveLeft method moving the character to the left by 1 unit
     * changing the x-coordinate by one to the left
     */
    public void moveLeft() {
        this.x += 1;
    }

    /**
     * moveRight method moving the character to the right by 1 unit
     * changing the x-coordinate by one to the right
     */
    public void moveRight() {
        this.x -= 1;
    }

    public void setAnimatedImage(AnimationType animationType, Direction direction, CharacterColour characterColour) {
        String colour = "blue_character";
        if (characterColour.equals(CharacterColour.GREEN)) {
            colour = "green_character";
        }
        else if (characterColour.equals(CharacterColour.RED)) {
            colour = "red_character";
        }
        else if (characterColour.equals(CharacterColour.YELLOW)) {
            colour = "yellow_character";
        }
        else if (characterColour.equals(CharacterColour.PURPLE)) {
            colour = "purple_character";
        }
        else {
            colour = "blue_character";
        }
        if (animationType.equals(AnimationType.RUNNING) && direction.equals(Direction.FORWARD)) {
            Image[] imageArray = new Image[5];
            for (int i = 0; i < 5; i++) {
                imageArray[i] = new Image("img/game/" + colour + "/run_right_" + i + ".png");
            }
            animatedImage.setFrames(imageArray);
            animatedImage.setDuration(0.150);
        }
        else if (animationType.equals(AnimationType.RUNNING) && direction.equals(Direction.BACKWARD)) {
            Image[] imageArray = new Image[5];
            for (int i = 0; i < 5; i++) {
                imageArray[i] = new Image("img/game/" + colour + "/run_left_" + i + ".png");
            }

            animatedImage.setFrames(imageArray);
            animatedImage.setDuration(0.150);
        }

        if (animationType.equals(AnimationType.IDLE)) {
            Image[] imageArray = new Image[2];
            imageArray[0] = new Image("img/game/" + colour + "/run_right_0.png");
            imageArray[1] = new Image("img/game/" + colour + "/run_right_2.png");
            animatedImage.setFrames(imageArray);
            animatedImage.setDuration(0.200);
        }
    }
}