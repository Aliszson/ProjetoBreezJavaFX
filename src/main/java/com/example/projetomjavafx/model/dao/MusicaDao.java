package com.example.projetomjavafx.model.dao;

import com.example.projetomjavafx.model.entities.Musica;

public interface MusicaDao {
    void inserirMusica(Musica m);
    void atualizarTitulo(Musica m);
    void atualizarLetra(Musica m);
    void deletarPorId(int id);
    Musica procurarPorIdMusica(int id);
}
