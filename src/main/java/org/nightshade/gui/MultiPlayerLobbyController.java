package org.nightshade.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MultiPlayerLobbyController implements Initializable {

    @FXML private TableView<Player> tableView;
    @FXML private TableColumn<Player,String>nameColumn;
    @FXML private TableColumn<Player,String>readyColumn;

    public void backButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    public void readyButton() {
        if (GuiHandler.player.getReady() == "NOT READY") {
            GuiHandler.player.setReady("READY");
            tableView.refresh();
        }else{
            GuiHandler.player.setReady("NOT READY");
            tableView.refresh();
        }
        // update table
        // start game
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
        players.add(GuiHandler.player);
        System.out.println(GuiHandler.player.getReady());
        players.add(new Player("player 2"));
        players.add(new Player("FifaPlayer52"));
        players.add(new Player("brian1997"));

        return players;
    }
}
