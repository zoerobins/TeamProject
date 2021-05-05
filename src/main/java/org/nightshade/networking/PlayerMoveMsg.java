package org.nightshade.networking;

import java.io.Serializable;

/**
 * PlayerMoveMsg class
 * Message holding a Player's name and coordinates which can be sent between Client and Server
 */
public class PlayerMoveMsg implements Serializable {

    private String name;
    private double x;
    private double y;
    private boolean alive;

    /**
     * Constructor for the PlayerMoveMsg class
     * Sets the variable values using the arguments provided
     * @param name Name of the Player
     * @param x x-coordinate of the Player
     * @param y y-coordinate of the Player
     * @param alive Whether the Player is alive
     */
    public PlayerMoveMsg(String name, int x, int y, boolean alive) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    /**
     * Returns the name of the Player
     * @return Name of the Player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the x-coordinate of the Player
     * @return x-coordinate of the Player
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the Player
     * @return y-coordinate of the Player
     */
    public double getY() {
        return y;
    }

    /**
     * Returns whether the Player is alive
     * @return Whether the Player is alive
     */
    public boolean isAlive() {
        return alive;
    }

}
