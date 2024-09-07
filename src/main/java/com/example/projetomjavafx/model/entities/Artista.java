package com.example.projetomjavafx.model.entities;

import java.util.Arrays;
import java.util.Objects;

public class Artista {

    private int id;

    private String nome;
    private String senha;
    private String genero;
    private byte[] foto;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artista artista = (Artista) o;
        return id == artista.id && Objects.equals(nome, artista.nome) && Objects.equals(senha, artista.senha) && Objects.equals(genero, artista.genero) && Objects.deepEquals(foto, artista.foto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, senha, genero, Arrays.hashCode(foto));
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", genero='" + genero + '\'' +
                ", foto=" + Arrays.toString(foto) +
                '}';
    }
}
