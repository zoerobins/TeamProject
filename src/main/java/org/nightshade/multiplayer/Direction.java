package org.nightshade.multiplayer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * enum class containing constants FORWARD and BACKWARD
 */
public enum Direction {
    FORWARD, BACKWARD;
    /**
     * getRandomDirection getter method returning the directions of the character
     * @return next direction the character will make and the size of it
     */
    public static Direction getRandomDirection() {
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(FORWARD);
        directions.add(BACKWARD);
        return directions.get(ThreadLocalRandom.current().nextInt(directions.size()));
    }
}
