package org.nightshade.ai;

import org.nightshade.game.Sprite;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AILogic {

    public void moveChar(AI ai, ArrayList<Sprite> platformSprites, ArrayList<Sprite> groundSprites) {

        if (ai.getVelocity().getY() < 10) {
            ai.setVelocity(ai.getVelocity().add(0, 1));
        }

        ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.addAll(platformSprites);
        sprites.addAll(groundSprites);

        ai.moveX(sprites);

        for (Sprite sprite : sprites) {
            int closeness = ThreadLocalRandom.current().nextInt(2, (30) + 1);
            if (ai.getSprite().getPositionX() + ai.getSprite().getWidth() + closeness > sprite.getPositionX() &&
                    ai.getSprite().getPositionX() + 10 < sprite.getPositionX() &&
                    ai.getSprite().getPositionY() > sprite.getPositionY()) {
                ai.jump();
            }
        }

        ai.moveY(sprites);
    }
}