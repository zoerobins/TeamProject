package org.nightshade.multiplayer;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.nightshade.gui.Player;
import org.nightshade.multiplayer.Game;
import org.nightshade.networking.Client;

import java.util.ArrayList;

public class GameHandler {
    private final int levelWidth = 120;
    private Level level;
    private Client client;
    public GameHandler(Stage stage, String localGameClientName, Client client, ObservableList<Player> players){

        this.client = client;
        ArrayList<GameClient> gameClients = new ArrayList<>();
        GameClient localGameClient = new GameClient(localGameClientName);

        //send out localGameClient object / its name so it can be replicated on the other machines
        //add recieved objects into array / make new gameClient objects with the recieved names and add them into the array
        //if host - send this
        level = makeLevel(levelWidth);
        //


        for(Player player : players){
            GameClient newGameClient = new GameClient(player.getName());
            gameClients.add(newGameClient);
        }
        Game game = new Game(stage, localGameClient, gameClients, level, this.client);

    }
    public Level makeLevel(int levelWidth){
        //ArrayList<ArrayList <Node>> nodes = Level.getRandomNodes(12);
        //return new Level(12, nodes);
        ArrayList<ArrayList <Node>> nodes = Level.getLevel1();
        return new Level(levelWidth,nodes);
    }
}
