package com.example.projetomjavafx.model.entities;

import java.util.Objects;

public class ArtistaProduz
{
    private int fk_id_artista;
    private int fk_id_album;
    private int fk_id_musica;

    public int getFk_id_artista() {
        return fk_id_artista;
    }

    public void setFk_id_artista(int fk_id_artista) {
        this.fk_id_artista = fk_id_artista;
    }

    public int getFk_id_album() {
        return fk_id_album;
    }

    public void setFk_id_album(int fk_id_album) {
        this.fk_id_album = fk_id_album;
    }

    public int getFk_id_musica() {
        return fk_id_musica;
    }

    public void setFk_id_musica(int fk_id_musica) {
        this.fk_id_musica = fk_id_musica;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistaProduz that = (ArtistaProduz) o;
        return fk_id_artista == that.fk_id_artista && fk_id_album == that.fk_id_album && fk_id_musica == that.fk_id_musica;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fk_id_artista, fk_id_album, fk_id_musica);
    }

    @Override
    public String toString() {
        return "AristaProduz{" +
                "fk_id_artista=" + fk_id_artista +
                ", fk_id_album=" + fk_id_album +
                ", fk_id_musica=" + fk_id_musica +
                '}';
    }
}

