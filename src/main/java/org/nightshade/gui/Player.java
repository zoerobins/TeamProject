package org.nightshade.gui;

/**this class is used to store data about the player
 * e.g. name, IP address and, their ready status
 *
 */
public class Player {
    private String ip;
    public String name;
    public String ready;

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

    /** Returns the players ip
     * @return
     */
    public String getIp(){
        return this.ip;
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
    /** Sets the players ip to the value of the parameter
     *  @param newIp
     */
    public void setIp(String newIp){
        this.ip = newIp;
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
}
