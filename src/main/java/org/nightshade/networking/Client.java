package org.nightshade.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public Client() throws IOException {
        clientLogic = new ClientLogic();
    }

    public Client(String name, String serverIp, int portValue) throws IOException {
        this.name = name;
        this.host = false;
        clientLogic = new ClientLogic(serverIp, portValue, this);
    }

    public String getName() {
        return this.name;
    }

    public ClientLogic getClientLogic() {
        return clientLogic;
    }

    public void setHost() {
        this.host = true;
    }

    public boolean isHost() {
        return this.host;
    }

    /*public static void main(String[] args) throws IOException {
        new Client();
    }*/

}

