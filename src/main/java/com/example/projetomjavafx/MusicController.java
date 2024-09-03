package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Album;
import com.example.projetomjavafx.model.entities.Musica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MusicController {

    @FXML
    private TextField titulo;
    @FXML
    private TextField duracao;
    @FXML
    private TextArea letra;
    @FXML
    private TextField albumPesquisa;
    @FXML
    private ListView<String> albumList;
    @FXML
    private Button buttonAdicionar;

    private int fk_album;

    @FXML
    protected void onAdicionarClick(){
        Musica m = new Musica();
        m.setTitulo(titulo.getText());
        m.setDuracao(Float.parseFloat(duracao.getText()));
        m.setLetra(letra.getText());
        m.setFk_id_album(getFkAlbum());

        DaoFactory.createMusicaDao().inserirMusica(m);
    }

    @FXML
    protected void pesquisarAlbum(){
        String texto = albumPesquisa.getText().trim(); // remove espaços em branco nas extremidades

        if (!texto.isEmpty()){
            List<Album> lista = DaoFactory.createAlbumDao().procurarTodosAlbuns();
            List<String> nomesAlbuns = new ArrayList<String>();
            for (Album album : lista){
                // verificando texto digitado no textfield
                if (album.getNome().toLowerCase().contains(albumPesquisa.getText().toLowerCase())){
                    nomesAlbuns.add(album.getNome());
                }
            }
            ObservableList<String> listAlb = FXCollections.observableArrayList(nomesAlbuns);

            albumList.setItems(listAlb); // definir lista que aparece no listView
            albumList.refresh(); // atualiza valores na lista
        }else{
            albumList.getItems().clear();
        }
    }

    public int getFkAlbum(){
        List<Album> lista = DaoFactory.createAlbumDao().procurarTodosAlbuns();
        for (Album album : lista){
            // verificando texto digitado no textfield
            if (Objects.equals(album.getNome(), albumList.getSelectionModel().getSelectedItem())){
                return album.getId();
            }
        }
        return 0;
    }


}
