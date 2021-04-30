package org.nightshade.gui;


/**
 *this class acts as a controller for the menu.fxml file
 */
public class MenuController {

    /**
     *this method changes the current scene of the window
     *from the menu scene to the singlePlayer scene
     */
    public void singlePLayerButton (){
        GuiHandler.stage.setScene(GuiHandler.singlePlayer);
    }

    /**
     *this method changes the current scene of the window
     *from the menu scene to the settings scene
     */
    public void settingsButton (){
        GuiHandler.stage.setScene(GuiHandler.settings);
    }

    /**
     *this method changes the current scene of the window
     *from the menu scene to the titleScreen scene
     */
    public void exitButton (){
        GuiHandler.stage.setScene(GuiHandler.titleScreen);
    }

    /**
     *this method changes the current scene from
     *the menu scene to the multiPlayer scene
     */
    public void multiPlayerButton (){GuiHandler.stage.setScene(GuiHandler.multiPlayer);}
}
