package org.nightshade.gui;

import org.nightshade.networking.Client;
import java.io.Serializable;

public class Player implements Serializable {

    private String ip;
    public String name;
    public String ready;
    public Client client;

    public Player(String name){
        this.name = name;
        this.ready = "NOT READY";
    }

    public Player(String name, String ready) {
        this.name = name;
        this.ready = ready;
    }

    public Player(String name, String ready, Client client) {
        this.name = name;
        this.ready = ready;
        this.client = client;
    }

    public String getIp(){
        return this.ip;
    }
    public String getName(){
        return this.name;
    }
    public String getReady(){
        return this.ready;
    }
    public Client getClient() {
        return this.client;
    }

    public void setIp(String newIp){
        this.ip = newIp;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public void setReady(String newReady){
        this.ready = newReady;
    }
    public void setClient(Client newClient) {
        this.client = newClient;
    }
}
