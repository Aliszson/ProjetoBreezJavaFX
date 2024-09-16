package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Usuario;
import com.example.projetomjavafx.util.Alerta;
import com.example.projetomjavafx.util.Restricoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.controlsfx.control.SearchableComboBox;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;


public class RegistroUsuarioController implements Initializable {

    @FXML
    private ImageView iconeVoltar;
    @FXML
    private TextField nome;
    @FXML
    private PasswordField senha;
    @FXML
    private TextArea bio;
    @FXML
    private SearchableComboBox<String> genero1;
    @FXML
    private SearchableComboBox<String> genero2;
    @FXML
    private SearchableComboBox<String> genero3;
    @FXML
    private ImageView foto;
    @FXML
    private Button registrar;
    @FXML
    private Circle circuloFoto;

    List<String> listaGeneros = generos();

    @FXML
    void onIconeVoltarClick() {
        try {
            Application.updateStageScene(ApplicationController.getStage(), "application-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Restricoes.verificaNome(nome);
        Restricoes.verificaSenha(senha);
        Restricoes.verificaBio(bio);
        circuloFoto.setVisible(false);

        genero1.getItems().addAll(listaGeneros);
        genero2.getItems().addAll(listaGeneros);
        genero3.getItems().addAll(listaGeneros);

    }

    File padrao = new File("src/main/resources/img/usuarioImg/fotoPerfilPadrao.png");
    File arquivo;

    @FXML
    void onFotoClick() {
        FileChooser fc = new FileChooser();
        arquivo = fc.showOpenDialog(ApplicationController.getStage().getScene().getWindow()); // abre pra selecionar a imagem
        if (arquivo != null) {
            foto.setVisible(false);
            circuloFoto.setVisible(true);
            Image imagem = new Image(arquivo.toURI().toString());
            circuloFoto.setFill(new ImagePattern(imagem));
        }
    }


    @FXML
    void onCirculoFotoClick() {
        FileChooser fc = new FileChooser();
        arquivo = fc.showOpenDialog(ApplicationController.getStage().getScene().getWindow()); // abre pra selecionar a imagem
        if (arquivo != null) {
            Image imagem = new Image(arquivo.toURI().toString());
            circuloFoto.setFill(new ImagePattern(imagem));
        }

    }

    @FXML
    private void onComboBoxClick() {
        // cria uma cópia da lista de gêneros para cada combobox
        List<String> generosVisiveis1 = new ArrayList<>(listaGeneros);
        List<String> generosVisiveis2 = new ArrayList<>(listaGeneros);
        List<String> generosVisiveis3 = new ArrayList<>(listaGeneros);

        // remove os gêneros já selecionados dos outros comboboxes
        if (genero1.getValue() != null) {
            generosVisiveis2.remove(genero1.getValue());
            generosVisiveis3.remove(genero1.getValue());
        }
        if (genero2.getValue() != null) {
            generosVisiveis1.remove(genero2.getValue());
            generosVisiveis3.remove(genero2.getValue());
        }
        if (genero3.getValue() != null) {
            generosVisiveis1.remove(genero3.getValue());
            generosVisiveis2.remove(genero3.getValue());
        }

        // atualiza os itens disponíveis em cada combobox
        genero1.setItems(FXCollections.observableArrayList(generosVisiveis1));
        genero2.setItems(FXCollections.observableArrayList(generosVisiveis2));
        genero3.setItems(FXCollections.observableArrayList(generosVisiveis3));

        // tenta manter o valor selecionado anteriormente, se ele ainda estiver disponível
        if (genero1.getValue() != null && generosVisiveis1.contains(genero1.getValue())) {
            genero1.setValue(genero1.getValue());
        } else {
            genero1.setValue(null); // se o valor não estiver disponível, desmarque o ComboBox
        }

        if (genero2.getValue() != null && generosVisiveis2.contains(genero2.getValue())) {
            genero2.setValue(genero2.getValue());
        } else {
            genero2.setValue(null);
        }

        if (genero3.getValue() != null && generosVisiveis3.contains(genero3.getValue())) {
            genero3.setValue(genero3.getValue());
        } else {
            genero3.setValue(null); 
        }
    }

    @FXML
    void onRegistrarClick() throws IOException {
        Usuario u = new Usuario();
        List<String> listaErros = new ArrayList<>();

        if (Objects.equals(nome.getText(), "") || Objects.equals(senha.getText(), "")) {
            listaErros.add("- Os campos de nome e senha não podem ser vazios.");

        } else if (DaoFactory.createUsuarioDao().procurarTodosUsuario().toString().toLowerCase().contains(nome.getText().toLowerCase())) {
            listaErros.add("- Nome de usuário já esta sendo utilizado");
        } else {
            u.setNome(nome.getText());
            u.setSenha(senha.getText());
        }

        if (Objects.equals(bio.getText(), "")){
            u.setBio(null);
        }else {
            u.setBio(bio.getText());
        }



        if (genero1.getValue() == null) {
            listaErros.add("- Você deve escolher um gênero musical no campo gênero favorito.");
        } else {
            u.setGenero1(genero1.getValue());
        }

        u.setGenero2(genero2.getValue());
        u.setGenero3(genero3.getValue());


        if (arquivo != null) {
            byte[] bytesImagem = Files.readAllBytes(arquivo.toPath());
            u.setFoto(bytesImagem);

        } else {
            byte[] bytesImagem = Files.readAllBytes(padrao.toPath());
            u.setFoto(bytesImagem);
        }

        if (!listaErros.isEmpty()) {
            String erros = String.join("\n", listaErros);
            Alerta.exibirAlerta("Erro", "Campo inválido", erros, Alert.AlertType.ERROR);
            return;
        }

        DaoFactory.createUsuarioDao().inserirUsuario(u);
        Alerta.exibirAlerta(null, null, "Registro realizado com sucesso!", Alert.AlertType.INFORMATION);
        limparCampos();

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

    public void limparCampos(){
        nome.clear();
        senha.clear();
        bio.clear();
        genero1.setValue(null);
        genero2.setValue(null);
        genero3.setValue(null);
        genero1.setPromptText("Nenhum");
        genero2.setPromptText("Nenhum");
        genero2.setPromptText("Nenhum");
        File perfilVazio = new File("src/main/resources/img/usuarioImg/perfilvazio.png");
        foto.setImage(new Image(perfilVazio.toURI().toString()));
        foto.setVisible(true);
        circuloFoto.setVisible(false);
        arquivo = null;
    }


}