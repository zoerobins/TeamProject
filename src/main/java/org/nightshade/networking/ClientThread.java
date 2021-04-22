package org.nightshade.networking;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import org.nightshade.gui.Player;

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


    @Override
    public void run() {

        try {
            while(!allPlayersReady) {
                receivePlayers();
                sendPlayers();
            }
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

    public void receivePlayers() {
        Player player;
        try {
            player = (Player) objectInput1.readObject();

            if(player.getName().equals("ALLPLAYERSREADY") && player.getReady().equals("ALLPLAYERSREADY")) {
                allPlayersReady = true;
                System.out.println("all players ready");
                return;
            }

            if(serverLogic.getPlayers().size() == 0) {
                serverLogic.addPlayer(player);
            } else {
                for(int j=0; j<serverLogic.getPlayers().size(); j++) {
                    if((serverLogic.getPlayers().get(j).getName() != null) && serverLogic.getPlayers().get(j).getName().equals(player.getName())) {
                        serverLogic.replacePlayer(j, player);
                    } else if(player.getName() != null) {
                        serverLogic.addPlayer(player);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void sendPlayers() throws IOException {
        players = serverLogic.getPlayers();
        System.out.println(players.size());
        for(int i=0; i<players.size(); i++) {
            objectOutput1.writeObject(players.get(i));
        }
    }

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
                for(int j=0; j<serverLogic.getMoveMsgs().size(); j++) {
                    if((serverLogic.getMoveMsgs().get(j).getName() != null) && serverLogic.getMoveMsgs().get(j).getName().equals(moveMsg.getName())) {
                        serverLogic.replaceMsg(j, moveMsg);
                    } else if(moveMsg.getName() != null) {
                        serverLogic.addMsg(moveMsg);
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void sendMoveMsgs() throws IOException {
        moveMsgs = serverLogic.getMoveMsgs();
        for(int i=0; i<moveMsgs.size(); i++) {
            objectOutput1.writeObject(moveMsgs.get(i));
        }
    }



}
