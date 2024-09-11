package com.example.projetomjavafx.model.entities;

import java.util.Arrays;
import java.util.Objects;

public class Album {

    private int id;
    private String nome;
    private String genero1;
    private String genero2;
    private byte[] capa;
    private int fk_id_artista;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero1() {
        return genero1;
    }

    public void setGenero1(String genero1) {
        this.genero1 = genero1;
    }

    public String getGenero2() {
        return genero2;
    }

    public void setGenero2(String genero2) {
        this.genero2 = genero2;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public int getFk_id_artista() {
        return fk_id_artista;
    }

    public void setFk_id_artista(int fk_id_artista) {
        this.fk_id_artista = fk_id_artista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id && fk_id_artista == album.fk_id_artista && Objects.equals(nome, album.nome) && Objects.equals(genero1, album.genero1) && Objects.equals(genero2, album.genero2) && Objects.deepEquals(capa, album.capa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, genero1, genero2, Arrays.hashCode(capa), fk_id_artista);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero1='" + genero1 + '\'' +
                ", genero2='" + genero2 + '\'' +
                ", capa=" + Arrays.toString(capa) +
                ", fk_id_artista=" + fk_id_artista +
                '}';
    }
}