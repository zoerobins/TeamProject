package org.nightshade.game;

import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;

public class Cloud {

    private final Image cloudImage = new Image("view/dark.png");

    public void showCloud(Renderer renderer, Client client, int x, int y){
        if(x+1200<client.getClientSprite().getPositionX()){
            int newXPos=client.getClientSprite().getPositionX()-1200;
            if (newXPos>x){
                x=newXPos;
            }
        }
        renderer.drawImage(cloudImage,x,y);
    }
}
