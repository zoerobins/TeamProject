package org.nightshade.networking;

import org.nightshade.gui.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ServerLogic class
 * The logic for the Server, which accept client connections
 */
public class ServerLogic {

    private int portValue;
    static ServerSocket serverSocket;
    private static ArrayList<ClientThread> clientThreads = new ArrayList<>();

    private Socket client;
    private ArrayList<Socket> clientSockets = new ArrayList<>();

    int numClients;

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<PlayerMoveMsg> moveMsgs = new ArrayList<>();


    /**
     * Constructor for the ServerLogic class
     * Creates a new ServerSocket and calls a method to wait for clients to connect
     * @param portValue Port number for the Server
     * @throws IOException
     */
    public ServerLogic(int portValue) throws IOException {
        this.portValue = portValue;
        serverSocket = new ServerSocket(portValue);
        this.numClients = 0;
        System.out.println("Started game server");
        waitForPlayers();
    }

    /**
     * Returns an ArrayList of PlayerMoveMsgs received from clients
     * @return ArrayList of received PlayerMoveMsgs
     */
    public ArrayList<PlayerMoveMsg> getMoveMsgs() {
        return moveMsgs;
    }

    /**
     * Adds a received PlayerMoveMsg to the ArrayList
     * @param moveMsg Received PlayerMoveMsg object
     */
    public void addMsg(PlayerMoveMsg moveMsg) {
        moveMsgs.add(moveMsg);
    }

    /**
     * Replaces a PlayerMoveMsg at a specified index with another
     * @param index Index position of the PlayerMoveMsg in the ArrayList
     * @param moveMsg Received PlayerMoveMsg object
     */
    public void replaceMsg(int index, PlayerMoveMsg moveMsg) {
        moveMsgs.set(index, moveMsg);
    }

    public static ArrayList<ClientThread> getClientThreads() {
        return clientThreads;
    }

    public int getNumClients() {
        return this.numClients;
    }

    /**
     * Increments the number of clients connected
     */
    public void incNumClients() {
        this.numClients += 1;
    }

    /**
     * Returns an ArrayList of Player objects received from clients
     * @return ArrayList of Player objects received
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Adds a received Player to the ArrayList
     * @param player Received Player object
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Replaces a Player object at a specified index with another
     * @param index Index position of the Player in the ArrayList
     * @param player Received Player object
     */
    public void replacePlayer(int index, Player player) {
        players.set(index, player);
    }

    public Socket getClientSocket() {
        return client;
    }

    public ArrayList<Socket> getClientSockets() {
        return clientSockets;
    }

    /**
     * Waits for clients & accepts connections, and creates a new ClientThread for each Client
     * @throws IOException
     */
    public void waitForPlayers() throws IOException {
        int clientNo = 1;
        while(clientNo < 10) {
            client = serverSocket.accept();
            clientSockets.add(client);
            System.out.println("Client arrived");
            //System.out.println(client.getInetAddress().getHostName());
            //System.out.println("Starting thread for client " + clientNo);
            ClientThread task = new ClientThread(client, clientNo, this);
            clientThreads.add(task);
            clientNo++;
            incNumClients();
            new Thread(task).start();
        }
    }

    /**
     * Closes the ServerSocket
     */
    public void kill() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not close serverSocket");
        }

    }
}
