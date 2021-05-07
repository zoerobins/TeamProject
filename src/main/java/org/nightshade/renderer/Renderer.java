package org.nightshade.renderer;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Renderer {

    private final Group group;
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    /**
     * Constructor for the renderer class
     */
    public Renderer() {
        group = new Group();
        canvas = new Canvas(500, 500);
        group.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
    }

    /**
     * Constructor for the renderer class
     * @param w canvas width
     * @param h canvas height
     */
    public Renderer(int w, int h) {
        group = new Group();
        canvas = new Canvas(w, h);
        group.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public Group getGroup() {
        return group;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setWidth(int x) {
        canvas.setWidth(x);
    }

    public void setHeight(int x) {
        canvas.setHeight(x);
    }

    /**
     * draws an image to the canvas
     * @param image image to draw
     * @param x x position
     * @param y y position
     */
    public void drawImage(Image image, int x, int y) {
        graphicsContext.drawImage(image, x, y);
    }

    /**
     * draws an image to the canvas
     * @param image image to draw
     * @param x x position
     * @param y y position
     * @param w image width
     * @param h image height
     */
    public void drawImage(Image image, int x, int y, int w, int h) {
        graphicsContext.drawImage(image, x, y, w, h);
    }

    /**
     * draws a rectangle to the canvas
     * @param x x position
     * @param y y position
     * @param w width
     * @param h height
     * @param color color
     */
    public void drawRectangle(int x, int y, int w, int h, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y, w, h);
    }

    /**
     * draws a circle to the canvas
     * @param x x position
     * @param y y position
     * @param c circumference
     * @param color color
     */
    public void drawCircle(int x, int y, int c, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(x, y, c, c);
    }

    /**
     * draws a triangle to the canvas
     * @param xs x positions of the three vertices
     * @param ys y positions of the three vertices
     * @param color color
     */
    public void drawTriangle(double[] xs, double[] ys, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillPolygon(xs, ys, 3);
    }

}
