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

public class MultiPlayerLobbyController implements Initializable {

    @FXML private Button readyButton;

    public void backButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
