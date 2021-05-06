package org.nightshade.networking;

import java.io.IOException;

/**
 * Client class
 * Creates a Client which connect to the Server
 */
public class Client {

    private String name;
    private ClientLogic clientLogic;
    private boolean host;

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

}
