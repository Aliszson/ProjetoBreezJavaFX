package com.example.projetomjavafx.model.dao;

import com.example.projetomjavafx.model.entities.AvaliaMsc;

public interface AvaliaMscDao {
    void inserirAvaliacaoMusica(AvaliaMsc am);
    void atualizarNotaMusica(AvaliaMsc am);
    void atualizarComentarioMusica(AvaliaMsc am);
}
