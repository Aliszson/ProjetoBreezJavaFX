package com.example.projetomjavafx.model.entities;

import java.util.Objects;

public class AvaliaAlb {
    private int id;
    private float nota;
    private String comentario;
    private int fk_id_usuario;
    private int fk_id_album;

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
        AvaliaAlb avaliaAlb = (AvaliaAlb) o;
        return id == avaliaAlb.id && Float.compare(nota, avaliaAlb.nota) == 0 && fk_id_usuario == avaliaAlb.fk_id_usuario && fk_id_album == avaliaAlb.fk_id_album && Objects.equals(comentario, avaliaAlb.comentario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nota, comentario, fk_id_usuario, fk_id_album);
    }

    @Override
    public String toString() {
        return "AvaliaAlb{" +
                "id=" + id +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                ", fk_id_usuario=" + fk_id_usuario +
                ", fk_id_album=" + fk_id_album +
                '}';
    }
}
