package org.nightshade.networking;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * StartGameMsg class
 * Message holding the number of players and an ArrayList of their names
 * Sent to clients once all players are ready to start the game
 */
public class StartGameMsg implements Serializable {

    private int numPlayers;
    private ArrayList<String> players;

    /**
     * Constructor for the StartGameMsg class
     * Sets the variables using the arguments provided
     * @param numPlayers Number of players
     * @param players ArrayList of players' names
     */
    public StartGameMsg(int numPlayers, ArrayList<String> players) {
        this.numPlayers = numPlayers;
        this.players = players;
    }

    /**
     * Returns the number of players
     * @return Number of players
     */
    public int getNumPlayers() {
        return this.numPlayers;
    }

    /**
     * Returns the ArrayList of players' names
     * @return ArrayList of players' names
     */
    public ArrayList<String> getPlayers() {
        return players;
    }

}
