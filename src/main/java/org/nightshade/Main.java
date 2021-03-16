package org.nightshade;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Hello World");
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(label);
        Scene scene = new Scene(stackPane, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Hello World");
        stage.show();
    }
}
