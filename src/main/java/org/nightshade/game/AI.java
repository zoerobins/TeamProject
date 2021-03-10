package org.nightshade.game;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AI{

    private final boolean isLive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite AISprite = new Sprite(new Image("view/Body.png"),300,50);
    private final int speed;

    int startX = ThreadLocalRandom.current().nextInt(250, (350) + 1);
    int startY = ThreadLocalRandom.current().nextInt(30, (80) + 1);


    public int getSpeed() {
        return speed;
    }

    public AI(int speed) {
        this.isLive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        this.speed = speed;
    }
    public Sprite createSprite() {
        return new Sprite(new Image("view/Body.png"),startX,startY);
    }

    public double getWidth(){
        return AISprite.getWidth();
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getPositionX(), sprite.getPositionY());
    }
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public Sprite getAISprite() {
        return AISprite;
    }

    public void jump() {
        if (canJump) {
            velocity = velocity.add(0, -30);
            canJump = false;
        }
    }

    public void moveX(int value,ArrayList<Sprite> platformSprites){
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(AISprite)){
                    if(movingRight){
                        getAISprite().setPositionX(getAISprite().getPositionX() - 1);
                    } else {
                        getAISprite().setPositionX(getAISprite().getPositionX() + 1);
                    }
                    return;
                }
            }
            getAISprite().setPositionX(getAISprite().getPositionX() + (movingRight ? 1 : -1));
        }
    }
    public void moveY(int value,ArrayList<Sprite> platformSprites){

        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(AISprite) && movingDown) {
                    getAISprite().setPositionY(getAISprite().getPositionY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            getAISprite().setPositionY(getAISprite().getPositionY() + (movingDown ? 1 : -1));
        }

    }

}