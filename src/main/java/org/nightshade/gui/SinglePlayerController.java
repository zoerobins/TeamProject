package org.nightshade.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.nightshade.Main;
import org.nightshade.ai.AI;
import org.nightshade.ai.Difficulty;
import org.nightshade.game.Game;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 *this class acts as a controller for the single_player.fxml file
 */
public class SinglePlayerController implements Initializable {

    @FXML private CheckBox ai1CheckBox;
    @FXML private CheckBox ai2CheckBox;
    @FXML private CheckBox ai3CheckBox;

    @FXML public RadioButton ai1EasyRadioButton;
    @FXML private RadioButton ai1MediumRadioButton;
    @FXML private RadioButton ai1HardRadioButton;

    @FXML private RadioButton ai2EasyRadioButton;
    @FXML private RadioButton ai2MediumRadioButton;
    @FXML private RadioButton ai2HardRadioButton;

    @FXML private RadioButton ai3EasyRadioButton;
    @FXML private RadioButton ai3MediumRadioButton;
    @FXML private RadioButton ai3HardRadioButton;

    @FXML private ToggleGroup ai1ToggleGroup;
    @FXML private ToggleGroup ai2ToggleGroup;
    @FXML private ToggleGroup ai3ToggleGroup;

    private ArrayList<RadioButton> easyRadioButtons;
    private ArrayList<RadioButton> mediumRadioButtons;
    private ArrayList<RadioButton> hardRadioButtons;

    private ArrayList<RadioButton> ai1RadioButtons;
    private ArrayList<RadioButton> ai2RadioButtons;
    private ArrayList<RadioButton> ai3RadioButtons;

    public static Game game;

    /**
     * called as this scene is displayed on the window,
     * sets all the toggle groups, puts all the easy/medium/hard
     * buttons into one array for each AI and another
     * for each difficulty. Also sets all buttons to
     * be disabled by default
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ai1ToggleGroup = new ToggleGroup();
        ai2ToggleGroup = new ToggleGroup();
        ai3ToggleGroup = new ToggleGroup();

        setToggleGroup(ai1EasyRadioButton,ai1MediumRadioButton,ai1HardRadioButton,ai1ToggleGroup);
        setToggleGroup(ai2EasyRadioButton,ai2MediumRadioButton,ai2HardRadioButton,ai2ToggleGroup);
        setToggleGroup(ai3EasyRadioButton,ai3MediumRadioButton,ai3HardRadioButton,ai3ToggleGroup);

        easyRadioButtons = radioButtonArrayMaker(ai1EasyRadioButton,ai2EasyRadioButton,ai3EasyRadioButton);
        mediumRadioButtons = radioButtonArrayMaker(ai1MediumRadioButton,ai2MediumRadioButton,ai3MediumRadioButton);
        hardRadioButtons = radioButtonArrayMaker(ai1HardRadioButton,ai2HardRadioButton,ai3HardRadioButton);

        ai1RadioButtons = radioButtonArrayMaker(ai1EasyRadioButton,ai1MediumRadioButton,ai1HardRadioButton);
        ai2RadioButtons = radioButtonArrayMaker(ai2EasyRadioButton,ai2MediumRadioButton,ai2HardRadioButton);
        ai3RadioButtons = radioButtonArrayMaker(ai3EasyRadioButton,ai3MediumRadioButton,ai3HardRadioButton);


        ArrayList<RadioButton> radioButtons = new ArrayList<>();
        radioButtons.addAll(ai1RadioButtons);
        radioButtons.addAll(ai2RadioButtons);
        radioButtons.addAll(ai3RadioButtons);

        for (RadioButton radioButton : radioButtons) {
            radioButton.setDisable(true);
        }
    }

    /**takes the first 3 parameters and adds them
     * into the toggle group that is the 4th parameter
     *
     * @param b1
     * @param b2
     * @param b3
     * @param tg
     */
    @FXML
    private void setToggleGroup(RadioButton b1, RadioButton b2, RadioButton b3, ToggleGroup tg){
        b1.setToggleGroup(tg);
        b2.setToggleGroup(tg);
        b3.setToggleGroup(tg);
    }

