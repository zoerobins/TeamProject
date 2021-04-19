package org.nightshade.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Server {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int serverPort;
    private static ServerLogic serverLogic;

    public Server() throws NumberFormatException, IOException {
        System.out.println("Please enter the server port number: ");
        serverPort = Integer.parseInt(br.readLine());
        br.close();
        serverLogic = new ServerLogic(serverPort);
    }

    public static void main(String[] args) throws IOException {
        new Server();
        //serverLogic.kill();
    }

}
