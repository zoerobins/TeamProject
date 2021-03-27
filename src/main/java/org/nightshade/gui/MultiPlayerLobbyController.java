package org.nightshade.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MultiPlayerLobbyController implements Initializable {

    @FXML
    private ArrayList<String> Ips;

    @FXML
    private ArrayList<String> Names;

    @FXML
    private ArrayList<String> Ready;

    public void backButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
