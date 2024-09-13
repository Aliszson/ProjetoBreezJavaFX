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
            st = c.prepareStatement("insert into album(nome, genero1, genero2, capa, fk_id_artista) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getNome());
            st.setString(2, a.getGenero1());
            st.setString(3, a.getGenero2());
            st.setBytes(4, a.getCapa());
            st.setInt(5, a.getFk_id_artista());
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
            st = c.prepareStatement("update album set genero1 = ? where id_album = ?");
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
            st = c.prepareStatement("update album set genero2 = ? where id_album = ?");
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
            st = c.prepareStatement("delete from album where id_album = ?");
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
            st = c.prepareStatement("select id_album, nome from album where id_album = ?");
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
            st = c.prepareStatement("select id_album, nome, genero1, genero2, capa, fk_id_artista from album");
            rs = st.executeQuery();
            List<Album> lista = new ArrayList<>();

            while(rs.next()){
                Album a = new Album();
                a.setNome(rs.getString("nome"));
                a.setId(rs.getInt("id_album"));
                a.setGenero1(rs.getString("genero1"));
                a.setGenero1(rs.getString("genero2"));
                a.setCapa(rs.getBytes("capa"));
                a.setFk_id_artista(rs.getInt("fk_id_artista"));
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

    @Override
    public double calcularMediaAvaliacoes(int idAlbum) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = c.prepareStatement(
                    "SELECT AVG(a.nota) AS media " +
                            "FROM avaliamsc a " +
                            "JOIN musica m ON a.fk_id_musica = m.id_musica " +
                            "WHERE m.fk_id_album = ?"
            );
            st.setInt(1, idAlbum);
            rs = st.executeQuery();

            if (rs.next()) {
                return rs.getDouble("media");
            }

            return 0.0; // Caso não haja avaliações, retorna 0
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
