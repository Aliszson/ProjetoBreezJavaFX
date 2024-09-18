package com.example.projetomjavafx;
import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.util.SessaoUsuario;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

public class AvaliacoesUsuarioController {

    @FXML
    private TableView<ObservableList<Object>> tableAvaliacoes;

    @FXML
    private TableColumn<ObservableList<Object>, String> colNomeMusica;

    @FXML
    private TableColumn<ObservableList<Object>, String> colNomeArtista;

    @FXML
    private TableColumn<ObservableList<Object>, String> colNomeAlbum;

    @FXML
    private TableColumn<ObservableList<Object>, String> colComentario;

    @FXML
    private TableColumn<ObservableList<Object>, Integer> colNota;
    @FXML
    private ImageView voltar;



    SessaoUsuario sessaoU = new SessaoUsuario();



    @FXML
    public void onVoltarClick(){
        try {
            Application.updateStageScene(ApplicationController.getStage(), "perfil_usuario-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @FXML
    public void initialize() {
        colNomeMusica.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0).toString()));
        colNomeArtista.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1).toString()));
        colNomeAlbum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2).toString()));
        colComentario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3).toString()));
        colNota.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Integer) cellData.getValue().get(4)));

        List<List<Object>> avaliacoes = DaoFactory.createAvaliaMscDao().carregarAvaliacoes(sessaoU.getUsuario().getId());
        ObservableList<ObservableList<Object>> avaliacoesUsuario = FXCollections.observableArrayList();
        for (List<Object> linha : avaliacoes) {
            avaliacoesUsuario.add(FXCollections.observableArrayList(linha));
        }
        tableAvaliacoes.setItems(avaliacoesUsuario);
    }
}
