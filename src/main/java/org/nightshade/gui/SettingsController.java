package org.nightshade.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;


public class SettingsController{

    @FXML
    private Slider bSlider;
    public static float bSliderVal;

    @FXML
    private Slider mSlider;
    public static double mSliderVal = 20.0;

    @FXML
    public void OnSliderChanged(){
        bSliderVal = (int) bSlider.getValue();
        System.out.println(bSliderVal/100);
    }

    @FXML
    public void OnmSliderChanged(){
        mSliderVal = mSlider.getValue();
        System.out.println(mSliderVal);
    }

    public void MainMenuButton() {
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

    public void SaveButton() {
        bSlider.setValue(bSliderVal);
        mSlider.setValue(mSliderVal);
        GuiHandler.stage.setScene(GuiHandler.menu);
    }

}

