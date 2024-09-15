package com.example.projetomjavafx;

import com.example.projetomjavafx.util.SessaoArtista;
import com.example.projetomjavafx.util.SessaoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class TelaPrincipalController  implements Initializable {
    @FXML
    private Circle circuloFoto;
    @FXML
    private Button inicio;
    @FXML
    private Region regiao;

    // para usuário
    @FXML
    private MenuButton pesquisar;
    @FXML
    private MenuItem pesqAlbum;
    @FXML
    private MenuItem pesqMusica;

    // para artista;
    @FXML
    private MenuButton gerenciar;
    @FXML
    private MenuItem addAlbum;
    @FXML
    private MenuItem addMusica;
    @FXML
    private MenuItem delAlbum;
    @FXML
    private MenuItem delMusica;

    SessaoUsuario sessaoU = new SessaoUsuario();
    SessaoArtista sessaoA = new SessaoArtista();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        identificar();
        setarOpcoes();

    }

    @FXML
    public void onCirculoClicked() {
        if(identificar() == 1){
            try {
                Application.updateStageScene(ApplicationController.getStage(), "perfil_usuario-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }else{
            try {
                Application.updateStageScene(ApplicationController.getStage(), "perfil_artista-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException();
            }

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
    // funcinalidades do usuario
    @FXML
    public void onPesquisarAlbumClicked(){
        try{
            Application.updateStageScene(ApplicationController.getStage(), "pesquisa-album-view.fxml");
        }catch(IOException e){
            throw new RuntimeException();
        }
    }

    @FXML
    public void onPesquisarMusicaClicked(){
        try{
            Application.updateStageScene(ApplicationController.getStage(), "pesquisa-musica-view.fxml");
        }catch(IOException e){
            throw new RuntimeException();
        }
    }

    // funcionalidades do artista;
    @FXML
    public void onAddAlbumClick(){
        try{
            Application.updateStageScene(ApplicationController.getStage(), "add-album-view.fxml");
        }catch(IOException e){
            throw new RuntimeException();
        }
    }

    @FXML
    public void onAddMusicaClick(){
        try{
            Application.updateStageScene(ApplicationController.getStage(), "add-music-view.fxml");
        }catch(IOException e){
            throw new RuntimeException();
        }
    }


    @FXML
    public void onDelAlbumClick(){
        try{
            // depois só trocar para a tela de excluir album
            Application.updateStageScene(ApplicationController.getStage(), "tela-principal-view.fxml");
        }catch(IOException e){
            throw new RuntimeException();
        }
    }


    @FXML
    public void onDelMusicaClick(){
        try{
            // depois só trocar para a tela de excluir musica
            Application.updateStageScene(ApplicationController.getStage(), "tela-principal-view.fxml");
        }catch(IOException e){
            throw new RuntimeException();
        }
    }


    public int identificar(){

        if(sessaoA.getArtista() == null){
            return 1;
        }else{
            return 2;
        }
    }

    @FXML
    public void setarOpcoes(){
        if(identificar() == 1){
            Image imagem = new Image(new ByteArrayInputStream(sessaoU.getUsuario().getFoto()));
            circuloFoto.setFill(new ImagePattern(imagem));
            gerenciar.setVisible(false);;
            gerenciar.setManaged(false);
            regiao.setMinWidth(303);
        }else{
            Image imagem = new Image(new ByteArrayInputStream(sessaoA.getArtista().getFoto()));
            circuloFoto.setFill(new ImagePattern(imagem));
            pesquisar.setVisible(false);
        }


    }















}








