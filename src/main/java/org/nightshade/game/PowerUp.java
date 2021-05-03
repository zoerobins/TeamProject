package org.nightshade.game;

import javafx.scene.image.Image;

public class PowerUp extends Sprite {

    private boolean collected;
    private final Ability ability;

    public PowerUp(Image image, int x, int y) {
        super(image, x, y);
        this.collected = false;
        this.ability = assignRandomAbility();
    }

    public Ability getAbility() {
        return this.ability;
    }

    public boolean getCollected() {
        return !this.collected;
    }

    public void collect() {
        this.collected = true;
    }

    private Ability assignRandomAbility() {
        double randomNum = Math.random();

        if (randomNum < 0.33) {
            return Ability.JUMPBOOST;
        } else if (randomNum < 0.67) {
            return Ability.SPEEDBOOST;
        } else {
            return Ability.SHIELD;
        }
    }
}
