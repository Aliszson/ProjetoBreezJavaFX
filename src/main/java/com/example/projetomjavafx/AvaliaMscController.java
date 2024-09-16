package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Album;
import com.example.projetomjavafx.model.entities.AvaliaMsc;
import com.example.projetomjavafx.model.entities.Musica;
import com.example.projetomjavafx.util.Alerta;
import com.example.projetomjavafx.util.Restricoes;
import com.example.projetomjavafx.util.SessaoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AvaliaMscController implements Initializable {
    @FXML
    private TextArea comentario;
    @FXML
    private Button salvarAvaliacao;
    @FXML
    private Rating avaliacao;
    @FXML
    private ImageView capaAlbum;
    @FXML
    private Text musicaTitulo;

    public int idMusica;
    public int fkAlbum;

    @FXML
    protected void onSaveClick(){
        SessaoUsuario sessaoUsuario = new SessaoUsuario();

        AvaliaMsc aMsc = new AvaliaMsc();

        aMsc.setNota((int)avaliacao.getRating());

        if (comentario.getText().length() > 500){
            Alerta.exibirAlerta("Comentário inválido",null, "Tamanho máximo de 500 caracteres!", Alert.AlertType.INFORMATION);
        }else {
            aMsc.setComentario(comentario.getText());
        }

        aMsc.setFk_id_usuario(sessaoUsuario.getUsuario().getId());
        aMsc.setFk_id_musica(idMusica);

        DaoFactory.createAvaliaMscDao().inserirAvaliacaoMusica(aMsc);
    }

    protected void capturarValores(){
        // pegando o imagem do album da música no banco
        Album a = new Album();
        a = DaoFactory.createAlbumDao().procurarPorIdAlbum(fkAlbum);

        Image imagem = new Image(new ByteArrayInputStream(a.getCapa()));
        capaAlbum.setImage(imagem);

        // pegando o título
        Musica m = DaoFactory.createMusicaDao().procurarPorIdMusica(idMusica);
        musicaTitulo.setText(m.getTitulo());
    }

    protected boolean avaliacaoExistente(){
        SessaoUsuario sessaoUsuario = new SessaoUsuario();
        if (DaoFactory.createAvaliaMscDao().avaliacaoExiste(sessaoUsuario.getUsuario().getId(), idMusica)) {
            return true;  // Interrompe o fluxo se a avaliação já existir
        }else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Restricoes.COMENTARIO(comentario);
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    public int getFkAlbum() {
        return fkAlbum;
    }

    public void setFkAlbum(int fkAlbum) {
        this.fkAlbum = fkAlbum;
    }
}