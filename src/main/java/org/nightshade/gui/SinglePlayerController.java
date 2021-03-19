package org.nightshade.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.nightshade.Main;
import org.nightshade.ai.AI;
import org.nightshade.ai.Difficulty;
import org.nightshade.game.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class SinglePlayerController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup ai1ToggleGroup = new ToggleGroup();
        ToggleGroup ai2ToggleGroup = new ToggleGroup();
        ToggleGroup ai3ToggleGroup = new ToggleGroup();

        ai1EasyRadio = new RadioButton();
        ai1MediumRadio = new RadioButton();
        ai1HardRadio = new RadioButton();

        ai1EasyRadio.setToggleGroup(ai1ToggleGroup);
        ai1MediumRadio.setToggleGroup(ai1ToggleGroup);
        ai1HardRadio.setToggleGroup(ai1ToggleGroup);

        ai2EasyRadio = new RadioButton();
        ai2MediumRadio = new RadioButton();
        ai2HardRadio = new RadioButton();

        ai2EasyRadio.setToggleGroup(ai2ToggleGroup);
        ai2MediumRadio.setToggleGroup(ai2ToggleGroup);
        ai2HardRadio.setToggleGroup(ai2ToggleGroup);

        ai3EasyRadio = new RadioButton();
        ai3MediumRadio = new RadioButton();
        ai3HardRadio = new RadioButton();

        ai3EasyRadio.setToggleGroup(ai3ToggleGroup);
        ai3MediumRadio.setToggleGroup(ai3ToggleGroup);
        ai3HardRadio.setToggleGroup(ai3ToggleGroup);
    }

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

        for (AI ai : game.getAiPlayers()) {
            System.out.println(ai.getSpeed());
        }
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