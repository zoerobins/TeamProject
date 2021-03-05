package org.nightshade.gamelogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class Cloud {

    public Cloud(){

    }

    private final Image cloudImage = new Image( "view/cloud.png");

    public void showCloud(Renderer renderer, int x, int y){
        renderer.drawImage(cloudImage,x,y);
    }

    public void showCloud(Renderer renderer, int x, int y, int w, int h){
        renderer.drawImage(cloudImage,x,y,w,h);
    }

}
