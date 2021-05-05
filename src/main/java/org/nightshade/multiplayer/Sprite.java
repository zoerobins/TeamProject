package org.nightshade.multiplayer;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import org.nightshade.animation.AnimatedImage;
import org.nightshade.animation.AnimationType;
import org.nightshade.animation.CharacterColour;
import org.nightshade.game.Direction;

public class Sprite {

    private Image image;
    private double x;
    private double y;
    private AnimatedImage animatedImage;
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
     * Constructor for AnimatedImage version of Sprite
     * @param animatedImage
     * @param x x-coordinate of the sprite
     * @param y y-coordinate of the sprite
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

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public int getY() {
        return (int) y;
    }

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
     * @param spr character
     * @return a boolean whether the character intersects the boundaries
     */
    public boolean intersects(Sprite spr) {
        return spr.getBoundary().intersects(this.getBoundary());
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

    public void moveLeft() {
        this.x += 1;
    }

    public void moveRight() {
        this.x -= 1;
    }

    /**
     * Changes the AnimatedImage of the sprite so that it can display the correct animation
     * @param animationType Enum for what type of animation is needed
     * @param direction The direction the character is facing
     * @param characterColour The colour of the character
     */
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