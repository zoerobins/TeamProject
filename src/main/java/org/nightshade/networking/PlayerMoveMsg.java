package org.nightshade.networking;

import java.io.Serializable;

public class PlayerMoveMsg implements Serializable {

    private String name;
    private double x;
    private double y;
    private boolean alive;

    public PlayerMoveMsg(String name, int x, int y, boolean alive) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isAlive() {
        return alive;
    }

}
