package org.nightshade.multiplayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.nightshade.multiplayer.Direction;

public class DirectionTest {

    @Test
    public void testGetRandomDirection() {
        org.nightshade.multiplayer.Direction direction = org.nightshade.multiplayer.Direction.getRandomDirection();
        Assertions.assertNotNull(direction);

        boolean hasBeenForward = false;
        boolean hasBeenBackward = false;

        for (int i = 0; i < 100; i ++) {
            direction = org.nightshade.multiplayer.Direction.getRandomDirection();
            if (direction.equals(org.nightshade.multiplayer.Direction.FORWARD)) {
                hasBeenForward = true;
            } else if (direction.equals(Direction.BACKWARD)) {
                hasBeenBackward = true;
            }
        }

        Assertions.assertTrue(hasBeenForward);
        Assertions.assertTrue(hasBeenBackward);
    }

}

