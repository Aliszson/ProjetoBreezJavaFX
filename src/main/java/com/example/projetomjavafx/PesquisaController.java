package com.example.projetomjavafx;

import com.example.projetomjavafx.model.entities.Musica;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PesquisaController {

    private TableColumn albumCapa = new TableColumn("Capa");
    private TableColumn titulo = new TableColumn<>("Título");
    private TableColumn artista = new TableColumn<>("Artista");
    private TableColumn duracao = new TableColumn<>("Duração");
    private TableColumn avaliação = new TableColumn<>("Avaliação");
    
    @FXML
    private TextField campoPesquisa;

    protected void onPesquisaAction(){

    }

}
