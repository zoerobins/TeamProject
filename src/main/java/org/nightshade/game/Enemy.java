package org.nightshade.game;
import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class Enemy {

    private final Sprite sprite;
    private final int speed;
    private Direction direction;
    private int offset;

    public Enemy(int speed, int x, int y) {
        this.sprite = new Sprite(new Image("img/game/enemy.png"),x,y);
        this.speed = speed;
        this.offset = 0;
        this.direction = Direction.getRandomDirection();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
    }

    public void moveEnemy(){
        if (this.direction.equals(Direction.FORWARD)) {
            if (offset > 180) {
                direction = Direction.BACKWARD;
            } else {
                offset += speed;
                sprite.setX(sprite.getX() + speed);
            }
        } else {
            if (offset < -180) {
                direction = Direction.FORWARD;
            } else {
                offset -= speed;
                sprite.setX(sprite.getX() - speed);
            }
        }
    }
}