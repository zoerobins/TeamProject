package org.nightshade.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;


/**
 *this class acts as a controller for the settings.fxml file
 */
public class SettingsController{

    @FXML
    private Slider mSlider;
    public static double mSliderVal = 20.0;

    @FXML
    private Slider bgSlider;
    public static double bgSliderVal;

    /**
     * this method stores the current value of
     * the back ground music volume slider
     * into the variable bgSliderVal
     */
    @FXML
    public void onBGSliderChanged(){
        bgSliderVal = bgSlider.getValue();
    }

    /**
     * this method stores the current value of
     * the sound effects volume slider
     * into the variable mSliderVal
     */
    @FXML
    public void OnmSliderChanged(){
        mSliderVal = mSlider.getValue();
    }
    /**
     *this method changes the current scene of the window
     *from the settings scene to the menu scene
     */
    public void MainMenuButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }
    /**
     *this method saves the current slider values
     */
    public void SaveButton() {
        mSlider.setValue(mSliderVal);
        bgSlider.setValue(bgSliderVal);
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

}
