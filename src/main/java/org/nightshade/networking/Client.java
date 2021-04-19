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

    public Client(String name, String serverIp, int portValue) throws IOException {
        this.name = name;
        clientLogic = new ClientLogic(serverIp, portValue, this);
    }

    public String getName() {
        return this.name;
    }

    public ClientLogic getClientLogic() {
        return clientLogic;
    }

    /*public static void main(String[] args) throws IOException {
        new Client();
    }*/

}

