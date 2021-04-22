package org.nightshade.multiplayer;

import javafx.stage.Stage;
import org.nightshade.multiplayer.Game;
import org.nightshade.networking.Client;

import java.util.ArrayList;

public class GameHandler {
    private final int levelWidth = 120;
    private Level level;
    private Client client;
    public GameHandler(Stage stage, String localGameClientName, Client client){

        this.client = client;
        ArrayList<GameClient> gameClients = new ArrayList<>();
        GameClient localGameClient = new GameClient(localGameClientName);

        //send out localGameClient object / its name so it can be replicated on the other machines
        //add recieved objects into array / make new gameClient objects with the recieved names and add them into the array
        //if host - send this
        level = makeLevel(levelWidth);
        //

        Game game = new Game(stage, localGameClient, gameClients, level, this.client);
    }
    public Level makeLevel(int levelWidth){
        return new Level(levelWidth);
    }
}
