package org.nightshade.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.nightshade.Main;
import org.nightshade.ai.AI;
import org.nightshade.ai.Difficulty;
import org.nightshade.game.Game;

import java.net.URL;
import java.util.ArrayList;
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

    private ToggleGroup ai1ToggleGroup;
    private ToggleGroup ai2ToggleGroup;
    private ToggleGroup ai3ToggleGroup;

    private ArrayList<RadioButton> easyRadioButtons;
    private ArrayList<RadioButton> mediumRadioButtons;
    private ArrayList<RadioButton> hardRadioButtons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ai1ToggleGroup = new ToggleGroup();
        ai2ToggleGroup = new ToggleGroup();
        ai3ToggleGroup = new ToggleGroup();

        ai1EasyRadio.setToggleGroup(ai1ToggleGroup);
        ai1MediumRadio.setToggleGroup(ai1ToggleGroup);
        ai1HardRadio.setToggleGroup(ai1ToggleGroup);

        ai2EasyRadio.setToggleGroup(ai2ToggleGroup);
        ai2MediumRadio.setToggleGroup(ai2ToggleGroup);
        ai2HardRadio.setToggleGroup(ai2ToggleGroup);

        ai3EasyRadio.setToggleGroup(ai3ToggleGroup);
        ai3MediumRadio.setToggleGroup(ai3ToggleGroup);
        ai3HardRadio.setToggleGroup(ai3ToggleGroup);

        easyRadioButtons = new ArrayList<>();
        easyRadioButtons.add(ai1EasyRadio);
        easyRadioButtons.add(ai2EasyRadio);
        easyRadioButtons.add(ai3EasyRadio);

        mediumRadioButtons = new ArrayList<>();
        mediumRadioButtons.add(ai1MediumRadio);
        mediumRadioButtons.add(ai2MediumRadio);
        mediumRadioButtons.add(ai3MediumRadio);

        hardRadioButtons = new ArrayList<>();
        hardRadioButtons.add(ai1HardRadio);
        hardRadioButtons.add(ai2HardRadio);
        hardRadioButtons.add(ai3HardRadio);
    }

    @FXML
    public void backToMain(ActionEvent event) {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    @FXML
    public void startButtonHandler(ActionEvent event) {
        Game game = new Game(Main.stage);

        addAiPlayers(game, ai1Check, ai1ToggleGroup);

        addAiPlayers(game, ai2Check, ai2ToggleGroup);

        addAiPlayers(game, ai3Check, ai3ToggleGroup);
    }

    // adds AI players to the game based on radio buttons selected when start button is pressed
    private void addAiPlayers(Game game, CheckBox checkBox, ToggleGroup toggleGroup) {
        if (checkBox.isSelected()) {
            if (easyRadioButtons.contains(toggleGroup.getSelectedToggle())) {
                System.out.println(toggleGroup.getSelectedToggle().toString());
                game.addAiPlayer(new AI(Difficulty.EASY));
            } else if (mediumRadioButtons.contains(toggleGroup.getSelectedToggle())) {
                game.addAiPlayer(new AI(Difficulty.MEDIUM));
            } else if (hardRadioButtons.contains(toggleGroup.getSelectedToggle())) {
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