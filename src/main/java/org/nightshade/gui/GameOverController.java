package org.nightshade.gui;
/**
 *this class acts as a controller for the settings.fxml file
 */
public class GameOverController {
    /**
     *this method changes the current scene of the window
     *from the gameOver scene to the singlePlayer scene
     */
    public void restartButton() {
        GuiHandler.stage.setScene(GuiHandler.singlePlayer);
    }
    /**
     *this method changes the current scene of the window
     *from the gameOver scene to the menu scene
     */
    public void mainMenuButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

}
