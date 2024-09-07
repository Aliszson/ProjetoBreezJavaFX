package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Usuario;
import com.example.projetomjavafx.util.Alerta;
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
import java.util.*;

public class RegistroController implements Initializable {
    @FXML
    private TextField nome;
    @FXML
    private PasswordField senha;
    @FXML
    private TextArea bio;
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

    List<String> generos = new ArrayList<>(Arrays.asList ("Rock", "Jazz", "Clássico", "Rap"));
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        genero1.getItems().addAll(generos);
        genero2.getItems().addAll(generos);
        genero3.getItems().addAll(generos);

    }

    File padrao = new File("src/main/resources/img/fotoPerfilPadrao.png");
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
        List<String> listaErros = new ArrayList<>();

        if(Objects.equals(nome.getText(), "") || Objects.equals(senha.getText(), "")){
            listaErros.add("- Os campos de nome e senha não podem ser vazios.");

        }else if(DaoFactory.createUsuarioDao().procurarTodosUsuario().toString().toLowerCase().contains(nome.getText().toLowerCase())) {
          listaErros.add("- Nome de usuário já esta sendo utilizado");
        }else{
            u.setNome(nome.getText());
            u.setSenha(senha.getText());
        }

        if(bio.getText().length() > 280){
            listaErros.add("- Limite de caracteres do campo bio excedido");
        }
        else{
            u.setBio(bio.getText());
        }

        if(genero1.getValue() == null){
            listaErros.add("- Você deve escolher um gênero musical no campo gênero favorito.");
        }else{
            u.setGenero1(genero1.getValue());
        }

        u.setGenero2(genero2.getValue());
        u.setGenero3(genero3.getValue());


        if(arquivo!=null){
            byte[] bytesImagem = Files.readAllBytes(arquivo.toPath());
            u.setFoto(bytesImagem);
        }else{
            byte[] bytesImagem = Files.readAllBytes(padrao.toPath());
            u.setFoto(bytesImagem);
        }

        if(!listaErros.isEmpty()){
            String erro = String.join("\n", listaErros);
            Alerta.exibirAlerta("Erro", "Campo inválido", erro , Alert.AlertType.ERROR);
            return;
        }

        DaoFactory.createUsuarioDao().inserirUsuario(u);
        Alerta.exibirAlerta(null, null, "Registro realizado com sucesso!", Alert.AlertType.INFORMATION);
        nome.clear();
        senha.clear();
        bio.clear();
        genero1.setValue(null);
        genero2.setValue("Nenhum");
        genero3.setValue("Nenhum");
        File perfilVazio = new File("src/main/resources/img/perfilvazio.png");
        foto.setImage(new Image(perfilVazio.toURI().toString()));
        arquivo = null;


    }

    public void procuraNome(){
    }




}
