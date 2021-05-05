package org.nightshade.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.nightshade.Main;
import org.nightshade.multiplayer.GameHandler;
import org.nightshade.networking.StartGameMsg;
/**
 *This class acts as a controller for the multi_player_lobby.fxml file
 */
public class MultiPlayerLobbyController implements Initializable {

    @FXML private Button readyButton;

    /**
     *This method changes the current scene of the window
     *from the multiPlayerLobby scene to the menu scene.
     */
    public void backButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    /**
     * This method sets the players ready status to "READY"
     * and then sends out a message saying that this player
     * is ready. It then waits to receive messages from all
     * other players saying that they are also ready before
     * finally starting the game.
     */
    public void readyButton() {
        GuiHandler.player.setReady("READY");
        try {
            GuiHandler.player.getClient().getClientLogic().sendPlayer(GuiHandler.player.getName(), "READY");
            StartGameMsg start;
            start = GuiHandler.player.getClient().getClientLogic().receiveStartMsg();
            GameHandler gameHandler = new GameHandler(Main.stage, GuiHandler.player.getName(), GuiHandler.player.getClient(), start.getPlayers());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**This method is called as this scene is displayed on the window
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
