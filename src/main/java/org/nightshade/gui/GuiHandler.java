package org.nightshade.gui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
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
        Parent gameOverRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/game_over.fxml")));
        Parent multiPlayerLobbyRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/GuiComponents/multi_player_lobby.fxml")));
        menu = new Scene(menuRoot, 1280, 720);
        singlePlayer = new Scene(singlePlayerRoot, 1280, 720);
        titleScreen = new Scene(titleScreenRoot, 1280, 720);
        settings = new Scene(settingsRoot, 1280, 720);
        multiPlayer = new Scene(multiPlayerRoot, 1280, 720);
        gameOverScreen = new Scene(gameOverRoot, 1280, 720);
        multiPlayerLobby = new Scene(multiPlayerLobbyRoot, 1280, 720);

        ArrayList<Scene> scenes = new ArrayList<>();
        scenes.add(menu);
        scenes.add(singlePlayer);
        scenes.add(titleScreen);
        scenes.add(settings);
        scenes.add(multiPlayer);
        scenes.add(gameOverScreen);
        scenes.add(multiPlayerLobby);
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
