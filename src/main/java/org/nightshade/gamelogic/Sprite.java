package org.nightshade.gamelogic;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class Sprite {

    private final Image image;
    private double positionX;
    private double positionY;
    private final double width;
    private final double height;

    public Sprite(Image image, int x, int y) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        positionX = x;
        positionY = y;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void render(Renderer renderer,int x, int y) {
        renderer.drawImage(image, x, y);
    }

    public int getPositionY() {
        return (int) positionY;
    }

    public int getPositionX() {
        return (int) positionX;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Sprite spr) {
        return spr.getBoundary().intersects(this.getBoundary());
    }
}