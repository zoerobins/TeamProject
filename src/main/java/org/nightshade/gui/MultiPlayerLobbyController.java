package org.nightshade.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.nightshade.Main;
import org.nightshade.multiplayer.Game;
import org.nightshade.multiplayer.GameHandler;
import org.nightshade.networking.Client;
import org.nightshade.networking.ClientLogic;
import org.nightshade.networking.StartGameMsg;

public class MultiPlayerLobbyController implements Initializable {

    @FXML private TableView<Player> tableView;
    @FXML private TableColumn<Player,String>nameColumn;
    @FXML private TableColumn<Player,String>readyColumn;
    @FXML private Button readyButton;
    ClientLogic clientLogic;
    ArrayList<Player> playersList;

    public void backButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    public void readyButton() {

        if (GuiHandler.player.getReady().equals("NOT READY")) {
            GuiHandler.player.setReady("READY");
            readyButton.setText("Not Ready");
        } else{
            GuiHandler.player.setReady("NOT READY");
            readyButton.setText("Ready");
        }

        tableView.getItems().clear();
        tableView.getItems().addAll(getPlayers());
        tableView.refresh();

        try {
            GuiHandler.player.getClient().getClientLogic().sendPlayer(GuiHandler.player.getName(), "READY");
            StartGameMsg start;
            start = GuiHandler.player.getClient().getClientLogic().receiveStartMsg();
            GameHandler gameHandler = new GameHandler(Main.stage, GuiHandler.player.getName(), GuiHandler.player.getClient(), start.getPlayers());


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void makeTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("Name"));
        readyColumn.setCellValueFactory(new PropertyValueFactory<Player,String>("Ready"));
        tableView.setItems(getPlayers());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeTable();
    }

    public ObservableList<Player> getPlayers(){
        ObservableList<Player> players = FXCollections.observableArrayList();
        /*clientLogic = GuiHandler.player.getClient().getClientLogic();
        playersList = clientLogic.getPlayersList();
        for(Player player : playersList) {
            players.add(player);
        }*/
        if(players.size() == 0) {
            players.add(GuiHandler.player);
        }

        return players;
    }
}
