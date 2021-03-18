package org.nightshade.game;

import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class MovingPlatform {

    private final Sprite mvSprite;
    private int speed;
    private boolean direction;
    private int offset;

    public MovingPlatform(int x, int y, int speed, int direction){

        this.mvSprite = new Sprite(new Image("view/GameComponents/DarkGrass.png"),x,y);
        this.speed = speed;
        this.direction = direction == 1;
        this.offset = 0;

    }

    public Sprite getmvSprite(){
        return this.mvSprite;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getPositionX(), sprite.getPositionY());
    }

    public void movePlatform(){

        if (this.direction) {
            if (offset > 180) {
                direction = false;
            }
            else{
                offset = offset + speed;
                mvSprite.setPositionX(mvSprite.getPositionX() + speed);
            }
        } else {
            if (offset < -180) {
                direction = true;
            } else {
                offset = offset - speed;
                mvSprite.setPositionX(mvSprite.getPositionX() - speed);
            }
        }
    }


}
