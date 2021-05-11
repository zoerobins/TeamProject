package org.nightshade.multiplayer;
import javafx.scene.image.Image;
/**
 * class containing everything about the enemies inside the track
 */
public class Enemy {

    private final Sprite sprite;
    private final int speed;
    private Direction direction;
    private int offset;
    /**
     * constructor of the enemy unit
     * @param speed speed of the enemy
     * @param x x-coordinate of the enemy
     * @param y y-coordinate of the enemy
     */
    public Enemy(int speed, int x, int y,Direction direction) {
        Image spriteImage = new Image("img/game/enemy.png");
        this.sprite = new Sprite(spriteImage, x, y);
        this.speed = speed;
        this.offset = 0;
        this.direction = direction;
    }
    /**
     * getSprite method for returning the enemy in the track
     * @return the enemy
     */
    public Sprite getSprite() {
        return sprite;
    }
    /**
     * moveEnemy method is the method for moving the enemy around the track
     */
    public void moveEnemy() {
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