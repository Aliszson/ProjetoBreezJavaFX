package com.example.projetomjavafx.model.entities;

import java.util.Objects;

public class AvaliaMsc {
    private int id;
    private float nota;
    private String comentario;
    private int fk_id_usuario;
    private int fk_id_musica;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getFk_id_usuario() {
        return fk_id_usuario;
    }

    public void setFk_id_usuario(int fk_id_usuario) {
        this.fk_id_usuario = fk_id_usuario;
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
        AvaliaMsc avaliaMsc = (AvaliaMsc) o;
        return id == avaliaMsc.id && Float.compare(nota, avaliaMsc.nota) == 0 && fk_id_usuario == avaliaMsc.fk_id_usuario && fk_id_musica == avaliaMsc.fk_id_musica && Objects.equals(comentario, avaliaMsc.comentario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nota, comentario, fk_id_usuario, fk_id_musica);
    }

    @Override
    public String toString() {
        return "AvaliaMsc{" +
                "id=" + id +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                ", fk_id_usuario=" + fk_id_usuario +
                ", fk_id_musica=" + fk_id_musica +
                '}';
    }
}
