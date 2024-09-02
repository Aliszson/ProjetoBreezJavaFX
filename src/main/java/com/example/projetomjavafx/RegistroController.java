package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroController implements Initializable {
    @FXML
    private TextField nome;
    @FXML
    private TextField senha;
    @FXML
    private TextField bio;
    @FXML
    private ComboBox<String> genero1;
    @FXML
    private ComboBox<String> genero2;
    @FXML
    private ComboBox<String> genero3;
    @FXML
    private Button registrar;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preencheComboBox();
    }
    public void preencheComboBox(){

        genero1.getItems().addAll("Rock", "Pop", "Jazz", "Clássico", "Hip-Hop");
        genero2.getItems().addAll("Rock", "Pop", "Jazz", "Clássico", "Hip-Hop");
        genero3.getItems().addAll("Rock", "Pop", "Jazz", "Clássico", "Hip-Hop");
    }

    @FXML
    void onRegistrarClick(){
        Usuario u = new Usuario();
        u.setNome(nome.getText());
        u.setSenha(senha.getText());
        u.setBio(bio.getText());
        u.setGenero1(genero1.getValue());
        u.setGenero2(genero2.getValue());
        u.setGenero3(genero3.getValue());
        DaoFactory.createUsuarioDao().inserirUsuario(u);

    }

}
