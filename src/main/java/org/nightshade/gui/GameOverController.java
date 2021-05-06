package org.nightshade.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.nightshade.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *this class acts as a controller for the game_over.fxml file
 */
public class GameOverController implements Initializable {

    @FXML
    public static Label label;

    public static String tag;

    public static void print(){
        System.out.println(tag);

    }
    /**
     *this method changes the current scene of the window
     *from the gameOver scene to the singlePlayer scene
     */
    public void restartButton() {
        GuiHandler.stage.setScene(GuiHandler.singlePlayer);
    }

    /**
     *this method changes the current scene of the window
     *from the gameOver scene to the multiplayer scene
     */
    public void multiPLayerRestartButton() {
        GuiHandler.stage.setScene(GuiHandler.multiPlayer);
    }


    /**
     *this method changes the current scene of the window
     *from the gameOver scene to the menu scene
     */
    public void mainMenuButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
