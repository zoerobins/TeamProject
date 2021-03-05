package org.nightshade.gamelogic;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.nightshade.renderer.Renderer;

public class Client {

    private boolean isLive;
    private boolean canJump;
    private Point2D velocity;
    private final Sprite clientSprite;


    public Client() {
        this.isLive = true;
        this.canJump = true;
        this.velocity = new Point2D(0,0);
        clientSprite = createCharacter();
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

    private Sprite createCharacter() {
        Sprite sprite = new Sprite(new Image("view/Body.png"),300,50);
     return sprite;
    }






}
