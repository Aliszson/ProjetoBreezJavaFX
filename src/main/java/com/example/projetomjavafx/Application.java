package com.example.projetomjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("application-view.fxml"));
        scene = new Scene(fxmlLoader.load());
        //String css = Application.class.getResource("/temas/primer-light.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setTitle("Projeto M");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static Scene getScene() {
        return scene;
    }

    public static Stage newStage(String url) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(url));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        return stage;
    }

    public static void updateStageScene(Stage stage, String url) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(url));
        Parent componentePai = fxmlLoader.load(); // pega o elemento pai da tela
        stage.getScene().setRoot(componentePai);// troca o pai da cena atual pelo do fxml carregado
        stage.sizeToScene();
    }


    public static void main(String[] args) {
        launch();
    }
}