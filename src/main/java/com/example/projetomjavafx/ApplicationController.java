package com.example.projetomjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class ApplicationController {
    @FXML
    private Button botaoRegistrar;
    @FXML
    void onBotaoRegistrarClick(){
        try {
            Application.newStage("registrar_usuario-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}