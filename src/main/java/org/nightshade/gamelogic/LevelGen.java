package org.nightshade.gamelogic;

import org.nightshade.renderer.Renderer;
import javafx.scene.paint.Color;
import java.awt.*;

public class LevelGen {
    Renderer renderer = new Renderer();







    public void drawPlatform(int x, int y, int w, int h, Color color){
        renderer.drawRectangle(x,y,w,h,color);
    }


}
