package org.nightshade.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import org.nightshade.Main;
import org.nightshade.ai.AI;
import org.nightshade.ai.Difficulty;
import org.nightshade.game.Game;

public class SinglePlayerController {

    @FXML private CheckBox ai1Check;
    @FXML private CheckBox ai2Check;
    @FXML private CheckBox ai3Check;

    @FXML public RadioButton ai1EasyRadio;
    @FXML private RadioButton ai1MediumRadio;
    @FXML private RadioButton ai1HardRadio;

    @FXML private RadioButton ai2EasyRadio;
    @FXML private RadioButton ai2MediumRadio;
    @FXML private RadioButton ai2HardRadio;

    @FXML private RadioButton ai3EasyRadio;
    @FXML private RadioButton ai3MediumRadio;
    @FXML private RadioButton ai3HardRadio;

    @FXML private Button start;
    @FXML private Button back;

    @FXML
    public void backToMain(ActionEvent event) {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    @FXML
    public void startButtonHandler(ActionEvent event) {
        Game game = new Game(Main.stage);

        addAiPlayers(game, ai1Check, ai1EasyRadio, ai1MediumRadio, ai1HardRadio);

        addAiPlayers(game, ai2Check, ai2EasyRadio, ai2MediumRadio, ai2HardRadio);

        addAiPlayers(game, ai3Check, ai3EasyRadio, ai3MediumRadio, ai3HardRadio);
    }


    // adds AI players to the game based on radio buttons selected when start button is pressed
    private void addAiPlayers(Game game, CheckBox checkBox, RadioButton easyRadioButton, RadioButton mediumRadioButton, RadioButton hardRadioButton) {
        if (checkBox.isSelected()) {
            if (easyRadioButton.isSelected()) {
                game.addAiPlayer(new AI(Difficulty.EASY));
            } else if (mediumRadioButton.isSelected()) {
                game.addAiPlayer(new AI(Difficulty.MEDIUM));
            } else if (hardRadioButton.isSelected()) {
                game.addAiPlayer(new AI(Difficulty.HARD));
            }
        }
    }

    @FXML
    public void checkBoxHandler(ActionEvent event) {
        String onText = "ON";
        String offText = "OFF";

        if (ai1Check.isSelected()) {
            ai1Check.setText(onText);
            ai1EasyRadio.setSelected(true);
        } else {
            ai1Check.setText(offText);
        }

        if (ai2Check.isSelected()) {
            ai2Check.setText(onText);
            ai2EasyRadio.setSelected(true);
        } else {
            ai2Check.setText(offText);
        }

        if (ai3Check.isSelected()) {
            ai3Check.setText(onText);
            ai3EasyRadio.setSelected(true);
        } else {
            ai3Check.setText(offText);
        }
    }
}
