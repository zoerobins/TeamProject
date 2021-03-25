package org.nightshade.game;

import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class MovingPlatform {

    private final Sprite sprite;
    private int speed;
    private boolean direction;
    private int offset;

    public MovingPlatform(int x, int y, int speed, boolean direction){

        this.sprite = new Sprite(new Image("view/GameComponents/DarkGrass.png"),x,y);
        this.speed = speed;
        this.direction = direction;
        this.offset = 0;

    }

    public int getSpeed() {
        return speed;
    }

    public boolean getDirection() {
        return direction;
    }

    public Sprite getSprite(){
        return this.sprite;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
    }

    public void movePlatform(){

        if (this.direction) {
            if (offset > 180) {
                direction = false;
            }
            else{
                offset = offset + speed;
                sprite.setX(sprite.getX() + speed);
            }
        } else {
            if (offset < -180) {
                direction = true;
            } else {
                offset = offset - speed;
                sprite.setX(sprite.getX() - speed);
            }
        }
    }


}
