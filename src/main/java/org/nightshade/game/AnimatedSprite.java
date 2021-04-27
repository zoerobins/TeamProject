package org.nightshade.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import org.nightshade.animation.AnimatedImage;

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

    public void setAnimatedImage(int type, boolean direction) {
        if (type == 1 && direction == true) {
            Image[] imageArray = new Image[8];
            for (int i = 0; i < 8; i++) {
                imageArray[i] = new Image("img/game/player_run_right_" + i + ".png");
            }
            image.frames = imageArray;
            image.duration = 0.150;
        }
        else if (type == 1 && direction == false) {
            Image[] imageArray = new Image[8];
            for (int i = 0; i < 8; i++) {
                imageArray[i] = new Image("img/game/player_run_left_" + i + ".png");
            }
            image.frames = imageArray;
            image.duration = 0.150;
        }
        if (type == 2) {
            Image[] imageArray = new Image[2];
            for (int i = 0; i < 2; i++) {
                imageArray[i] = new Image("img/game/player_idle_" + i + ".png");
            }
            image.frames = imageArray;
            image.duration = 0.150;
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

    public boolean intersects(Sprite spr) {
        return spr.getBoundary().intersects(this.getBoundary());
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
