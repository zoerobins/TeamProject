package org.nightshade.networking;

import org.nightshade.gui.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ClientLogic class
 * The logic for the Client, which connects the Client to the Server
 */
public class ClientLogic {

    private final ObjectOutputStream objectOutput;
    private final ObjectInputStream objectInput;
    private final Socket socket;
    private final ArrayList<Player> playersList = new ArrayList<>();
    private final ArrayList<PlayerMoveMsg> msgsList = new ArrayList<>();

    /**
     * Constructor for the ClientLogic class
     * Creates a Socket connection to the Server and initialises the object streams
     * @param serverIp IP address of the Server
     * @param portValue Port number for the Server
     * @param client Client object which created this ClientLogic object
     * @throws IOException
     */
    public ClientLogic(String serverIp, int portValue, Client client) throws IOException {
        socket = new Socket(serverIp, portValue);
        objectOutput = new ObjectOutputStream(socket.getOutputStream());
        objectInput = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Constructor for the ClientLogic class
     * Creates a Socket connection to the Server and initialises the object streams
     * @throws IOException
     */
    public ClientLogic() throws IOException {
        socket = new Socket("127.0.0.1", 2222);
        objectOutput = new ObjectOutputStream(socket.getOutputStream());
        objectInput = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Reads in PlayerMoveMsgs received from the Server and adds them to an ArrayList
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void receiveMoveMsgs() throws IOException, ClassNotFoundException {
        PlayerMoveMsg newMoveMsg;
        Object next;
        next = objectInput.readObject();

        while (next instanceof Player) {
            next = objectInput.readObject();
        }

        while(next instanceof PlayerMoveMsg) {
            newMoveMsg = (PlayerMoveMsg) next;

            if (msgsList.size() == 0) {
                msgsList.add(newMoveMsg);
            } else {
                boolean msgAdded = false;

                for (int i=0; i<msgsList.size(); i++) {
                    if(newMoveMsg.getName().equals(msgsList.get(i).getName())) {
                        msgsList.set(i, newMoveMsg);
                        msgAdded = true;
                        break;
                    }
                }
                if (!msgAdded) {
                    msgsList.add(newMoveMsg);
                }
            }

            if (objectInput.available() != 0) {
                next = objectInput.readObject();
            } else {
                break;
            }
        }
    }

    /**
     * Returns an ArrayList of the received PlayerMoveMsgs
     * @return ArrayList of received PlayerMoveMsgs
     */
    public ArrayList<PlayerMoveMsg> getMsgsList() {
        return msgsList;
    }

    /**
     * Sends a new PlayerMoveMsg object containing the Player's updated position to the Server
     * @param name Name of the Player
     * @param x x-coordinate of the Player's position
     * @param y y-coordinate of the Player's position
     * @param alive Whether the Player is alive
     * @throws IOException
     */
    public void sendToServer(String name, int x, int y, boolean alive) throws IOException {
        objectOutput.writeObject(new PlayerMoveMsg(name, x, y, alive));
    }

    /**
     * Sends a Player object to the Server
     * @param name Name of the Player
     * @param ready Whether the Player is ready to start the game
     * @throws IOException
     */
    public void sendPlayer(String name, String ready) throws IOException {
        objectOutput.writeObject(new Player(name, ready));
    }

    /**
     * Returns an ArrayList of the received Player objects
     * @return ArrayList of received Players
     */
    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    /**
     * Reads in and returns the StartGameMsg receive from the Server
     * @return StartGameMsg received from the Server
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public StartGameMsg receiveStartMsg() throws IOException, ClassNotFoundException {
        StartGameMsg startGame = (StartGameMsg) objectInput.readObject();
        return startGame;
    }

    /**
     * Closes the Socket connection to the Server
     * @throws IOException
     */
    public void closeSocket() throws IOException {
        socket.close();
    }
}