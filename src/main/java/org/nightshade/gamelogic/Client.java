package org.nightshade.gamelogic;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Client {

    private boolean isLive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite clientSprite;

    public Client() {
        this.isLive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        clientSprite = createSprite();
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


    public Sprite getClientSprite() {
        return clientSprite;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getPositionX(), sprite.getPositionY());
    }

    public void jump() {
        if (canJump) {
            velocity = velocity.add(0, -30);
            canJump = false;
        }
    }

    public void kill(Group root, Node node) {
        isLive=false;
    }

    public Sprite createSprite() {
        Sprite sprite = new Sprite(new Image("view/Body.png"),300,50);
     return sprite;
    }



    public void moveX(int value,ArrayList<Sprite> platformSprites){
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(clientSprite)){
                    if (movingRight) {
                        if (getClientSprite().getPositionX() + 40 == platform.getPositionX()) {
                            return;
                        }
                    } else {
                        if (getClientSprite().getPositionX() == platform.getPositionX() + 60) {
                            return;
                        }
                    }
                }
            }
            getClientSprite().setPositionX(getClientSprite().getPositionX() + (movingRight ? 1 : -1));
        }
    }
    public void moveY(int value,ArrayList<Sprite> platformSprites){

        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite platform : platformSprites) {
                if (platform.intersects(clientSprite) && movingDown) {
                    getClientSprite().setPositionY(getClientSprite().getPositionY() - 1);
                    setCanJump(true);
                    return;
                }
            }
            getClientSprite().setPositionY(getClientSprite().getPositionY() + (movingDown ? 1 : -1));
        }

    }


}
