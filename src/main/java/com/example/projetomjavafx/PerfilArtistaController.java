package com.example.projetomjavafx;

import com.example.projetomjavafx.util.SessaoArtista;
import com.example.projetomjavafx.util.SessaoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PerfilArtistaController implements Initializable {

    @FXML
    private Circle circuloFoto;
    @FXML
    private Label nome;
    @FXML
    private Label genero;
    @FXML
    private ImageView voltar;
    @FXML
    private Button sair;

    SessaoArtista sessaoA = new SessaoArtista();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        setarDados();
    }

    @FXML
    public void onVoltarClick(){
        try {
            Application.updateStageScene(ApplicationController.getStage(), "tela-principal-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    @FXML
    public void onSairClick(){
        sessaoA.setArtista(null);
        try {
            Application.updateStageScene(ApplicationController.getStage(), "application-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void setarDados() {
        Image imagem = new Image(new ByteArrayInputStream(sessaoA.getArtista().getFoto()));
        circuloFoto.setFill(new ImagePattern(imagem));
        nome.setText(sessaoA.getArtista().getNome());
        genero.setText(sessaoA.getArtista().getGenero());


    }
}