    /**makes a new array, adds all 3 parameters
     * into the array returns the array
     *
     * @param b1
     * @param b2
     * @param b3
     * @return
     */
    @FXML
    private ArrayList<RadioButton> radioButtonArrayMaker(RadioButton b1, RadioButton b2, RadioButton b3){
        ArrayList<RadioButton> radioButtons = new ArrayList<>();
        radioButtons.add(b1);
        radioButtons.add(b2);
        radioButtons.add(b3);

        return radioButtons;
    }

    /**
     *this method changes the current scene of the window
     *from the singlePlayer scene to the menu scene
     * @param event
     */
    @FXML
    public void backToMain(ActionEvent event) {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    /**
     *this method changes the current scene of the window
     *from the singlePlayer scene into to the game
     * @param event
     */
    @FXML
    public void startGame(ActionEvent event) {
        game = new Game(Main.stage);
    }

    /**starts the game and changes the current scene of
     *the window from the singlePlayer scene to the game
     *and feeds all values of the ai checkboxes to addAiPlayers
     * @param event
     */
    @FXML
    public void startButtonHandler(ActionEvent event) {
        Game game = new Game(Main.stage);
        addAiPlayers(game,ai1CheckBox,ai1ToggleGroup);
        addAiPlayers(game,ai2CheckBox,ai2ToggleGroup);
        addAiPlayers(game,ai3CheckBox,ai3ToggleGroup);
    }

    /**checks the various Ai toggles and adds the correct
     *number of ai players into the game accordingly
     *
     * @param game
     * @param checkBox
     * @param toggleGroup
     */
    private void addAiPlayers(Game game, CheckBox checkBox, ToggleGroup toggleGroup) {
        if (checkBox.isSelected()) {
            if (easyRadioButtons.contains(toggleGroup.getSelectedToggle())) {
                game.addAiPlayer(new AI(Difficulty.EASY));
            } else if (mediumRadioButtons.contains(toggleGroup.getSelectedToggle())) {
                game.addAiPlayer(new AI(Difficulty.MEDIUM));
            } else if (hardRadioButtons.contains(toggleGroup.getSelectedToggle())) {
                game.addAiPlayer(new AI(Difficulty.HARD));
            }
        }
    }

    /**handles all events for the checkboxes attached to all the Ais
     *calls checkBoxHandlerHelper to help with the all of the logic
     *that the buttons obey
     *
     * @param event
     */
    @FXML
    public void checkBoxHandler(ActionEvent event) {
        checkBoxHandlerHelper(ai1CheckBox,ai1EasyRadioButton,ai1ToggleGroup,ai1RadioButtons);
        checkBoxHandlerHelper(ai2CheckBox,ai2EasyRadioButton,ai2ToggleGroup,ai2RadioButtons);
        checkBoxHandlerHelper(ai3CheckBox,ai3EasyRadioButton,ai3ToggleGroup,ai3RadioButtons);
    }

    /**handles all logic relating to the 12 checkboxes on the Ais
     *e.g. makes sure only one of the boxes in the groups of 3 is
     *ever selected, disengages the relevant difficulty boxes
     * if the main box for that ai gets unchecked and so on
     *
     * @param checkBox
     * @param easyRadioButton
     * @param toggleGroup
     * @param radioButtons
     */
    private void checkBoxHandlerHelper(CheckBox checkBox, RadioButton easyRadioButton, ToggleGroup toggleGroup, ArrayList<RadioButton> radioButtons) {
        String onText = "ON";
        String offText = "OFF";

        if (checkBox.isSelected()) {
            checkBox.setText(onText);
            if (toggleGroup.getSelectedToggle() == null) {
                easyRadioButton.setSelected(true);
            }
            for (RadioButton radioButton : radioButtons) {
                radioButton.setDisable(false);
            }
        } else {
            checkBox.setText(offText);
            for (RadioButton radioButton : radioButtons) {
                radioButton.setDisable(true);
            }
        }
    }
}