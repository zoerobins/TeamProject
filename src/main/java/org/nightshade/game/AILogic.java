package org.nightshade.game;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AILogic {

    public void moveChar(AI ai, ArrayList<Sprite> platformSprites){

        if (ai.getVelocity().getY() < 10) {
            ai.setVelocity(ai.getVelocity().add(0,1));
        }

        ai.moveX(ai.getSpeed(), platformSprites);

        for (Sprite platform : platformSprites) {

            int closeness = ThreadLocalRandom.current().nextInt(2, (30) + 1);

            if(ai.getAISprite().getPositionX()+ai.getWidth()+closeness > platform.getPositionX()&&
                    ai.getAISprite().getPositionX() + 10 < platform.getPositionX() &&
                    ai.getAISprite().getPositionY() > platform.getPositionY()){
                ai.jump();
            }
        }

        ai.moveY((int)ai.getVelocity().getY(), platformSprites);

    }






}
