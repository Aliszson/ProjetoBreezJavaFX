package com.example.projetomjavafx;

import com.example.projetomjavafx.model.dao.DaoFactory;
import com.example.projetomjavafx.model.entities.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistroController {
    @FXML
    private TextField nome;
    @FXML
    private TextField senha;
    @FXML
    private TextField bio;
    @FXML
    private TextField genero;
    @FXML
    private Button registrar;

    @FXML
    void onRegistrarClick(){
        Usuario u = new Usuario();
        u.setNome(nome.getText());
        u.setSenha(senha.getText());
        u.setBio(bio.getText());
        u.setGenero1(genero.getText());
        DaoFactory.createUsuarioDao().inserirUsuario(u);

    }


}
