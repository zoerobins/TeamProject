package org.nightshade.gui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * this class is used to create a Gui that
 * will display on the users window and
 * allow them to navigate the game
 */
public class GuiHandler {

    public static Stage stage;
    public static Scene settings;
    public static Scene singlePlayer;
    public static Scene titleScreen;
    public static Scene multiPlayer;
    public static Scene menu;
    public static Scene gameOverScreen;
    public static Scene multiPlayerLobby;
    public static Player player = new Player("Enter Name");
    public static Scene gameOverScreenW;
    public static Scene gameOverScreen1;
    public static Scene gameOverScreen2;
    public static Scene gameOverScreen3;
    public static Scene gameOverScreen4;

    public static Scene multiGameOverScreen;
    public static Scene multiGameOverScreenW;
    public static Scene multiGameOverScreen1;
    public static Scene multiGameOverScreen2;
    public static Scene multiGameOverScreen3;
    public static Scene multiGameOverScreen4;

    /**
     * this method loads up all the FXML files,
     * creates all the scenes for the different
     * "pages" of the menu, adds the CSS file
     * to all of the scenes and returns the
     * scene of the title page
     *
     * @param window
     * @return
     * @throws IOException
     */

    public Scene loadGui(Stage window) throws IOException {

        stage = window;
        Parent settingsRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/settings.fxml")));
        Parent singlePlayerRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/single_player.fxml")));
        Parent titleScreenRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/title_screen.fxml")));
        Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/menu.fxml")));
        Parent multiPlayerRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/multi_player.fxml")));
        Parent multiPlayerLobbyRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/multi_player_lobby.fxml")));

        Parent gameOverRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/single_player/game_over.fxml")));
        Parent gameOverRootW = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/single_player/game_over_w.fxml")));
        Parent gameOverRoot1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/single_player/game_over_1st.fxml")));
        Parent gameOverRoot2 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/single_player/game_over_2nd.fxml")));
        Parent gameOverRoot3 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/single_player/game_over_3rd.fxml")));
        Parent gameOverRoot4 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/single_player/game_over_4th.fxml")));

        Parent multiGameOverRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/multi_player/game_over.fxml")));
        Parent multiGameOverRootW = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/multi_player/game_over_w.fxml")));
        Parent multiGameOverRoot1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/multi_player/game_over_1st.fxml")));
        Parent multiGameOverRoot2 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/multi_player/game_over_2nd.fxml")));
        Parent multiGameOverRoot3 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/multi_player/game_over_3rd.fxml")));
        Parent multiGameOverRoot4 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over_screens/multi_player/game_over_4th.fxml")));


        menu = new Scene(menuRoot, 1280, 720);
        singlePlayer = new Scene(singlePlayerRoot, 1280, 720);
        titleScreen = new Scene(titleScreenRoot, 1280, 720);
        settings = new Scene(settingsRoot, 1280, 720);
        multiPlayer = new Scene(multiPlayerRoot, 1280, 720);
        multiPlayerLobby = new Scene(multiPlayerLobbyRoot, 1280, 720);

        gameOverScreen = new Scene(gameOverRoot, 1280, 720);
        gameOverScreenW = new Scene(gameOverRootW, 1280, 720);
        gameOverScreen1 = new Scene(gameOverRoot1, 1280, 720);
        gameOverScreen2 = new Scene(gameOverRoot2, 1280, 720);
        gameOverScreen3 = new Scene(gameOverRoot3, 1280, 720);
        gameOverScreen4 = new Scene(gameOverRoot4, 1280, 720);

        multiGameOverScreen = new Scene(multiGameOverRoot, 1280, 720);
        multiGameOverScreenW = new Scene(multiGameOverRootW, 1280, 720);
        multiGameOverScreen1 = new Scene(multiGameOverRoot1, 1280, 720);
        multiGameOverScreen2 = new Scene(multiGameOverRoot2, 1280, 720);
        multiGameOverScreen3 = new Scene(multiGameOverRoot3, 1280, 720);
        multiGameOverScreen4 = new Scene(multiGameOverRoot4, 1280, 720);


        ArrayList<Scene> scenes = new ArrayList<>();
        scenes.add(menu);
        scenes.add(singlePlayer);
        scenes.add(titleScreen);
        scenes.add(settings);
        scenes.add(multiPlayer);
        scenes.add(multiPlayerLobby);

        scenes.add(gameOverScreen);
        scenes.add(gameOverScreenW);
        scenes.add(gameOverScreen1);
        scenes.add(gameOverScreen2);
        scenes.add(gameOverScreen3);
        scenes.add(gameOverScreen4);

        scenes.add(multiGameOverScreen);
        scenes.add(multiGameOverScreenW);
        scenes.add(multiGameOverScreen1);
        scenes.add(multiGameOverScreen2);
        scenes.add(multiGameOverScreen3);
        scenes.add(multiGameOverScreen4);


        String stylesheetPath = "css/style.css";
        for (Scene scene : scenes) {
            scene.getStylesheets().add(stylesheetPath);
        }
        Image image = new Image("img/gui/back-button.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);
        return (titleScreen);
    }

}
