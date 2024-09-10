package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.AvaliaMsc;
import com.example.projetomjavafx.util.Alerta;
import com.example.projetomjavafx.util.Restricoes;
import com.example.projetomjavafx.util.SessaoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.controlsfx.control.Rating;

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
    protected void onSaveClick(){
        AvaliaMsc aMsc = new AvaliaMsc();

        if (comentario.getText().length() > 500){
            Alerta.exibirAlerta("Comentário inválido",null, "Tamanho máximo de 500 caracteres!", Alert.AlertType.INFORMATION);
        }else {
            aMsc.setComentario(comentario.getText());
        }

        // Tratar não inserir em avaliação já existente
        // ser apenas 5 estrelas
        // !!!!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!
        aMsc.setFk_id_usuario(4); // Diego
        aMsc.setFk_id_musica(79);
        aMsc.setNota(avaliacao.getMax());
        System.out.println(aMsc.toString());

        DaoFactory.createAvaliaMscDao().inserirAvaliacaoMusica(aMsc);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Restricoes.COMENTARIO(comentario);

    }
}
