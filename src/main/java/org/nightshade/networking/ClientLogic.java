package org.nightshade.networking;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientLogic implements Runnable {

    private PrintWriter output;
    private BufferedReader input;
    private ObjectOutputStream objectOutput;
    private ObjectInputStream objectInput;
    private Client client;
    private Thread thread;
    private Socket server;
    private ClientServerController csc;

    public ClientLogic(String serverIp, int portValue, Client client) throws IOException {
        server = new Socket(serverIp, portValue);
        output = new PrintWriter(server.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(server.getInputStream()));
        objectOutput = new ObjectOutputStream(server.getOutputStream());
        objectInput = new ObjectInputStream(server.getInputStream());
        csc = new ClientServerController();
        this.client = client;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            waitForServer();
        } catch (SocketException e1) {
            csc.clientToClientMessage(client, "Connection lost to server");
        } catch (IOException e) {
            csc.clientToClientMessage(client, "Connection lost to server");
        } catch (RuntimeException e2) {
            csc.clientToClientMessage(client, "Connection lost to server");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void waitForServer() throws IOException, SocketException, RuntimeException, ClassNotFoundException {
        while(true) {
            /*String serverReply;
            while((serverReply = input.readLine()) != null) {
                System.out.println(serverReply);
                //csc.clientToClientMessage(client, serverReply);
            }*/
            PlayerMoveMsg moveMsg;
            moveMsg = (PlayerMoveMsg)objectInput.readObject();

        }
    }

    public void sendToServer(String name, int x, int y, boolean alive) throws IOException {
        //csc.clientToServerMessage(output, message);
        //output.println(message);
        //output.println(new PlayerMoveMsg(name, x, y, alive));
        objectOutput.writeObject(new PlayerMoveMsg(name, x, y, alive));
    }


    /*public void moveL() {
        sendToServer("move left");
    }

    public void moveR() {
        sendToServer("move right");
    }

    public void jump() {
        sendToServer("jump");
    }*/

}
