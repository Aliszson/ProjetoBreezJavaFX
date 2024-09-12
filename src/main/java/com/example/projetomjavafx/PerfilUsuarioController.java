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

public class PerfilUsuarioController implements Initializable {
    @FXML
    private Circle circuloFoto;
    @FXML
    private Label nome;
    @FXML
    private TextArea bio;
    @FXML
    private Label generos;
    @FXML
    private ImageView voltar;
    @FXML
    private Button sair;

    SessaoUsuario sessaoU = new SessaoUsuario();

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
        sessaoU.setUsuario(null);
        try {
            Application.updateStageScene(ApplicationController.getStage(), "application-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void setarDados(){
        Image imagem = new Image(new ByteArrayInputStream(sessaoU.getUsuario().getFoto()));
        circuloFoto.setFill(new ImagePattern(imagem));
        nome.setText(sessaoU.getUsuario().getNome());
        bio.setText(sessaoU.getUsuario().getBio());
        bio.setEditable(false);

        List<String> generosCurtidos = new ArrayList();

        generosCurtidos.add(sessaoU.getUsuario().getGenero1());

        if(sessaoU.getUsuario().getGenero2() != null){
           generosCurtidos.add(sessaoU.getUsuario().getGenero2());
        }
        if(sessaoU.getUsuario().getGenero3() != null){
            generosCurtidos.add(sessaoU.getUsuario().getGenero3());
        }

        String g = String.join(", ", generosCurtidos);
        generos.setText(g);


    }




    }






