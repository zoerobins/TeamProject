package org.nightshade.gamelogic;

import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

public class Cloud {

    public Image getCloudImage() {
        return cloudImage;
    }

    public Cloud(){

    }

    private final Image cloudImage = new Image("view/dark.png");

    public void showCloud(Renderer renderer,Client client,  int x, int y){
        if(x<client.getClientSprite().getPositionX()-1200){
            x=client.getClientSprite().getPositionX()-1200;
        }
        renderer.drawImage(cloudImage,x,y);
    }

}
