package org.nightshade.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Client class
 * Creates a Client which connect to the Server
 */
public class Client {

    //private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String name;
    private String serverIp;
    private int portValue;
    private ClientLogic clientLogic;
    private boolean host;

    /*public Client() {
        try {
            System.out.println("Please enter the server name or IP address: ");
            serverIp = br.readLine();
            System.out.println("Please enter the port number for the server: ");
            portValue = Integer.parseInt(br.readLine());
            clientLogic = new ClientLogic(serverIp, portValue, this);
        } catch (IOException e1) {
            System.out.println("Buffered reader does not exist");
        } catch (NumberFormatException e2) {
            System.out.println("port can only be a number");
        }
    }*/

    /**
     * Constructor for the Client class
     * Creates a new ClientLogic object
     * @throws IOException
     */
    public Client() throws IOException {
        clientLogic = new ClientLogic();
    }

    /**
     * Contructor for the Client class
     * @param name Name of the Client
     * @param serverIp IP address of the Server
     * @param portValue Port number for the Server
     * @throws IOException
     */
    public Client(String name, String serverIp, int portValue) throws IOException {
        this.name = name;
        this.host = false;
        clientLogic = new ClientLogic(serverIp, portValue, this);
    }

    /**
     * Returns the name of the Client
     * @return Name of the Client
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the ClientLogic object corresponding to the Client
     * @return Corresponding ClientLogic object
     */
    public ClientLogic getClientLogic() {
        return clientLogic;
    }

    /**
     * Sets the client as the host
     */
    public void setHost() {
        this.host = true;
    }

    /**
     * Returns whether the Client is the host
     * @return Whether the Client is the host
     */
    public boolean isHost() {
        return this.host;
    }

    /*public static void main(String[] args) throws IOException {
        new Client();
    }*/

}

