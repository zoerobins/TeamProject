package org.nightshade.gui;

import org.nightshade.networking.Client;
import java.io.Serializable;

/**this class is used to store data about the player
 * e.g. name, client and, their ready status
 *
 */
public class Player implements Serializable {

    public String name;
    public String ready;
    public Client client;
    /** Basic constructor that sets name to the parameter given
     *  and sets the ready string to "NOT READY"
     *
     * @param name
     */
    public Player(String name){
        this.name = name;
        this.ready = "NOT READY";
    }
    /** Another basic constructor that instead take two parameters
     * sets name to the first one and ready to the second one
     *
     * @param name
     * @param ready
     */
    public Player(String name, String ready) {
        this.name = name;
        this.ready = ready;
    }
    /** Returns the players name
     *  @return
     */
    public String getName(){
        return this.name;
    }
    /** Returns the players ready status
     *  @return
     */
    public String getReady(){
        return this.ready;
    }

    /** Returns the players Client object
     * @return
     */
    public Client getClient() {
        return this.client;
    }

    /** Sets the players name to the value of the parameter
     * @param newName
     */
    public void setName(String newName){
        this.name = newName;
    }
    /** Sets the players ready status to the value of the parameter
     *  @param newReady
     */
    public void setReady(String newReady){
        this.ready = newReady;
    }

    /**Sets the players client to the same as the client given by the parameter
     * @param newClient
     */
    public void setClient(Client newClient) {
        this.client = newClient;
    }
}
