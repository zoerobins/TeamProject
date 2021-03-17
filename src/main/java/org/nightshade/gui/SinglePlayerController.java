package org.nightshade.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import org.nightshade.Main;
import org.nightshade.game.Game;

import java.net.URL;
import java.util.ResourceBundle;


public class SinglePlayerController {

    @FXML private CheckBox ai1Check;

    @FXML private CheckBox ai2Check;

    @FXML private CheckBox ai3Check;

    @FXML private Label ai1OnOff;

    @FXML private Label ai2OnOff;

    @FXML private Label ai3OnOff;

    @FXML private ChoiceBox<String> ai1Drop;

    @FXML private ChoiceBox<String> ai2Drop;

    @FXML private ChoiceBox<String> ai3Drop;

    @FXML private Button start;

    @FXML private Button back;

    private int count;


    @FXML
    void backToMain(ActionEvent event) {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    @FXML
    void startGame(ActionEvent event) {
        Game game = new Game();
        game.initGame(Main.stage, count);
    }






    @FXML
    void checkBoxHandler (ActionEvent event){
        count = 0;
        if (ai1Check.isSelected()){
            count ++;
            ai1OnOff.setText("ON");
        }else{
            ai1OnOff.setText("OFF");
        }
        if (ai2Check.isSelected()){
            count ++;
            ai2OnOff.setText("ON");
        }else{
            ai2OnOff.setText("OFF");
        }
        if (ai3Check.isSelected()){
            count ++;
            ai3OnOff.setText("ON");
        }else{
            ai3OnOff.setText("OFF");
        }
    }

}
