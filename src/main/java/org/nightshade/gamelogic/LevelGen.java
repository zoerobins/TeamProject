package org.nightshade.gamelogic;

import javafx.scene.image.Image;
import org.nightshade.renderer.Renderer;

//import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LevelGen {
    private final int width;
    private final int height;
    Renderer renderer = new Renderer();
    ArrayList<ArrayList<NodeType>> level= new ArrayList<>();
    ArrayList<Sprite> platformSprites = new ArrayList<>();
    Image img = new Image("view/Grass.png");

    public LevelGen(int width1) {
        this.width = width1;
        this.level = createLevel(width);
        this.height = 12;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private ArrayList<ArrayList<NodeType>> createLevel(int width) {

        for (int i=0;i<12;i++){
            level.add(new ArrayList<>());
            for (int j=0;j<width;j++){
                level.get(i).add(getRandomNode(i,j));
            }
        }
        return level;
    }

    public ArrayList<Sprite> createPlatformSprites(){
        for (int i=0 ; i <12 ; i++){
            for(int j=0 ; j < width ; j++){
                if(level.get(i).get(j)==NodeType.PLATFORM) {
                    platformSprites.add(new Sprite(img, j * 60, i * 60));
                }
            }
        }
        return platformSprites;
    }

    private NodeType getRandomNode(int i,int j){
        int number = ThreadLocalRandom.current().nextInt(0, 100 + 1);

        if(i==6&&j==5||i==6&&j==6||i==6&&j==7){
            return NodeType.PLATFORM;
        }
        if(i<5&&j<20){
            return NodeType.AIR;
        }
        if(i==11){
            if(j==width-1) {
                return NodeType.END;
            } else if(number<90) {
                return NodeType.PLATFORM;
            } else{
                return NodeType.AIR;
            }
        }
        else if(i>7){
            if(j==width-1){
                return NodeType.END;
            } else if(number<20) {
                return NodeType.PLATFORM;
            } else if(number>=20&&number<22){
                return NodeType.ENEMY;
            } else{
                return NodeType.AIR;
            }
        }
        else{
            if(j==width-1){
                return NodeType.END;
            } else if(number==1){
                return NodeType.PLATFORM;
            } else if(number==22){
                return NodeType.ENEMY;
            } else{
                return NodeType.AIR;
            }
        }

    }




}
