package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.UsuarioDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.projetomjavafx.model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao  {

    private Connection conn;

    public UsuarioDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserirUsuario(Usuario u) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("insert into Usuario(nome,senha,bio, genero1, genero2, genero3, foto) values(?,?,?,?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, u.getNome());
            st.setString(2, u.getSenha());
            st.setString(3, u.getBio());
            st.setString(4, u.getGenero1());

            // Gêneros preferidos  2 e 3 são opcionais
            if(u.getGenero2() != null){
                st.setString(5, u.getGenero2());
            }else{
                st.setNull(5, java.sql.Types.VARCHAR);
            }

            if(u.getGenero3() != null){
                st.setString(6, u.getGenero3());
            }else{
                st.setNull(6, java.sql.Types.VARCHAR);
            }
            st.setBytes(7, u.getFoto());
            int linha = st.executeUpdate();
            if (linha>0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    u.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public void atualizarNomeUsuario(Usuario u) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update Usuario set nome=? where id_usuario=?");
            st.setString(1, u.getNome());
            st.setInt(2,u.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarSenhaUsuario(Usuario u) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("update Usuario set senha=? where id_usuario=?");
            st.setString(1, u.getSenha());
            st.setInt(2,u.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarBioUsuario(Usuario u) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("update Usuario set bio=? where id_usuario=?");
            st.setString(1, u.getBio());
            st.setInt(2,u.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarPorIdUsuario(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("delete from Usuario where id_usuario=?");
            st.setInt(1,id);
            int c = st.executeUpdate();

            if (c > 0){
                System.out.println("Usuário deletado!");
            }else {
                System.out.println("Usuário inexistente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Usuario procurarPorIdUsuario(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select id_usuario,nome,senha,bio,genero1,genero2,genero3,foto from Usuario where id_usuario=?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if (rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setBio(rs.getString("bio"));
                u.setGenero1(rs.getString("genero1"));
                u.setGenero2(rs.getString("genero2"));
                u.setGenero3(rs.getString("genero3"));
                u.setFoto(rs.getBytes("foto"));

                return u;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }

    @Override
    public List<Usuario> procurarTodosUsuario() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select id_usuario,nome from Usuario");
            rs = st.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()){
                Usuario u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setId(rs.getInt("id_usuario"));
                lista.add(u);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
