package com.example.projetomjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationController {
    private static Stage stage;

    @FXML
    private Button botaoRegistrar;
    @FXML
    private Button botaoLogar;
    @FXML
    void onBotaoRegistrarClick(){
        try {
            ApplicationController.setStage((Stage) botaoRegistrar.getScene().getWindow());
            Application.updateStageScene(getStage(), "registrar_usuario-view.fxml");
            stage.setTitle("Registrar");
            stage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onBotaoLogarClick (){
        try {
            ApplicationController.setStage((Stage) botaoLogar.getScene().getWindow());
            Application.updateStageScene(getStage(), "login_usuario-view.fxml");
            stage.setTitle("Logar");
            stage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stage getStage(){
        return stage;
    }

    public static void setStage(Stage stage) {
        ApplicationController.stage = stage;
    }
}