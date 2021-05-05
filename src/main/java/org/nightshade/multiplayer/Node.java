package org.nightshade.multiplayer;

import java.util.concurrent.ThreadLocalRandom;

public enum Node {
    AIR,
    PLATFORM,
    RIGHT_MOVING_PLATFORM,
    LEFT_MOVING_PLATFORM,
    GROUND,
    LAVA,
    LEFT_ENEMY,
    RIGHT_ENEMY,
    POWERUP,
    END;

    public static Node getRandomNode(int i, int j, int width) {
        //random number between 1 and 100
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 100 + 1);

        //This is the starting platform, so the characters land on it when spawning in
        if (i == 6 && j == 5 || i == 6 && j == 6 || i == 6 && j == 7) {
            return Node.PLATFORM;
        }
        //Last column of blocks should be end nodes (the end of the level)
        if (j == width - 1) {
            return Node.END;
        }
        //this condition keeps the start of the level clear of enemies where the players will spawn
        if (i < 5 && j < 30) {
            return Node.AIR;
        }
        if (i == 11) {
            //will spawn ground 90% of the time, and water the other 10%
            if (randomNumber < 90) {
                return GROUND;
            } else {
                return LAVA;
            }
        }
        if (i == 10) {
            if (randomNumber <= 4) {
                return PLATFORM;
            } else if (randomNumber <= 5) {
                return RIGHT_ENEMY;
            } else if (randomNumber <= 5) {
                return LEFT_ENEMY;
            } else if(randomNumber <= 6){
                return POWERUP;
            }else {
                return AIR;
            }
        }
        if (i > 7) {
            if (randomNumber < 10) {
                return PLATFORM;
            } else if (randomNumber < 12) {
                return LEFT_MOVING_PLATFORM;
            }  else if (randomNumber <= 13) {
                return RIGHT_MOVING_PLATFORM;
            }else if (randomNumber < 14 && j > 20) {
                return RIGHT_ENEMY;
            } else {
                return AIR;
            }
        } else {
            if (randomNumber == 1 && j > 20) {
                return LEFT_ENEMY;
            } else {
                return AIR;
            }
        }
    }
}
