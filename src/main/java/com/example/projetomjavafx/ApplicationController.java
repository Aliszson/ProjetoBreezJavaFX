package com.example.projetomjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationController {
    private static Stage stage;
    @FXML
    private MenuButton menuLogin;
    @FXML
    private MenuButton menuRegistro;

    @FXML
    private MenuItem registrarUsuario;
    @FXML
    private MenuItem registrarArtista;

    @FXML
    private MenuItem loginUsuario;
    @FXML
    private MenuItem loginArtista;


    @FXML
    void onRegistrarUsuario() {
        try {
            ApplicationController.setStage((Stage) menuRegistro.getScene().getWindow());
            Application.updateStageScene(getStage(), "registrar_usuario-view.fxml");
            stage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void onRegistrarArtista() {
        try {
            ApplicationController.setStage((Stage) menuRegistro.getScene().getWindow());
            Application.updateStageScene(getStage(), "registrar_artista-view.fxml");
            stage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onLoginUsuario() {
        try {
            ApplicationController.setStage((Stage) menuLogin.getScene().getWindow());
            Application.updateStageScene(getStage(), "login_usuario-view.fxml");
            stage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void onLoginArtista() {
        try {
            ApplicationController.setStage((Stage) menuLogin.getScene().getWindow());
            Application.updateStageScene(getStage(), "login_artista-view.fxml");
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