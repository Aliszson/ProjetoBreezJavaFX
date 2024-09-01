package com.example.projetomjavafx.model.dao;

import com.example.projetomjavafx.model.entities.AvaliaAlb;

public interface AvaliaAlbDao {
    void inserirAvaliacaoAlbum(AvaliaAlb ab);
    void atualizarNotaAlbum(AvaliaAlb ab);
    void atualizarComentarioAlbum(AvaliaAlb ab);
}
