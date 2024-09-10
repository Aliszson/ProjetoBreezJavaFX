package com.example.projetomjavafx.util;

import com.example.projetomjavafx.model.entities.Artista;

// metodo monostate

public class SessaoArtista {
    // estado compartilhado por todas as intâncias
    private static Artista artista;

    public SessaoArtista(){
        // construtor vazio, já que o estado é compartilhado
    }

    // métodos para obter e definir o usuário, afetando todas as instancias
    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        SessaoArtista.artista = artista;
    }
}
