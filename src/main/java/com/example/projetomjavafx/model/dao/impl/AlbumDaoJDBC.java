package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.AlbumDao;
import com.example.projetomjavafx.model.entities.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoJDBC implements AlbumDao {

    private Connection c;

    public AlbumDaoJDBC(Connection c) {
        this.c = c;
    }

    @Override
    public void inserirAlbum(Album a) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("insert into Album(nome, genero1, genero2, capa) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getNome());
            st.setString(2, a.getGenero1());
            st.setString(3, a.getGenero2());
            st.setBytes(4, a.getCapa());
            int l = st.executeUpdate();

            if(l > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    a.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarNomeAlbum(Album a) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("update album set nome = ? where id_album = ?");
            st.setString(1, a.getNome());
            st.setInt(2, a.getId());
            int c = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarGeneroUmAlbum(Album a) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("update Album set genero1 = ? where id_album = ?");
            st.setString(1, a.getGenero1());
            st.setInt(2, a.getId());
            int c = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarGeneroDoisAlbum(Album a) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("update Album set genero2 = ? where id_album = ?");
            st.setString(1, a.getGenero2());
            st.setInt(2, a.getId());
            int c = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarPorIdAlbum(int id) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("delete from Album where id_album = ?");
            st.setInt(1, id);
            int c = st.executeUpdate();

            if (c > 0){
                System.out.println("Álbum deletado!");
            }else {
                System.out.println("Álbum inexistente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Album procurarPorIdAlbum(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = c.prepareStatement("select id_album, nome from Album where id_album = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){
                Album a = new Album();
                a.setId(rs.getInt("id_album"));
                a.setNome(rs.getString("nome"));
                return a;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }

    @Override
    public List<Album> procurarTodosAlbuns() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = c.prepareStatement("select id_album, nome, genero1, capa from Album");
            rs = st.executeQuery();
            List<Album> lista = new ArrayList<>();

            while(rs.next()){
                Album a = new Album();
                a.setNome(rs.getString("nome"));
                a.setId(rs.getInt("id_album"));
                lista.add(a);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
