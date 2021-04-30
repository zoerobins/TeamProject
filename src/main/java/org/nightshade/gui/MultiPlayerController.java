package org.nightshade.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.nightshade.Main;
import org.nightshade.multiplayer.Game;
import org.nightshade.networking.Client;
import org.nightshade.networking.ClientLogic;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 *this class acts as a controller for the menu.fxml file
 */
public class MultiPlayerController implements Initializable {

    @FXML
    private TextField serverIpBox;
    @FXML
    private TextField portNumBox;
    @FXML
    private TextField nameBox;

    /**connects to a server with the IP and port
     * corresponding to the ones entered into the
     * text fields, sets player name to the name
     * text field and calls changeScene()
     *
     * @throws IOException
     */
    public void playButton() throws IOException {

        String serverIp = serverIpBox.getText();
        int portValue = Integer.parseInt(portNumBox.getText());

        GuiHandler.player.setName(nameBox.getText());

        Client client = new Client(serverIp, portValue);

        changeScene();

    }
    /**
     *this method changes the current scene of the window
     *from the multiPlayer scene to the multiPlayerLobby scene
     */
    public void changeScene(){
        GuiHandler.stage.setScene(GuiHandler.multiPlayerLobby);
    }
    /**
     *this method changes the current scene of the window
     *from the multiPlayer scene to the menu scene
     */
    public void backButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    /**
     * called as this scene is displayed on the window
     * adds the current player name into the Enter name box
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameBox.setText(GuiHandler.player.getName());
    }
}

