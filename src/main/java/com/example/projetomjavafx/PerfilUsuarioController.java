package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.util.Alerta;
import com.example.projetomjavafx.util.Restricoes;
import com.example.projetomjavafx.util.SessaoArtista;
import com.example.projetomjavafx.util.SessaoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PerfilUsuarioController implements Initializable {
    @FXML
    private Circle circuloFoto;
    @FXML
    private TextField nome;
    @FXML
    private TextArea bio;
    @FXML
    private Label generos;
    @FXML
    private ImageView voltar;
    @FXML
    private Button sair;
    @FXML
    private Button salvar;
    SessaoUsuario sessaoU = new SessaoUsuario();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        setarDados();
        Restricoes.verificaNome(nome);
        Restricoes.verificaBio(bio);

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


    @FXML
    public void onSalvarClick(){
        List<String> listaErros = new ArrayList<>();

        if(Objects.equals(nome.getText(), "")){
            listaErros.add("- O nome não pode ser vazio");
            nome.setText(sessaoU.getUsuario().getNome());

        }else if(Objects.equals(nome.getText().toLowerCase(), sessaoU.getUsuario().getNome().toLowerCase()))
        {   // nesse o caso usuário pode digitar o mesmo nome mas com diferença nas letras,
            // por exemplo nome atual -> cleber | nome digitado -> CleBer
            nome.setText(nome.getText());
            sessaoU.getUsuario().setNome(nome.getText());
        }else if(DaoFactory.createUsuarioDao().procurarTodosUsuario().toString().toLowerCase().contains(nome.getText().toLowerCase())) {
            listaErros.add("- Nome de usuário já esta sendo utilizado");
        }

        if(!listaErros.isEmpty()){
            String erros = String.join("\n", listaErros);
            Alerta.exibirAlerta("Erro", "Campo inválido", erros , Alert.AlertType.ERROR);
            return;
        }

        sessaoU.getUsuario().setNome(nome.getText());
        sessaoU.getUsuario().setBio(bio.getText());
        DaoFactory.createUsuarioDao().atualizarNomeUsuario(sessaoU.getUsuario());
        DaoFactory.createUsuarioDao().atualizarBioUsuario(sessaoU.getUsuario());

    }

    public void setarDados(){
        Image imagem = new Image(new ByteArrayInputStream(sessaoU.getUsuario().getFoto()));
        circuloFoto.setFill(new ImagePattern(imagem));
        nome.setText(sessaoU.getUsuario().getNome());
        bio.setText(sessaoU.getUsuario().getBio());

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






