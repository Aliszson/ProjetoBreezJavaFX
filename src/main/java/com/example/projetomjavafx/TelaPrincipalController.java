package com.example.projetomjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class TelaPrincipalController {
    @FXML
    private AnchorPane moldeAlbum;
    @FXML
    private FlowPane exbicaoAlbuns;
    @FXML
    private Button acao;

    @FXML
    protected void teste(){
        exbicaoAlbuns.getChildren().add(moldeAlbum);
    }
}
