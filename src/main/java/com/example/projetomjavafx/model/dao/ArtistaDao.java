package com.example.projetomjavafx.model.dao;
import com.example.projetomjavafx.model.entities.Artista;
import java.util.List;

public interface ArtistaDao {

    void inserirArtista(Artista a);
    void atualizarArtista(Artista a);
    void atualizarGeneroArtista(Artista a);
    void deletarPorIdArtista(int id);
    Artista procurarPorIdArtista(int id);
    List<Artista> procurarTodosArtista();
}
