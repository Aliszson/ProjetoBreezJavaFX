package com.example.projetomjavafx.model.dao;

import com.example.projetomjavafx.model.entities.Album;
import com.example.projetomjavafx.model.entities.Artista;
import com.example.projetomjavafx.model.entities.Musica;

import java.util.List;

public interface MusicaDao {
    void inserirMusica(Musica m);
    void atualizarTitulo(Musica m);
    void atualizarLetra(Musica m);
    void deletarPorId(int id);
    Musica procurarPorIdMusica(int id);
    List<Musica> procurarTodasMusica();
    Album procurarAlbumPorFk(int fk_id_album);
    Artista procurarArtistaPorFk(int fkIdArtista);
}
