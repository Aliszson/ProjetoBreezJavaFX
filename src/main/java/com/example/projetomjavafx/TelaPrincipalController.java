package com.example.projetomjavafx;

import com.example.projetomjavafx.util.SessaoArtista;
import com.example.projetomjavafx.util.SessaoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {
    @FXML
    private Circle circulo;
    @FXML
    private Button inicio;

    SessaoUsuario sessaoU = new SessaoUsuario();
    SessaoArtista sessaoA = new SessaoArtista();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(sessaoU.getUsuario() == null){
            Image imagem = new Image(new ByteArrayInputStream(sessaoA.getArtista().getFoto()));
            circulo.setFill(new ImagePattern(imagem));
        }else{
            Image imagem = new Image(new ByteArrayInputStream(sessaoU.getUsuario().getFoto()));
            circulo.setFill(new ImagePattern(imagem));
        }

    }
    @FXML
    public void onInicioClick(){
        try{
            Application.updateStageScene(ApplicationController.getStage(), "tela-principal-view.fxml");
        }catch(IOException e){
            throw new RuntimeException();
        }
    }


}
