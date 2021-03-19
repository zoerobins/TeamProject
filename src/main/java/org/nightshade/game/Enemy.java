package org.nightshade.game;
import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class Enemy {

    private final Sprite sprite;
    private final int speed;
    private boolean direction;
    private int offset;

    public Enemy(int speed, int direction, int x, int y) {
        this.sprite = new Sprite(new Image("view/GameComponents/enemy.png"),x,y);
        this.speed = speed;
        this.offset = 0;
        this.direction = direction == 1;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
    }

    public void moveEnemy(){
        if (this.direction){
            if (offset > 180) {
                direction = false;
            }
            else {
                offset = offset+speed;
                sprite.setX(sprite.getX()+speed);
            }
        } else{
            if (offset < -180) {
                direction = true;
            }
            else {
                offset = offset-speed;
                sprite.setX(sprite.getX()-speed);
            }
        }
    }



}
