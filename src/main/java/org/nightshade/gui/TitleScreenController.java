package org.nightshade.gui;

import javafx.fxml.FXML;
/**
 *this class acts as a controller for the title_screen.fxml file
 */
public class TitleScreenController {
    /**
     *this method changes the current scene of the window
     *from the titleScreen scene to the menu scene
     */
    @FXML
    public void titleButton(){
        GuiHandler.stage.setScene(GuiHandler.menu);
    }
}