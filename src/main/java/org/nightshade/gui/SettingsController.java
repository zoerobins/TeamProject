package org.nightshade.gui;
/**
 *this class acts as a controller for the settings.fxml file
 */
public class SettingsController {
    /**
     *this method changes the current scene of the window
     *from the settings scene to the menu scene
     */
    public void MainMenuButton(){
        GuiHandler.stage.setScene(GuiHandler.menu);
    }
}