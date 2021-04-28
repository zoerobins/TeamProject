package org.nightshade.networking;

import org.nightshade.gui.GuiHandler;
import org.nightshade.gui.Player;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * ClientLogic class
 * The logic for the Client, which connects the Client to the Server
 */
public class ClientLogic /*implements Runnable*/ {

    private ObjectOutputStream objectOutput;
    private ObjectInputStream objectInput;
    private Client client;
    //private Thread thread;
    private Socket server;

    private ArrayList<Player> playersList = new ArrayList<>();
    private ArrayList<PlayerMoveMsg> msgsList = new ArrayList<>();

    /**
     * Constructor for the ClientLogic class
     * Creates a Socket connection to the Server and initialises the object streams
     * @param serverIp IP address of the Server
     * @param portValue Port number for the Server
     * @param client Client object which created this ClientLogic object
     * @throws IOException
     */
    public ClientLogic(String serverIp, int portValue, Client client) throws IOException {
        server = new Socket(serverIp, portValue);
        objectOutput = new ObjectOutputStream(server.getOutputStream());
        objectInput = new ObjectInputStream(server.getInputStream());
        this.client = client;
        //thread = new Thread(this);
        //thread.start();
        //run();
    }

    /**
     * Constructor for the ClientLogic class
     * Creates a Socket connection to the Server and initialises the object streams
     * @throws IOException
     */
    public ClientLogic() throws IOException {
        server = new Socket("127.0.0.1", 2222);
        objectOutput = new ObjectOutputStream(server.getOutputStream());
        objectInput = new ObjectInputStream(server.getInputStream());
        return;

    }

    //@Override
    /*public void run() {
        try {
            //sendPlayer(this.client.getName(), "NOT READY");
            //receivePlayers();
            while(true) {
                sendPlayer(this.client.getName(), GuiHandler.player.getReady());
                receivePlayers();
            }
            //while(true) {
                //sendToServer(this.client.getName(), 1, 2, true);
                //waitForServer();
            //}
        } catch (IOException | RuntimeException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }*/

    /*public PlayerMoveMsg waitForServer() throws IOException, SocketException, RuntimeException, ClassNotFoundException {
        Object next = objectInput.readObject();
        while(next instanceof Player) {
            next = objectInput.readObject();
        }
        PlayerMoveMsg moveMsg = (PlayerMoveMsg) next;
        //System.out.println(moveMsg.getName());
        //System.out.println(moveMsg.getX());
        //System.out.println(moveMsg.getY());
        //System.out.println(moveMsg.isAlive());
        return moveMsg;
    }*/

    /**
     * Reads in PlayerMoveMsgs received from the Server and adds them to an ArrayList
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void receiveMoveMsgs() throws IOException, ClassNotFoundException {
        PlayerMoveMsg newMoveMsg;
        Object next;
        next = objectInput.readObject();
        while(next instanceof Player) {
            next = objectInput.readObject();
        }
        while(next instanceof PlayerMoveMsg) {
            newMoveMsg = (PlayerMoveMsg) next;
            if(msgsList.size() == 0) {
                msgsList.add(newMoveMsg);
            } else {
                boolean msgAdded = false;
                for(int i=0; i<msgsList.size(); i++) {
                    if(newMoveMsg.getName().equals(msgsList.get(i).getName())) {
                        msgsList.set(i, newMoveMsg);
                        msgAdded = true;
                        break;
                    }
                }
                if(!msgAdded) {
                    msgsList.add(newMoveMsg);
                }
            }


            //System.out.println(newMoveMsg.getName());
            //System.out.println(newMoveMsg.getX());
            //System.out.println(newMoveMsg.getY());
            //System.out.println(newMoveMsg.isAlive());

            if(objectInput.available() != 0) {
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


    public void clearMsgsList() {
        msgsList.clear();
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
     * Reads in Player objects received from the Server and adds them to an ArrayList
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void receivePlayers() throws IOException, ClassNotFoundException {
        Player newPlayer;
        Object next;
        next = objectInput.readObject();
        while(next instanceof Player) {
            newPlayer = (Player) next;
            if(playersList.size() == 0) {
                playersList.add(newPlayer);
            }
            boolean playerAdded = false;
            for(int i=0; i<playersList.size(); i++) {
                if(playersList.get(i).getName().equals(newPlayer.getName())) {
                    playersList.set(i, newPlayer);
                    playerAdded = true;
                    break;
                }
            }
            if(!playerAdded) {
                playersList.add(newPlayer);
            }

            //playersList.add(newPlayer);
            //System.out.println(newPlayer.getName());
            //System.out.println(newPlayer.getReady());
            if(objectInput.available() != 0) {
                next = objectInput.readObject();
            } else {
                break;
            }
        }

    }

    /**
     * Returns an ArrayList of the received Player objects
     * @return ArrayList of received Players
     */
    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    /**
     * Closes the Socket connection to the Server
     * @throws IOException
     */
    public void closeSocket() throws IOException {
        server.close();
    }

}
