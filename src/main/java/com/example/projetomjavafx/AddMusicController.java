package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Album;
import com.example.projetomjavafx.model.entities.Artista;
import com.example.projetomjavafx.model.entities.ArtistaProduz;
import com.example.projetomjavafx.model.entities.Musica;
import com.example.projetomjavafx.util.Restricoes;
import com.example.projetomjavafx.util.SessaoArtista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddMusicController implements Initializable {

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

    // Avisos

    @FXML
    Label avisoTitulo;
    @FXML
    Label avisoDuracao;
    @FXML
    Label avisoAlbum;


    @FXML
    protected void onAdicionarClick() throws ParseException {
        avisoLabel();
        tratarDuracao();
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
            //Sessão universal
            SessaoArtista sessaoArtista = new SessaoArtista();

            List<Album> listaAlbuns = DaoFactory.createAlbumDao().procurarTodosAlbuns();

            List<String> nomesAlbuns = new ArrayList<String>();
            for (Album album : listaAlbuns){
                // verificando texto digitado no textfield
                if (album.getNome().toLowerCase().contains(albumPesquisa.getText().toLowerCase()) && Objects.equals(album.getFk_id_artista(),sessaoArtista.getArtista().getId())){
                    nomesAlbuns.add(album.getNome() + " - " + sessaoArtista.getArtista().getNome());
                }
            }
            System.out.println(sessaoArtista.getArtista().toString());
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

        avisoTitulo.setVisible(false);
        avisoDuracao.setVisible(false);
        avisoAlbum.setVisible(false);
    }

    public void avisoLabel(){

        if (titulo.getText().isEmpty()) { // caso texto seja vazio
            avisoTitulo.setVisible(true);
        }else {
            avisoTitulo.setVisible(false);
        }

        if (duracao.getText().isEmpty()){ // caso album seja vazio
            avisoDuracao.setVisible(true);
        }else {
            avisoDuracao.setVisible(false);
        }

        if (albumPesquisa.getText().isEmpty() || albumList.getSelectionModel().isEmpty()){ // caso seleção de album seja vazia
            avisoAlbum.setVisible(true);
        }else {
            avisoAlbum.setVisible(false);
        }
    }

    public void tratarDuracao() throws ParseException {

        String texto = duracao.getText();
        String duracaoFormatada = "";

        if (texto.length() == 1){
            duracaoFormatada = "00:00:0"+texto;

        }else if (texto.length() == 2){
            duracaoFormatada = "00:00:"+texto;
        }
        else if (texto.length() == 4){
            duracaoFormatada = "00:0"+texto;
        }
        else if (texto.length() == 5){
            duracaoFormatada = "00:"+texto;
        }
        else if (texto.length() == 7){
            duracaoFormatada = "0"+texto;
        }

        System.out.println(duracaoFormatada);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date drcDate = sdf.parse(duracaoFormatada);

        duracaoFormatada = sdf.format(drcDate);
        System.out.println(duracaoFormatada);
        duracao.setText(duracaoFormatada);
    }
}
