package com.example.projetomjavafx;


import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Album;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class AlbumController {

    @FXML
    private TextField nomeAlbum;
    @FXML
    private TextField generoPesquisa1;
    @FXML
    private TextField generoPesquisa2;
    @FXML
    private Button buttonAddAlbum;
    @FXML
    private ListView<String> generoList1;
    @FXML
    private ListView<String> generoList2;
    @FXML
    private ImageView capaAlbum;

    @FXML
    protected void onAddAlbumClick() throws IOException {
        Album a = new Album();

        a.setNome(nomeAlbum.getText());
        a.setGenero1(getNomeGenero1());
        a.setGenero2(getNomeGenero2());

        if(arquivo!=null){
            byte[] bytesImagem = Files.readAllBytes(arquivo.toPath());
            a.setCapa(bytesImagem);
        }else{
            byte[] bytesImagem = Files.readAllBytes(padrao.toPath());
            a.setCapa(bytesImagem);
        }
        DaoFactory.createAlbumDao().inserirAlbum(a);
        limparCampos();
    }

    Random random = new Random();
    int numAleatorio = random.nextInt(1,5); // tendencioso para a imagem do fone
    File padrao = new File("src/main/resources/img/albumImg/capasPadrao/capaAlbum"+numAleatorio+".jpg");
    File arquivo = null;

    @FXML
    void onCapaClick(){
        FileChooser fc = new FileChooser();
        arquivo = fc.showOpenDialog(com.example.projetomjavafx.Album.getScene().getWindow()); // abre pra selecionar a imagem
        if(arquivo!=null){
            capaAlbum.setImage(new Image(arquivo.toURI().toString()));
        }
    }
    @FXML
    protected void pesquisarGenero1(){
        String textoPrincipal = generoPesquisa1.getText().trim();

        if (!textoPrincipal.isEmpty()){
            List<String> lista = generos();
            List<String> nomesGeneros = new ArrayList<String>();

            for (String genero: lista){
                // verificando texto digitado no textfield
                if ((genero.toLowerCase().contains(generoPesquisa1.getText().toLowerCase())) && !genero.equals(generoList2.getSelectionModel().getSelectedItem())){
                    nomesGeneros.add(genero);
                }
            }
            ObservableList<String> listaGen1 = FXCollections.observableArrayList(nomesGeneros);

            generoList1.setItems(listaGen1);
            generoList1.refresh();
        }else {
            generoList1.getItems().clear();
        }
    }
    @FXML
    protected void pesquisarGenero2(){
        String textoSecundario = generoPesquisa2.getText().trim();

        if (!textoSecundario.isEmpty()){
            List<String> lista = generos();
            List<String> nomesGeneros = new ArrayList<String>();

            for (String genero: lista){
                // verificando texto digitado no textfield
                if ((genero.toLowerCase().contains(generoPesquisa2.getText().toLowerCase())) && (!genero.equals(generoList1.getSelectionModel().getSelectedItem()))){
                    nomesGeneros.add(genero);
                }
            }
            ObservableList<String> listaGen2 = FXCollections.observableArrayList(nomesGeneros);

            generoList2.setItems(listaGen2);
            generoList2.refresh();
        }else {
            generoList2.getItems().clear();
        }
    }

    public String getNomeGenero1(){
        List<String> lista = generos();
        for (String genero : lista){
            // comparando texto selecionado no listView com algum string selecionado na lista de albuns
            if (Objects.equals(genero, generoList1.getSelectionModel().getSelectedItem())){
                return genero;
            }
        }
        return null;
    }

    public String getNomeGenero2(){
        List<String> lista = generos();
        for (String genero : lista){
            // comparando texto selecionado no listView com algum string selecionado na lista de albuns
            if (Objects.equals(genero, generoList2.getSelectionModel().getSelectedItem())){
                return genero;
            }
        }
        return null;
    }

    public List<String> generos() {
        Set<String> generosSet = new LinkedHashSet<>(Arrays.asList(
                "Rock", "Pop", "Jazz", "Blues", "Hip Hop", "R&B", "Country", "Reggae",
                "Salsa", "Funk", "Metal", "Classical", "Electronic", "House", "Techno",
                "Disco", "Gospel", "Punk", "Folk", "Soul", "Indie", "Samba", "Bossa Nova",
                "MPB", "Forró", "Axé", "Pagode", "K-pop", "J-pop", "Reggaeton", "Afrobeat",
                "Trap", "Grunge", "Progressive Rock", "Ambient", "Trance", "Dubstep", "Ska",
                "Hardcore", "Emo", "New Wave", "Indie"
        ));

        // convertendo set em lista
        return new ArrayList<>(generosSet);
    }

    void limparCampos(){
        nomeAlbum.setText(null);
        generoPesquisa1.clear();
        generoPesquisa2.clear();
        generoList1.getItems().clear();
        generoList2.getItems().clear();
    }
}
