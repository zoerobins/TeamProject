package org.nightshade.networking;

import org.nightshade.gui.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerLogic {

    private int portValue;
    static ServerSocket serverSocket;
    private static ArrayList<ClientThread> clientThreads = new ArrayList<>();

    private Socket client;
    private ArrayList<Socket> clientSockets = new ArrayList<>();

    int numClients;

    ArrayList<Player> players = new ArrayList<>();

    ArrayList<PlayerMoveMsg> moveMsgs = new ArrayList<>();


    public ServerLogic(int portValue) throws IOException {
        this.portValue = portValue;
        serverSocket = new ServerSocket(portValue);
        this.numClients = 0;
        System.out.println("Started game server");
        waitForPlayers();
    }

    public ArrayList<PlayerMoveMsg> getMoveMsgs() {
        return moveMsgs;
    }

    public static ArrayList<ClientThread> getClientThreads() {
        return clientThreads;
    }

    public int getNumClients() {
        return this.numClients;
    }

    public void incNumClients() {
        this.numClients += 1;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void replacePlayer(int index, Player player) {
        players.set(index, player);
    }

    public Socket getClientSocket() {
        return client;
    }

    public ArrayList<Socket> getClientSockets() {
        return clientSockets;
    }

    public void waitForPlayers() throws IOException {
        int clientNo = 1;
        while(clientNo < 5) {
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

    public void kill() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not close serverSocket");
        }

    }
}
