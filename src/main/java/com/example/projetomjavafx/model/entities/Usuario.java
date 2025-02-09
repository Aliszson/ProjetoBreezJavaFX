package com.example.projetomjavafx.model.entities;

import java.util.Arrays;
import java.util.Objects;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String bio;
    private String genero1;
    private String genero2;
    private String genero3;
    private byte[] foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public String getGenero3() {
        return genero3;
    }

    public void setGenero3(String genero3) {
        this.genero3 = genero3;
    }

    public byte[] getFoto() {
        return this.foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(nome, usuario.nome) && Objects.equals(senha, usuario.senha) && Objects.equals(bio, usuario.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, senha, bio);
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", bio='" + bio + '\'' +
                ", genero1='" + genero1 + '\'' +
                ", genero2='" + genero2 + '\'' +
                ", genero3='" + genero3 + '\'' +
                ", foto=" + Arrays.toString(foto) +
                '}';
    }

}
