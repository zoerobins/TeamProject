package org.nightshade.networking;

import org.nightshade.gui.GuiHandler;
import org.nightshade.gui.Player;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientLogic /*implements Runnable*/ {

    private ObjectOutputStream objectOutput;
    private ObjectInputStream objectInput;
    private Client client;
    //private Thread thread;
    private Socket server;

    private ArrayList<Player> playersList = new ArrayList<>();
    private ArrayList<PlayerMoveMsg> msgsList = new ArrayList<>();

    public ClientLogic(String serverIp, int portValue, Client client) throws IOException {
        server = new Socket(serverIp, portValue);
        objectOutput = new ObjectOutputStream(server.getOutputStream());
        objectInput = new ObjectInputStream(server.getInputStream());
        this.client = client;
        //thread = new Thread(this);
        //thread.start();
        //run();
    }

    public ClientLogic() throws IOException {
        server = new Socket("127.0.0.1", 2222);
        objectOutput = new ObjectOutputStream(server.getOutputStream());
        objectInput = new ObjectInputStream(server.getInputStream());
        return;

    }

    //@Override
    public void run() {
        try {
            //sendPlayer(this.client.getName(), "NOT READY");
            //receivePlayers();
            while(true) {
                sendPlayer(this.client.getName(), GuiHandler.player.getReady());
                receivePlayers();
            }
            /*while(true) {
                sendToServer(this.client.getName(), 1, 2, true);
                waitForServer();
            }*/
        } catch (IOException | RuntimeException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public PlayerMoveMsg waitForServer() throws IOException, SocketException, RuntimeException, ClassNotFoundException {
        Object next = objectInput.readObject();
        while(next instanceof Player) {
            next = objectInput.readObject();
        }
        PlayerMoveMsg moveMsg = (PlayerMoveMsg) next;
        System.out.println(moveMsg.getName());
        System.out.println(moveMsg.getX());
        System.out.println(moveMsg.getY());
        System.out.println(moveMsg.isAlive());
        return moveMsg;
    }

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
            }
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

            System.out.println(newMoveMsg.getName());
            System.out.println(newMoveMsg.getX());
            System.out.println(newMoveMsg.getY());
            System.out.println(newMoveMsg.isAlive());

            if(objectInput.available() != 0) {
                next = objectInput.readObject();
            } else {
                break;
            }

        }

    }

    public ArrayList<PlayerMoveMsg> getMsgsList() {
        return msgsList;
    }

    public void sendToServer(String name, int x, int y, boolean alive) throws IOException {
        objectOutput.writeObject(new PlayerMoveMsg(name, x, y, alive));
    }

    public void sendPlayer(String name, String ready) throws IOException {
        objectOutput.writeObject(new Player(name, ready));
    }

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
            System.out.println(newPlayer.getName());
            System.out.println(newPlayer.getReady());
            if(objectInput.available() != 0) {
                next = objectInput.readObject();
            } else {
                break;
            }
        }

    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }
}
