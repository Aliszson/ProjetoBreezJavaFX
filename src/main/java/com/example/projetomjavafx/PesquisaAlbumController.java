package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Artista;
import com.example.projetomjavafx.model.entities.Album;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PesquisaAlbumController implements Initializable {
    @FXML
    private ImageView voltar;
    @FXML
    private TableView<Album> tabelaResuAlbum;
    @FXML
    private TextField campoPesquisa;

    @FXML
    private void pesquisaTabelaAlbum() {
        tabelaResuAlbum.setVisible(true);

        // obter o texto do campo de pesquisa e o converte para minúsculas
        String filtro = campoPesquisa.getText().toLowerCase();

        // obter a lista de todas as músicas e filtra com base no texto de pesquisa
        List<Album> todosAlbuns = DaoFactory.createAlbumDao().procurarTodosAlbuns();
        List<Album> listaAlbuns = new ArrayList<>();

        for (Album alb : todosAlbuns) {
            if (alb.getNome().toLowerCase().contains(filtro)) {
                listaAlbuns.add(alb);
            }
        }

        // converte a lista filtrada para ObservableList e define como itens da tabela
        ObservableList<Album> listaAlb = FXCollections.observableArrayList(listaAlbuns);

        tabelaResuAlbum.setItems(listaAlb);
    }

    protected void mostraTabelaAlbuns(){
        // Inicializa a coluna apenas uma vez

        TableColumn<Album, byte[]> albumCapa = new TableColumn("Capa");
        // configura a fábrica de valor de célula
        albumCapa.setCellValueFactory(parametro -> new SimpleObjectProperty<>(parametro.getValue().getCapa()));
        // configura a fábrica de célula para exibir a capa do álbum
        albumCapa.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Album, byte[]> call(TableColumn<Album, byte[]> coluna) {
                return new TableCell<>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(byte[] bytes, boolean vazio) {
                        super.updateItem(bytes, vazio);
                        if (vazio || bytes == null) {
                            setGraphic(null);
                        } else {
                            // cria um HBox para organizar os componentes
                            HBox box = new HBox();
                            box.setSpacing(10);

                            // configura a imagem
                            InputStream inputStream = new ByteArrayInputStream(bytes);
                            Image image = new Image(inputStream);
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                            imageView.setImage(image);

                            // adiciona a imagem e os detalhes ao HBox
                            box.getChildren().addAll(imageView);
                            setGraphic(box);
                        }
                    }
                };
            }
        });

        TableColumn<Album, String> albumNome = new TableColumn<>("Nome");
        // Configura a fábrica de valor de célula
        albumNome.setCellValueFactory(parametro -> new SimpleObjectProperty<>(parametro.getValue().getNome()));

        // Configura a fábrica de célula para exibir o nome do artista
        albumNome.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Album, String> call(TableColumn<Album, String> coluna) {
                return new TableCell<>() {
                    private final Label label = new Label();

                    @Override
                    protected void updateItem(String nomeArtista, boolean vazio) {
                        super.updateItem(nomeArtista, vazio);
                        if (vazio || nomeArtista == null) {
                            setGraphic(null);
                        } else {
                            // Exibe o nome do artista em um Label
                            label.setText(nomeArtista);
                            setGraphic(label);
                        }
                    }
                };
            }
        });

        TableColumn<Album, String> albumArtista = new TableColumn<>("Artista");
        albumArtista.setCellValueFactory(parametro -> {
            Album album = parametro.getValue();
            Artista artista = DaoFactory.createArtistaDao().procurarPorIdArtista(album.getFk_id_artista());
            return new SimpleObjectProperty<>(artista != null ? artista.getNome() : "Desconhecido");
        });

        albumArtista.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Album, String> call(TableColumn<Album, String> coluna) {
                return new TableCell<>() {
                    private final Label label = new Label();

                    @Override
                    protected void updateItem(String nomeArtista, boolean vazio) {
                        super.updateItem(nomeArtista, vazio);
                        if (vazio || nomeArtista == null) {
                            setGraphic(null);
                        } else {
                            label.setText(nomeArtista);
                            setGraphic(label);
                        }
                    }
                };
            }
        });

        TableColumn<Album, String> albumGeneros = new TableColumn<>("Gêneros");
        // sonfigura a fábrica de valor de célula
        albumGeneros.setCellValueFactory(parametro -> {
            String genero1 = parametro.getValue().getGenero1();
            String genero2 = parametro.getValue().getGenero2();

            // se genero2 for nulo ou vazio, exibe apenas genero1
            String generos = genero1;
            System.out.println("Antes: "+generos);
            if (genero2 != null && !genero2.trim().isEmpty()) {
                generos += ", " + genero2;
            }

            return new SimpleObjectProperty<>(generos);
        });

        TableColumn<Album, String> albumMediaAvaliacoes = new TableColumn<>("Média de Avaliações");
        albumMediaAvaliacoes.setCellValueFactory(parametro -> {
            Album album = parametro.getValue();
            double mediaAvaliacoes = DaoFactory.createAlbumDao().calcularMediaAvaliacoes(album.getId());
            return new SimpleObjectProperty<>(String.format("%.1f/5", mediaAvaliacoes));
        });

        albumNome.setPrefWidth(150); // Largura preferida
        albumNome.setMinWidth(150);  // Largura mínima
        albumNome.setMaxWidth(300);  // Largura máxima

        albumArtista.setPrefWidth(150); // Largura preferida
        albumArtista.setMinWidth(150);  // Largura mínima
        albumArtista.setMaxWidth(300);  // Largura máxima

        tabelaResuAlbum.getColumns().add(albumCapa);
        tabelaResuAlbum.getColumns().add(albumNome);
        tabelaResuAlbum.getColumns().add(albumArtista);
        tabelaResuAlbum.getColumns().add(albumGeneros);
        tabelaResuAlbum.getColumns().add(albumMediaAvaliacoes);
    }

    @FXML
    private void onVoltarClick(){
        try{
            Application.updateStageScene(ApplicationController.getStage(), "tela-principal-view.fxml");
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostraTabelaAlbuns();
    }
}