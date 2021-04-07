package org.nightshade.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.nightshade.Main;
import org.nightshade.multiplayer.Game;

public class ServerLogic {

    private int portValue;
    static Game game;
    static ServerSocket serverSocket;
    private static ArrayList<ClientThread> clientThreads;

    private static ArrayList<Integer> xPositions;
    private static ArrayList<Integer> yPositions;
    private static ArrayList<Boolean> alive;


    public ServerLogic(int portValue) throws IOException {
        this.portValue = portValue;
        serverSocket = new ServerSocket(portValue);
        System.out.println("Started game server");
        clientThreads = new ArrayList<>();
    }

    /*public static Game getGame() {
        return game;
    }*/

    public static ArrayList<ClientThread> getClientThreads() {
        return clientThreads;
    }

    public static void getPlayerPositions() {
        // receive and store messages from clients containing their positions
        int xPos = 0;
        int yPos = 0;
        boolean isAlive = true;
        xPositions.add(xPos);
        yPositions.add(yPos);
        alive.add(isAlive);

    }

    public static void sendPlayerPositions() {
        // send message to client with all Players' positions


    }

    public void waitForPlayers() throws IOException {
        int clientNo = 1;
        while(clientNo < 5) {
            Socket client = serverSocket.accept();
            System.out.println("Client arrived");
            System.out.println(client.getInetAddress().getHostName());
            System.out.println("Starting thread for client " + clientNo);
            ClientThread task = new ClientThread(client, game, clientNo);
            clientThreads.add(task);
            System.out.println("number of client threads: " + clientThreads.size());
            System.out.println("number of client threads: " + getClientThreads().size());
            clientNo++;
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
