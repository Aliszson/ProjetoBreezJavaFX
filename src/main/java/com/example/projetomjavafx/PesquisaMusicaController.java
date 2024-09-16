package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Artista;
import com.example.projetomjavafx.model.entities.Musica;
import com.example.projetomjavafx.model.entities.Album;
import com.example.projetomjavafx.util.Alerta;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class PesquisaMusicaController implements Initializable{


public class PesquisaMusicaController implements Initializable {
    @FXML
    private ImageView voltar;
    @FXML
    private TableView<Musica> tabelaResuMusica;
    @FXML
    private TextField campoPesquisa;
    @FXML
    private Button avaliar;
  
  
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

        // Coluna para exibir a capa do álbum
        TableColumn<Musica, byte[]> albumMusicaCapa = new TableColumn<>("Capa");
        albumMusicaCapa.setCellValueFactory(parametro -> {
            // Obtenha o objeto Album associado à música
            Musica musica = parametro.getValue();
            Album album = DaoFactory.createMusicaDao().procurarAlbumPorFk(musica.getFk_id_album());

            if (album != null && album.getCapa() != null) {
                return new SimpleObjectProperty<>(album.getCapa());
            } else {
                return new SimpleObjectProperty<>(null); // Se não houver capa, retorna null
            }
        });

        // define como a célula vai exibir a imagem
        albumMusicaCapa.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Musica, byte[]> call(TableColumn<Musica, byte[]> coluna) {
                return new TableCell<>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(byte[] capa, boolean vazio) {
                        super.updateItem(capa, vazio);
                        if (vazio || capa == null) {
                            setGraphic(null); // Não exibe imagem se estiver vazio
                        } else {
                            try {
                                // Cria o InputStream a partir do array de bytes
                                InputStream inputStream = new ByteArrayInputStream(capa);
                                Image image = new Image(inputStream);

                                // Ajusta o tamanho da imagem
                                imageView.setFitHeight(50);
                                imageView.setFitWidth(50);
                                imageView.setImage(image);

                                setGraphic(imageView); // Define a imagem na célula
                            } catch (Exception e) {
                                setGraphic(null);
                            }
                        }
                    }
                };
            }
        });

        // exibir titulo
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

        // exibir duracao musica
        TableColumn<Musica, Time> musicaDuracao = new TableColumn<>("Duracao");
        musicaDuracao.setCellValueFactory(parametro -> new SimpleObjectProperty<>(parametro.getValue().getDuracao()));
        musicaDuracao.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Musica, Time> call(TableColumn<Musica, Time> coluna) {
                return new TableCell<>() {
                    private final Label label = new Label();

                    @Override
                    protected void updateItem(Time duracao, boolean vazio) {
                        super.updateItem(duracao, vazio);
                        if (vazio || duracao == null) {
                            setGraphic(null);
                        } else {
                            label.setText(String.valueOf(duracao));
                            setGraphic(label);
                        }
                    }
                };
            }
        });

        // exibir o nome do artista
        TableColumn<Musica, String> colunaArtista = new TableColumn<>("Artista");
        colunaArtista.setCellValueFactory(parametro -> {
            // Obtenha o objeto Musica
            Musica musica = parametro.getValue();

            // Busque o artista usando o método que criamos
            Artista artista = DaoFactory.createMusicaDao().procurarArtistaPorFk(musica.getId());

            // Retorna o nome do artista, se houver
            if (artista != null) {
                return new SimpleStringProperty(artista.getNome());
            } else {
                return new SimpleStringProperty("Artista desconhecido"); // Ou alguma outra mensagem padrão
            }
        });

        // Coluna para exibir a média das avaliações
        TableColumn<Musica, Float> colunaMedia = new TableColumn<>("Média Avaliações");
        colunaMedia.setCellValueFactory(parametro -> {
            // Obtenha o objeto Musica
            Musica musica = parametro.getValue();

            // Busque a média das avaliações usando o método que criamos
            float media = DaoFactory.createAvaliaMscDao().calcularMediaPorMusica(musica.getId());

            return new SimpleObjectProperty<>(media);
        });

        // Define a formatação para a média, se necessário
        colunaMedia.setCellFactory(column -> new TableCell<Musica, Float>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Float media, boolean vazio) {
                super.updateItem(media, vazio);
                if (vazio || media == null) {
                    setGraphic(null);
                } else {
                    label.setText(String.format("%.1f/5", media)); // Formata para duas casas decimais
                    setGraphic(label);
                }
            }
        });

        musicaTitulo.setPrefWidth(150); // Largura preferida (ajuste conforme necessário)
        musicaTitulo.setMinWidth(150);  // Largura mínima
        musicaTitulo.setMaxWidth(300);  // Largura máxima

        colunaArtista.setPrefWidth(150); // Largura preferida (ajuste conforme necessário)
        colunaArtista.setMinWidth(150);  // Largura mínima
        colunaArtista.setMaxWidth(300);  // Largura máxima

        tabelaResuMusica.getColumns().add(albumMusicaCapa);
        tabelaResuMusica.getColumns().add(musicaTitulo);
        tabelaResuMusica.getColumns().add(musicaDuracao);
        tabelaResuMusica.getColumns().add(colunaArtista);
        tabelaResuMusica.getColumns().add(colunaMedia);
    }

    @FXML
    protected void onAvaliarClick(){
        int id_musica;
        int fk_album;

        if (tabelaResuMusica.getSelectionModel().getSelectedItem() != null){ // captura item selecionado na tabela

            id_musica = tabelaResuMusica.getSelectionModel().getSelectedItem().getId();
            fk_album = tabelaResuMusica.getSelectionModel().getSelectedItem().getFk_id_album();

            // carrega o novo FXML e obtem o controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("avalia-msc-view.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AvaliaMscController controller = loader.getController();
            controller.setIdMusica(id_musica); // passa o ID da música
            controller.setFkAlbum(fk_album);
            controller.capturarValores(); // definindo os valores a serem chamados na outra tela antes dela ser chamada, sem precisar do initialize

            if (controller.avaliacaoExistente()){
                Alerta.exibirAlerta("Escolha outra música.", "Música já avaliada.","Escolha outra música ou selecione editar avaliação..", Alert.AlertType.INFORMATION);
            }else {
                // atualiza a cena com o novo root
                Stage stage = (Stage) avaliar.getScene().getWindow();
                stage.setScene(new Scene(root));
            }
        }else {
            Alerta.exibirAlerta("Selecione uma música!", "Nenhuma música selecionada.","Por favor, selecione uma música para avaliar.", Alert.AlertType.INFORMATION);
        }
    }

    private void onVoltarClick(){
        try{
            Application.updateStageScene(ApplicationController.getStage(), "tela-principal-view.fxml");
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostraTabelaMusica();
    }
}