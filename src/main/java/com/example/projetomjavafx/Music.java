package com.example.projetomjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Music extends javafx.application.Application {
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.projetomjavafx.Application.class.getResource("add-music.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static Scene getScene() {
        return scene;
    }

    public static Stage newStage(String url) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(url));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Universidade X");
        stage.setScene(scene);
        stage.show();
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}

