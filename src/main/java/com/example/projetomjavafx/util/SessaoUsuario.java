package com.example.projetomjavafx.util;

import com.example.projetomjavafx.model.entities.Usuario;
// metodo monostate
public class SessaoUsuario {
    // estado compartilhado por todas as intâncias
    private static Usuario usuario;

    public SessaoUsuario(){
        // construtor vazio, já que o estado é compartilhado
    }

    // métodos para obter e definir o usuário, afetando todas as instancias

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        SessaoUsuario.usuario = usuario;
    }
}
