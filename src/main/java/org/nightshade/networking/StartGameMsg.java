package org.nightshade.networking;

import java.io.Serializable;
import java.util.ArrayList;

public class StartGameMsg implements Serializable {

    private int numPlayers;
    private ArrayList<String> players;

    public StartGameMsg(int numPlayers, ArrayList<String> players) {
        this.numPlayers = numPlayers;
        this.players = players;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

}
