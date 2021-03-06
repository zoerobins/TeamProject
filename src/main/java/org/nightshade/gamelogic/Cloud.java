package org.nightshade.gamelogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class Cloud {

    public Image getCloudImage() {
        return cloudImage;
    }

    public Cloud(){

    }

    private final Image cloudImage = new Image( "view/cloud.png");

    public void showCloud(Renderer renderer, int x, int y){
        renderer.drawImage(cloudImage,x,y);
        renderer.drawImage(cloudImage,x,y-220);
        renderer.drawImage(cloudImage,x,y+220);

    }

    public void showCloud(Renderer renderer, int x, int y, int w, int h){
        renderer.drawImage(cloudImage,x,y,w,h);
    }

}
