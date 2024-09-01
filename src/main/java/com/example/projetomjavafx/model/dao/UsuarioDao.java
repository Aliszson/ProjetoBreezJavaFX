package com.example.projetomjavafx.model.dao;

import com.example.projetomjavafx.model.entities.Usuario;

import java.util.List;

public interface UsuarioDao {
    void inserirUsuario(Usuario u);
    void atualizarNomeUsuario(Usuario u);
    void atualizarSenhaUsuario(Usuario u);
    void atualizarBioUsuario(Usuario u);
    void deletarPorIdUsuario(int id);
    Usuario procurarPorIdUsuario(int id);
    List<Usuario> procurarTodosUsuario();
}
