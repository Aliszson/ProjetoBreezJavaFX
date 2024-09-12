package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Musica;
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


public class PesquisaController implements Initializable {

    @FXML
    private TableView<Musica> tabelaResuMusica;
    @FXML
    private TextField campoPesquisa;

    private TableColumn<Album, byte[]> capa;

    @FXML
    private void pesquisaTabelaMusica() {
        tabelaResuMusica.setVisible(true);

        // Obtém o texto do campo de pesquisa e o converte para minúsculas
        String filtro = campoPesquisa.getText().toLowerCase();

        // Obtém a lista de todas as músicas e filtra com base no texto de pesquisa
        List<Musica> todasMusicas = DaoFactory.createMusicaDao().procurarTodasMusica();
        List<Musica> listaMusicas = new ArrayList<>();


        for (Musica mu : todasMusicas) {
            if (mu.getTitulo().toLowerCase().contains(filtro)) {
                listaMusicas.add(mu);

            }
        }

        // Converte a lista filtrada para ObservableList e define como itens da tabela
        ObservableList<Musica> listaMsc = FXCollections.observableArrayList(listaMusicas);

        tabelaResuMusica.setItems(listaMsc);

    }


    protected void mostraTabelaMusica(){
        // Inicializa a coluna apenas uma vez

        TableColumn<Musica, String> musicaTitulo = new TableColumn<>("Título");
        musicaTitulo.setCellValueFactory(parametro -> new SimpleObjectProperty<>(parametro.getValue().getTitulo()));
        musicaTitulo.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Musica, String> call(TableColumn<Musica, String> coluna) {
                return new TableCell<>() {
                    private final Label label = new Label();

                    @Override
                    protected void updateItem(String titulo, boolean vazio) {
                        super.updateItem(titulo, vazio);
                        if (vazio || titulo == null) {
                            setGraphic(null);
                        } else {
                            label.setText(titulo);
                            setGraphic(label);
                        }
                    }
                };
            }
        });

        // exibir id album
        TableColumn<Musica, Integer> musicaId = new TableColumn<>("Id - Album");
        musicaId.setCellValueFactory(parametro -> new SimpleObjectProperty<>(parametro.getValue().getId()));
        musicaId.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Musica, Integer> call(TableColumn<Musica, Integer> coluna) {
                return new TableCell<>() {
                    private final Label label = new Label();

                    @Override
                    protected void updateItem(Integer id, boolean vazio) {
                        super.updateItem(id, vazio);
                        if (vazio || id == null) {
                            setGraphic(null);
                        } else {
                            String idString = id.toString();
                            label.setText(idString);
                            setGraphic(label);
                        }
                    }
                };
            }
        });

        // exibir duracao musica
        // CORRIGIR!!!!!!!!
        TableColumn<Musica, String> musicaLetra = new TableColumn<>("Duracao");
        musicaLetra.setCellValueFactory(parametro -> new SimpleObjectProperty<>(parametro.getValue().getLetra()));
        musicaLetra.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Musica, String> call(TableColumn<Musica, String> coluna) {
                return new TableCell<>() {
                    private final TextArea textArea = new TextArea();

                    @Override
                    protected void updateItem(String letra, boolean vazio) {
                        super.updateItem(letra, vazio);
                        if (vazio || letra == null) {
                            setGraphic(null);
                        } else {
                            textArea.setText(letra);
                            setGraphic(textArea);
                        }
                    }
                };
            }
        });

        musicaTitulo.setPrefWidth(150); // Largura preferida (ajuste conforme necessário)
        musicaTitulo.setMinWidth(150);  // Largura mínima
        musicaTitulo.setMaxWidth(300);  // Largura máxima


        tabelaResuMusica.getColumns().add(musicaTitulo);
        tabelaResuMusica.getColumns().add(musicaLetra);
        tabelaResuMusica.getColumns().add(musicaId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostraTabelaMusica();
    }
}