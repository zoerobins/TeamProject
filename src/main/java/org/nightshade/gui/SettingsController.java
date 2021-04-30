package org.nightshade.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;


public class SettingsController{

    @FXML
    private Slider mSlider;
    public static double mSliderVal = 20.0;

    @FXML
    private Slider bgSlider;
    public static double bgSliderVal;

    @FXML
    public void onBGSliderChanged(){
        bgSliderVal = bgSlider.getValue();
    }

    @FXML
    public void OnmSliderChanged(){
        mSliderVal = mSlider.getValue();
    }

    public void MainMenuButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    public void SaveButton() {
        mSlider.setValue(mSliderVal);
        bgSlider.setValue(bgSliderVal);
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

}

