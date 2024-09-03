package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class RegistroController implements Initializable {
    @FXML
    private TextField nome;
    @FXML
    private PasswordField senha;
    @FXML
    private TextField bio;
    @FXML
    private ComboBox<String> genero1;
    @FXML
    private ComboBox<String> genero2;
    @FXML
    private ComboBox<String> genero3;
    @FXML
    private ImageView foto;
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


    File arquivo;
    @FXML
    void onFotoClick(){
        FileChooser fc = new FileChooser();
        arquivo = fc.showOpenDialog(ApplicationController.getStage().getScene().getWindow()); // abre pra selecionar a imagem
        if(arquivo!=null){
            foto.setImage(new Image(arquivo.toURI().toString()));
        }
    }
    @FXML
    void onRegistrarClick() throws IOException {
        Usuario u = new Usuario();
        u.setNome(nome.getText());
        u.setSenha(senha.getText());
        u.setBio(bio.getText());
        u.setGenero1(genero1.getValue());
        u.setGenero2(genero2.getValue());
        u.setGenero3(genero3.getValue());
        if(arquivo!=null){
            byte[] bytesImagem = Files.readAllBytes(arquivo.toPath());
            u.setFoto(bytesImagem);
        }
        DaoFactory.createUsuarioDao().inserirUsuario(u);

    }

}
