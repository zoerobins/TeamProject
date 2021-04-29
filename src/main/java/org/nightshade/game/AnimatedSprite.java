package org.nightshade.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import org.nightshade.animation.AnimatedImage;
import org.nightshade.animation.AnimationType;

public class AnimatedSprite {

    private AnimatedImage image;
    private double x;
    private double y;
    private final double width;
    private final double height;

    public AnimatedSprite(AnimatedImage image, int x, int y) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = x;
        this.y = y;
    }

    public AnimatedImage getImage() {
        return this.image;
    }

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

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }

    public boolean intersects(Sprite sprite) {
        return sprite.getBoundary().intersects(this.getBoundary());
    }

    public boolean intersects(AnimatedSprite animatedSprite) {
        return animatedSprite.getBoundary().intersects(this.getBoundary());
    }

    public boolean intersects(int x, int y, int w, int h) {
        return this.getBoundary().intersects(x,y,w,h);
    }

    public void moveLeft() {
        this.x += 1;
    }

    public void moveRight() {
        this.x -= 1;
    }

}
