package org.nightshade.game;
import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

/**
 * Represents a moving platform
 */
public class MovingPlatform {
    private final Sprite sprite;
    private int speed;
    private Direction direction;
    private int offset;


    /** Creates a moving platform with the specified size, speed and direction
     * @param x The moving platform's width
     * @param y The moving platform's height
     * @param speed The speed of the moving platform
     * @param direction The direction of the moving platform
     */
    public MovingPlatform(int x, int y, int speed, Direction direction){
        this.sprite = new Sprite(new Image("img/game/dark-grass.png"),x,y);
        this.speed = speed;
        this.direction = direction;
        this.offset = 0;
    }

    /**
     * @return returns the platform's speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return returns the platform's direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @return returns the platform's sprite
     */
    public Sprite getSprite(){
        return this.sprite;
    }

    /** displays the platform using the renderer
     * @param renderer The renderer being used to draw the images
     * @param image The image the renderer is drawing
     * @param sprite The platform's sprite
     */
    public void displaySprite(Renderer renderer, Image image, Sprite sprite){
        renderer.drawImage(image, sprite.getX(), sprite.getY());
    }

    /**
     * moves the platforms
     */
    public void movePlatform(){
        if (this.direction == Direction.FORWARD) {
            if (offset > 180) {
                direction = Direction.BACKWARD;
            }
            else{
                offset = offset + speed;
                sprite.setX(sprite.getX() + speed);
            }
        } else {
            if (offset < -180) {
                direction = Direction.FORWARD;
            } else {
                offset = offset - speed;
                sprite.setX(sprite.getX() - speed);
            }
        }
    }
}