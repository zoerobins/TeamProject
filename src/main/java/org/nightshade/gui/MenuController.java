package sample;


public class MenuController {

    public void singlePLayerButton (){
        Main.stage.setScene(Main.singlePlayer);
    }

    public void settingsButton (){
        Main.stage.setScene(Main.settings);
    }

    public void exitButton (){
        Main.stage.setScene(Main.titleScreen);
    }

    public void multiPlayerButton (){Main.stage.setScene(Main.multiPlayer);}
}
