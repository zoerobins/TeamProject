package org.nightshade.game;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.nightshade.renderer.Renderer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LevelGen {
    private final int width;
    ArrayList<ArrayList<NodeType>> level;
    ArrayList<Sprite> platformSprites = new ArrayList<>();

    Image grass = new Image("view/Grass.png");

    public LevelGen(int width) {
        this.width = width;
        this.level = createLevel(width);
    }

    private ArrayList<ArrayList<NodeType>> createLevel(int width) {
        level=new ArrayList<>();
        for (int i=0;i<12;i++){
            level.add(new ArrayList<>());
            for (int j=0;j<width;j++){
                level.get(i).add(getRandomNode(i,j));
            }
        }
        return level;
    }

    public ArrayList<Sprite> createPlatformSprites(Renderer renderer){
        //12 is the amount of blocks vertically (12*60=720, the canvas height) - using a variable gave an error for some reason?
        for (int i=0 ; i < 12 ; i++){
            for(int j=0 ; j < width ; j++){
                if(level.get(i).get(j)==NodeType.PLATFORM) {
                    platformSprites.add(new Sprite(grass, j * 60, i * 60));
                } else if(level.get(i).get(j)==NodeType.ENEMY) {
                    int speed = ThreadLocalRandom.current().nextInt(0, (5) + 1);
                    int direction = ThreadLocalRandom.current().nextInt(0, (1) + 1);
                    Game.enemies.add(new Enemy(speed,direction,j*60,i*60));
                } else if(level.get(i).get(j)==NodeType.END){
                    renderer.drawRectangle(j*60,i*60,60,60, Color.GREEN);
                }
            }
        }
        return platformSprites;
    }

    //made public for test
    public NodeType getRandomNode(int i,int j){
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
            } else if(number<22){
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
