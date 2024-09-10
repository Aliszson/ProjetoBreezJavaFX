package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.util.Alerta;
import com.example.projetomjavafx.util.Restricoes;
import com.example.projetomjavafx.util.SessaoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginUsuarioController implements Initializable {
    @FXML
    private ImageView iconeVoltar;
    @FXML
    private TextField nome;
    @FXML
    private PasswordField senha;
    @FXML
    private Button entrar;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Restricoes.verificaNome(nome);
        Restricoes.verificaSenha(senha);

    }

    @FXML
    void onIconeVoltarClick(){
        try{
            Application.updateStageScene(ApplicationController.getStage(), "application-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void onEntrarClick(){
        List<String> listaErros = new ArrayList<>();

        if (Objects.equals(nome.getText(), "") || Objects.equals(senha.getText(), "")) {
            listaErros.add("- Os campos de nome e senha não podem ser vazios.");
        }else if (DaoFactory.createUsuarioDao().login(nome.getText(), senha.getText())== null){
            listaErros.add("- Nome de usuário e/ou senha inválidos");
        }

        if(!listaErros.isEmpty()){
            String erros = String.join("\n", listaErros);
            Alerta.exibirAlerta("Erro", "Campo inválido", erros , Alert.AlertType.ERROR);
            return;
        }

        SessaoUsuario sessao = new SessaoUsuario();
        sessao.setUsuario(DaoFactory.createUsuarioDao().login(nome.getText(), senha.getText()));
        
        try{
            Application.updateStageScene(ApplicationController.getStage(), "tela-principal-view.fxml");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


