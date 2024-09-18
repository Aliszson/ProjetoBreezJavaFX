package com.example.projetomjavafx.model.dao;

import com.example.projetomjavafx.model.entities.AvaliaMsc;

import java.util.List;

public interface AvaliaMscDao {
    void inserirAvaliacaoMusica(AvaliaMsc am);
    void atualizarNotaMusica(AvaliaMsc am);
    void atualizarComentarioMusica(AvaliaMsc am);
    float calcularMediaPorMusica(int idMusica);
    boolean avaliacaoExiste(int idUsuario, int idMusica);
    List<List<Object>> carregarAvaliacoes(int usuarioId);
}
