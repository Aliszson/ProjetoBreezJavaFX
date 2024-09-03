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
    void onBotaoRegistrarClick(){
        try {
            stage = Application.newStage("registrar_usuario-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stage getStage(){
        return stage;
    }
}