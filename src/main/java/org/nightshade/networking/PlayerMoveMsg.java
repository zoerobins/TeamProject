package org.nightshade.networking;

import java.io.Serializable;

public class PlayerMoveMsg implements Serializable {

    private String name;
    private int x;
    private int y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return alive;
    }

}
