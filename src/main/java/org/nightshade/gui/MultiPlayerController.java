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


public class MultiPlayerController implements Initializable {

    @FXML
    private TextField serverIpBox;
    @FXML
    private TextField portNumBox;
    @FXML
    private TextField nameBox;

    public void playButton() throws IOException {

        String name = nameBox.getText();
        String serverIp = serverIpBox.getText();
        int portValue = Integer.parseInt(portNumBox.getText());

        GuiHandler.player.setName(name);

        Client client = new Client(name, serverIp, portValue);
        GuiHandler.player.setClient(client);

        changeScene();

    }
    public void changeScene(){
        GuiHandler.stage.setScene(GuiHandler.multiPlayerLobby);
    }
    public void backButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameBox.setText(GuiHandler.player.getName());
    }
}

