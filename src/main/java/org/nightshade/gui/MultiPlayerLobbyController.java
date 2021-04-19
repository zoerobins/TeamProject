package org.nightshade.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.nightshade.Main;
import org.nightshade.multiplayer.Game;
import org.nightshade.multiplayer.GameHandler;
import org.nightshade.networking.Client;
import org.nightshade.networking.ClientLogic;

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
        if (GuiHandler.player.getReady() == "NOT READY") {
            GuiHandler.player.setReady("READY");
            tableView.setItems(getPlayers());
            tableView.refresh();
            readyButton.setText("Not Ready");
        } else{
            GuiHandler.player.setReady("NOT READY");
            tableView.setItems(getPlayers());
            tableView.refresh();
            readyButton.setText("Ready");
        }

        /*if(ServerLogic.getClientThreads() != null) {
            int numClients = ServerLogic.getClientThreads().size();
            System.out.println("number of clients: " + numClients);
        } else {
            System.out.println("no client threads");
        }*/

        boolean allReady = false;
        for (Player ready : tableView.getItems()) {
            if(readyColumn.getCellObservableValue(ready).getValue() == "NOT READY") {
                allReady = false;
                break;
            } else {
                allReady = true;
            }
        }
        if(allReady) {
            // start game
            System.out.println("all players ready");
            GameHandler gameHandler = new GameHandler(Main.stage, GuiHandler.player.getName());
            //game.addClient(client);
            clientLogic.gameLoop();
            
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
        players.add(GuiHandler.player);
        //System.out.println(GuiHandler.player.getReady());
        /*players.add(new Player("player 2", "READY"));
        players.add(new Player("FifaPlayer52", "READY"));
        players.add(new Player("brian1997", "READY"));*/
        clientLogic = GuiHandler.player.getClient().getClientLogic();
        playersList = clientLogic.getPlayersList();
        for(Player player : playersList) {
            players.add(player);
        }

        return players;
    }
}
