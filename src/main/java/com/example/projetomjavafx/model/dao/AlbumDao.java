package com.example.projetomjavafx.model.dao;
import com.example.projetomjavafx.model.entities.Album;
import com.example.projetomjavafx.model.entities.Artista;

import java.sql.Time;
import java.util.List;

public interface AlbumDao {

    void inserirAlbum(Album a);
    void atualizarNomeAlbum(Album a);
    void atualizarGeneroUmAlbum(Album a);
    void atualizarGeneroDoisAlbum(Album a);
    void deletarPorIdAlbum(int id);
    Album procurarPorIdAlbum(int id);
    List<Album> procurarTodosAlbuns();
    double calcularMediaAvaliacoes(int idAlbum);
}
