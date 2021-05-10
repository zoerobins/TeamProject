package org.nightshade.ai;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.nightshade.animation.AnimatedImage;
import org.nightshade.animation.AnimationType;
import org.nightshade.animation.CharacterColour;
import org.nightshade.game.Direction;
import org.nightshade.game.Sprite;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AI {

    private Difficulty difficulty;
    private boolean isAlive;
    private boolean canJump;
    private Point2D velocity;
    private Sprite sprite;
    private AnimatedImage animatedImage;
    private int speed;

    public AI(Difficulty difficulty) {
        this.isAlive = true;
        this.canJump = true;
        this.velocity = new Point2D(0, 0);
        int randomXStart = ThreadLocalRandom.current().nextInt(270, 330 + 1);
        int randomYStart = ThreadLocalRandom.current().nextInt(20, 60 + 1);
        this.animatedImage = new AnimatedImage();
        Image[] imageArray = new Image[2];
        imageArray[0] = new Image("img/game/green_character/run_right_0.png");
        imageArray[1] = new Image("img/game/green_character/run_right_2.png");
        animatedImage.setFrames(imageArray);
        animatedImage.setDuration(0.150);
        this.sprite = new Sprite(animatedImage, randomXStart, randomYStart);
        this.sprite.setAnimatedImage(AnimationType.RUNNING, Direction.FORWARD, CharacterColour.YELLOW);
        this.difficulty = difficulty;

        switch (difficulty) {
            case EASY:
                this.speed = 3;
                break;
            case MEDIUM:
                this.speed = 4;
                break;
            case HARD:
                this.speed = 5;
                break;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite) {
        renderer.drawImage(image, sprite.getX(), sprite.getY());
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
        return this.sprite;
    }

    public AnimatedImage getAnimatedImage() {
        return this.animatedImage;
    }

    public void jump() {
        if (canJump) {
            velocity = velocity.add(0, -30);
            canJump = false;
        }
    }

    public void moveX(ArrayList<Sprite> sprites) {
        int absoluteSpeed = Math.abs(speed);

        for (int i = 0; i < absoluteSpeed; i++) {
            for (Sprite sprite : sprites) {
                if (sprite.intersects(this.sprite)) {
                    if (speed > 0) {
                        // moving right
                        this.sprite.moveRight();
                    } else {
                        this.sprite.moveLeft();
                    }
                }
            }

            if (speed > 0) {
                // moving right
                sprite.moveLeft();
            } else {
                sprite.moveRight();
            }
        }
    }

    public void moveY(ArrayList<Sprite> sprites) {

        int speedY = (int) velocity.getY();
        int absoluteSpeed = Math.abs(speedY);

        for (int i = 0; i < absoluteSpeed; i++) {
            for (Sprite platform : sprites) {
                if (platform.intersects(sprite) && speedY > 0) {
                    sprite.moveUp();
                    setCanJump(true);
                    return;
                }
            }

            if (speedY > 0) {
                sprite.moveDown();
            } else {
                sprite.moveUp();
            }
        }
    }
}