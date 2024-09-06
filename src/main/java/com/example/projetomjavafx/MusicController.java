package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Album;
import com.example.projetomjavafx.model.entities.Musica;
import com.example.projetomjavafx.util.Restricoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MusicController implements Initializable {

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
    protected void onAdicionarClick() throws ParseException {

        if (duracao.getText() == ""){

        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date drcDate = sdf.parse(duracao.getText()); // cria objeto tipo Date

        Musica m = new Musica();
        m.setTitulo(titulo.getText());
        m.setDuracao(new Time(drcDate.getTime())); // converte esse objeto para Time, o que o banco precisa
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
            // comparando texto selecionado no listView com algum string selecionado na lista de albuns
            if (Objects.equals(album.getNome(), albumList.getSelectionModel().getSelectedItem())){
                return album.getId();
            }
        }
        return 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Restricoes.DURACAO(duracao);

    }
}
