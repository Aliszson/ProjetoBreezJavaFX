package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Artista;
import com.example.projetomjavafx.util.Alerta;
import com.example.projetomjavafx.util.Restricoes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

public class RegistroArtistaController implements Initializable
{
    @FXML
    private ImageView iconeVoltar;
    @FXML
    private Button botaoUsuario;
    @FXML
    private TextField nome;
    @FXML
    private PasswordField senha;
    @FXML
    private ComboBox<String> genero;
    @FXML
    private ImageView foto;
    @FXML
    private Button registrar;

    List<String> generos = new ArrayList<>(Arrays.asList ("Rock", "Jazz", "Clássico", "Rap"));
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Restricoes.verificaNome(nome);
        Restricoes.verificaSenha(senha);
        genero.getItems().addAll(generos);

    }

    @FXML
    void onIconeVoltarClick() {
        try {
            Application.updateStageScene(ApplicationController.getStage(), "application-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        @FXML
    void onUsuarioClick(){
        try{
            Stage stageAtual = (Stage) botaoUsuario.getScene().getWindow();
            Application.updateStageScene(stageAtual, "registrar_usuario-view.fxml");
        }catch(IOException e){
            throw new RuntimeException();
        }
    }

    File padraoArtista = new File("src/main/resources/img/perfilPadraoArtista.png");
    File arquivo;
    @FXML
    void onFotoClick(){
        FileChooser fc = new FileChooser();
        arquivo = fc.showOpenDialog(ApplicationController.getStage().getScene().getWindow());
        if(arquivo != null){
            foto.setImage(new Image(arquivo.toURI().toString()));
        }

    }

    @FXML
    void onRegistrarClick() throws  IOException{
        Artista a = new Artista();
        List<String> listaErros = new ArrayList<>();

        if(Objects.equals(nome.getText(),"") || Objects.equals(senha.getText(),"")){
            listaErros.add("- Os campos de nome e senha não podem ser vazios");
        }else if(DaoFactory.createArtistaDao().procurarTodosArtista().toString().toLowerCase().contains(nome.getText())){
            listaErros.add("- Esse nome de artista já está sendo utilizado");
        }else{
            a.setNome(nome.getText());
            a.setSenha(senha.getText());
        }

        if(genero.getValue() == null){
            listaErros.add("- O gênero musical não pode ser vazio");
        }

        if(arquivo != null){
           byte[] bytesImagem =  Files.readAllBytes(arquivo.toPath());
           a.setFoto(bytesImagem);
        }else{
            byte [] bytesImagem = Files.readAllBytes(padraoArtista.toPath());
            a.setFoto(bytesImagem);
        }

        if(!listaErros.isEmpty()){
            String erros = String.join("\n", listaErros);
            Alerta.exibirAlerta("Erro", "Campo inválido", erros, Alert.AlertType.ERROR);
            return;
        }

        DaoFactory.createArtistaDao().inserirArtista(a);
        Alerta.exibirAlerta(null, null , "Registro realizado com sucesso", Alert.AlertType.INFORMATION);
        limparCampos();
    }

    public void limparCampos(){
        nome.clear();
        senha.clear();
        genero.setValue("Nenhum");
        File perfilVazio = new File("src/main/resources/img/perfilvazioArtista.png");
        foto.setImage(new Image(perfilVazio.toURI().toString()));
        arquivo = null;
    }
}
