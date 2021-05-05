package org.nightshade.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Server class
 * Creates a Server which Clients can connect to
 */
public class Server {

    /**
     * Constructor for the Server class
     * Reads in the port number and creates a new ServerLogic object
     * @throws NumberFormatException
     * @throws IOException
     */
    public Server() throws NumberFormatException, IOException {
        System.out.println("Please enter the server port number: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int serverPort = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.close();
        new ServerLogic(serverPort);
    }

    /**
     * Constructor for the Server class
     * Creates a new ServerLogic object
     * @param serverPort Port number for the Server
     * @throws IOException
     */
    public Server(int serverPort) throws IOException {
        new ServerLogic(serverPort);
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
