package org.nightshade.networking;

//import java.io.DataInputStream;
//import java.net.DatagramSocket;

public class PlayerMoveMsg implements Msg {

    private int msgType = Msg.PLAYER_MOVE_MSG;
    private String name;
    private int x;
    private int y;
    private boolean alive;
    private Client c; // should this be a Client from multiplayer or networking ??

    public PlayerMoveMsg(String name, int x, int y, boolean alive) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public PlayerMoveMsg(Client c) {
        this.c = c;
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

    /*@Override
    public void send(DatagramSocket ds, String IP, int UDP_Port) {
        // add code here

    }

    @Override
    public void parse(DataInputStream dis) {
        // add code here

    }*/

}
