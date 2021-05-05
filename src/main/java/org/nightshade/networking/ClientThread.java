package org.nightshade.networking;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import org.nightshade.gui.Player;

/**
 * ClientThread class
 * Runs a thread for sending and receiving messages to and from one client
 */
public class ClientThread implements Runnable {

    private final ServerLogic serverLogic;
    private final int clientNo;
    private final Socket socket;
    private ArrayList<PlayerMoveMsg> moveMsgs;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private boolean localPlayerReady;

    /**
     * Constructor for the ClientThread class
     * Initialises the object streams
     * @param client Socket that the Client used to connect to the Server
     * @param clientNo Client number
     * @param serverLogic ServerLogic object that which creates this ClientThread object
     */
    public ClientThread(Socket client, int clientNo, ServerLogic serverLogic) {
        this.clientNo = clientNo;
        this.socket = client;
        this.serverLogic = serverLogic;
        this.moveMsgs = serverLogic.getMoveMsgs();
        localPlayerReady = false;

        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Streams not set up for client");
        }

    }

    /**
     * Thread 'run' method
     * Uses loops to make repeated calls to methods which send and receive objects
     */
    @Override
    public void run() {
        try {
            while(!localPlayerReady) {
                receivePlayers();
            }
            sendStartMsg();
            while (true) {
                receiveMoveMsg();
                sendMoveMsgs();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client " + clientNo + " left");
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Reads in Player objects received from the Client and adds them to an ArrayList
     */
    public void receivePlayers() {
        Player player;
        try {
            player = (Player) objectInputStream.readObject();
            if(player.getReady().equals("READY")) {
                serverLogic.addPlayerName(player.getName());
                localPlayerReady = true;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads in PlayerMoveMsgs received from the Client and adds them to an ArrayList
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void receiveMoveMsg() throws IOException, ClassNotFoundException {

        try {
            Object next = objectInputStream.readObject();
            while(next instanceof Player) {
                next = objectInputStream.readObject();
            }
            PlayerMoveMsg moveMsg = (PlayerMoveMsg) next;

            if(serverLogic.getMoveMsgs().size() == 0) {
                serverLogic.addMsg(moveMsg);
            } else {
                boolean msgAdded = false;
                for(int j=0; j<serverLogic.getMoveMsgs().size(); j++) {
                    if((serverLogic.getMoveMsgs().get(j).getName() != null) && serverLogic.getMoveMsgs().get(j).getName().equals(moveMsg.getName())) {
                        serverLogic.replaceMsg(j, moveMsg);
                        msgAdded = true;
                        break;
                    }
                }
                if(!msgAdded) {
                    serverLogic.addMsg(moveMsg);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sends PlayerMoveMsg objects from the ArrayList to the Client
     * @throws IOException
     */
    public void sendMoveMsgs() throws IOException {
        moveMsgs = serverLogic.getMoveMsgs();
        for (PlayerMoveMsg moveMsg : moveMsgs) {
            objectOutputStream.writeObject(moveMsg);
        }
    }

    /**
     * Sends a StartGameMsg to the Client when all players are ready to start
     * @throws IOException
     */
    public void sendStartMsg() throws IOException {
        boolean allReady = false;
        while(!allReady) {
            ArrayList<String> playerNames = serverLogic.getPlayerNames();
            if(playerNames.size() == serverLogic.getNumClients()/2) {
                objectOutputStream.writeObject(new StartGameMsg(playerNames.size(), playerNames));
                allReady = true;
            }
        }
    }
}
