package org.nightshade.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Server class
 * Creates a Server which Clients can connect to
 */
public class Server {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int serverPort;
    private static ServerLogic serverLogic;


    /**
     * Constructor for the Server class
     * Reads in the port number and creates a new ServerLogic object
     * @throws NumberFormatException
     * @throws IOException
     */
    public Server() throws NumberFormatException, IOException {
        System.out.println("Please enter the server port number: ");
        serverPort = Integer.parseInt(br.readLine());
        br.close();
        serverLogic = new ServerLogic(serverPort);
    }

    /**
     * Constructor for the Server class
     * Creates a new ServerLogic object
     * @param serverPort Port number for the Server
     * @throws IOException
     */
    public Server(int serverPort) throws IOException {
        serverLogic = new ServerLogic(serverPort);
    }

    /**
     * Creates a new Server object with the port number 2222
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Server(2222);
        //serverLogic.kill();
    }

}
