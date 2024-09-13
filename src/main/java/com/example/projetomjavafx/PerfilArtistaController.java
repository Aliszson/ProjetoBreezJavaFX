package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.util.Alerta;
import com.example.projetomjavafx.util.Restricoes;
import com.example.projetomjavafx.util.SessaoArtista;
import com.example.projetomjavafx.util.SessaoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

public class PerfilArtistaController implements Initializable {

    @FXML
    private Circle circuloFoto;
    @FXML
    private TextField nome;
    @FXML
    private Label genero;
    @FXML
    private ImageView voltar;
    @FXML
    private Button salvar;
    @FXML
    private Button sair;
    @FXML
    private Button deletar;


    SessaoArtista sessaoA = new SessaoArtista();
    File arquivo = null;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        setarDados();
        Restricoes.verificaNome(nome);
    }

    @FXML
    public void onVoltarClick(){
        try {
            Application.updateStageScene(ApplicationController.getStage(), "tela-principal-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    @FXML
    public void onSairClick(){
        sessaoA.setArtista(null);
        try {
            Application.updateStageScene(ApplicationController.getStage(), "application-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    @FXML
    public void onDeletarClick(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Exclusão de conta");
        alerta.setHeaderText("Você tem certeza que quer excluir sua conta?");
        Optional<ButtonType> resultado = alerta.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            DaoFactory.createArtistaDao().deletarPorIdArtista(sessaoA.getArtista().getId());
            try {
                Application.updateStageScene(ApplicationController.getStage(), "application-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }


    @FXML
    public void onSalvarClick() throws IOException {
        List<String> listaErros = new ArrayList<>();

        if(Objects.equals(nome.getText(), "")){
            listaErros.add("- O nome não pode ser vazio");
            nome.setText(sessaoA.getArtista().getNome());

        }else if(Objects.equals(nome.getText().toLowerCase(), sessaoA.getArtista().getNome().toLowerCase()))
        {   // nesse o caso usuário pode digitar o mesmo nome mas com diferença nas letras,
            // por exemplo nome atual -> cleber | nome digitado -> CleBer
            nome.setText(nome.getText());
            // sessaoU.getUsuario().setNome(nome.getText());
        }else if(DaoFactory.createArtistaDao().procurarTodosArtista().toString().toLowerCase().contains(nome.getText().toLowerCase())) {
            listaErros.add("- Este nome já esta sendo utilizado");
        }


        if(arquivo != null){
            byte[] bytesImagem =  Files.readAllBytes(arquivo.toPath());
            sessaoA.getArtista().setFoto(bytesImagem);
        }


        if(!listaErros.isEmpty()){
            String erros = String.join("\n", listaErros);
            Alerta.exibirAlerta("Erro", "Campo inválido", erros , Alert.AlertType.ERROR);
            return;
        }

        sessaoA.getArtista().setNome(nome.getText());
        DaoFactory.createArtistaDao().atualizarNomeArtista(sessaoA.getArtista());
        DaoFactory.createArtistaDao().atualizarFotoArtista(sessaoA.getArtista());
        Alerta.exibirAlerta(null, null, "Alterações salvas", Alert.AlertType.INFORMATION);

    }

    @FXML
    void onCirculoFotoClick(){
        FileChooser fc = new FileChooser();
        arquivo = fc.showOpenDialog(ApplicationController.getStage().getScene().getWindow()); // abre pra selecionar a imagem
        if(arquivo!=null){
            Image imagem = new Image(arquivo.toURI().toString());
            circuloFoto.setFill(new ImagePattern(imagem));
        }

    }

    public void setarDados() {
        Image imagem = new Image(new ByteArrayInputStream(sessaoA.getArtista().getFoto()));
        circuloFoto.setFill(new ImagePattern(imagem));
        nome.setText(sessaoA.getArtista().getNome());
        genero.setText(sessaoA.getArtista().getGenero());


    }
}