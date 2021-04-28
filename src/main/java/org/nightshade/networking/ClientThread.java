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
    private int clientNo;
    private Socket socket;
    private ArrayList<Socket> clientSockets;
    private ArrayList<ClientThread> clientsThreads;
    private ArrayList<PlayerMoveMsg> moveMsgs;
    private ArrayList<Player> players;

    private ObjectOutputStream objectOutput1;
    private ObjectInputStream objectInput1;

    private boolean allPlayersReady;

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
        allPlayersReady = false;

        // create the streams:
        try {
            objectOutput1 = new ObjectOutputStream(socket.getOutputStream());
            objectInput1 = new ObjectInputStream(socket.getInputStream());
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
            while(!allPlayersReady) {
                receivePlayers();
                sendPlayers();
            }
            sendStartMessage();
            while(true) {
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
            player = (Player) objectInput1.readObject();

            if(player.getName().equals("ALLPLAYERSREADY") && player.getReady().equals("ALLPLAYERSREADY")) {
                System.out.println("all players ready");
                serverLogic.addReadyValue(allPlayersReady);
                System.out.println(serverLogic.isReadyToStartGame());
                allPlayersReady = true;
                return;
            }

            if(serverLogic.getPlayers().size() == 0) {
                serverLogic.addPlayer(player);
            } else {
                boolean playerAdded = false;
                for(int j=0; j<serverLogic.getPlayers().size(); j++) {
                    if((serverLogic.getPlayers().get(j).getName() != null) && serverLogic.getPlayers().get(j).getName().equals(player.getName())) {
                        serverLogic.replacePlayer(j, player);
                        playerAdded = true;
                        break;
                    }
                }
                if(!playerAdded) {
                    serverLogic.addPlayer(player);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sends Player objects from the ArrayList to the Client
     * @throws IOException
     */
    public void sendPlayers() throws IOException {
        players = serverLogic.getPlayers();
        System.out.println(players.size());
        for(int i=0; i<players.size(); i++) {
            objectOutput1.writeObject(players.get(i));
        }
    }

    /**
     * Reads in PlayerMoveMsgs received from the Client and adds them to an ArrayList
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void receiveMoveMsg() throws IOException, ClassNotFoundException {

        try {
            Object next = objectInput1.readObject();
            while(next instanceof Player) {
                next = objectInput1.readObject();
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
                    } /*else if(moveMsg.getName() != null) {
                        serverLogic.addMsg(moveMsg);
                    }*/
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
        for(int i=0; i<moveMsgs.size(); i++) {
            objectOutput1.writeObject(moveMsgs.get(i));
        }
    }


    public void sendStartMessage() throws IOException {
        boolean started = false;
        while(!started) {
            if(serverLogic.isReadyToStartGame()) {
                objectOutput1.writeObject(new Player("START THE GAME", "READY"));
                started = true;
            }
        }
    }


}
