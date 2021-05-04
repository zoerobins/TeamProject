package org.nightshade.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Sprite {

    private final Image image;
    private double x;
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
    public Image getImage() {
        return this.image;
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
     * setX setter method setting the y-coordinate of the sprite
     * @param y y-coordinate of sprite
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * getX getter method returning the x-coordinate of sprite
     * @return x-coordinate of sprite
     */
    public int getX() {
        return (int) x;
    }

    /**
     * getX getter method returning the y-coordinate of sprite
     * @return y-coordinate of sprite
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

}