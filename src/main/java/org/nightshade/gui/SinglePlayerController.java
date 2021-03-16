package org.nightshade.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import org.nightshade.Main;
import org.nightshade.game.Game;


public class SinglePlayerController {

    @FXML
    private CheckBox ai1Check;

    @FXML
    private ChoiceBox<?> ai1Drop;

    @FXML
    private CheckBox ai2Check;

    @FXML
    private ChoiceBox<?> ai1Drop1;

    @FXML
    private CheckBox ai3Check;

    @FXML
    private ChoiceBox<?> ai1Drop11;

    @FXML
    private Button start;

    @FXML
    private Button back;

    @FXML
    void backToMain(ActionEvent event) {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    @FXML
    void startGame(ActionEvent event) {
        Game game = new Game();
        game.initGame(Main.stage);
    }

}
