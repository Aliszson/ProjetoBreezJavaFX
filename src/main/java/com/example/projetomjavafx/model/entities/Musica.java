package com.example.projetomjavafx.model.entities;

import java.util.Objects;

public class Musica {
    private int id;
    private String titulo;
    private String letra;
    private float duracao;
    private int fk_id_album;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

    public int getFk_id_album() {
        return fk_id_album;
    }

    public void setFk_id_album(int fk_id_album) {
        this.fk_id_album = fk_id_album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musica musica = (Musica) o;
        return id == musica.id && Float.compare(duracao, musica.duracao) == 0 && fk_id_album == musica.fk_id_album && Objects.equals(titulo, musica.titulo) && Objects.equals(letra, musica.letra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, letra, duracao, fk_id_album);
    }

    @Override
    public String toString() {
        return "Musica{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", letra='" + letra + '\'' +
                ", duracao=" + duracao +
                ", fk_id_album=" + fk_id_album +
                '}';
    }
}
