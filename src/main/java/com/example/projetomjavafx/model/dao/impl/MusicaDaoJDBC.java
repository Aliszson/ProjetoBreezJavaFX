package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.MusicaDao;
import com.example.projetomjavafx.model.entities.Album;
import com.example.projetomjavafx.model.entities.Artista;
import com.example.projetomjavafx.model.entities.Musica;
import com.example.projetomjavafx.model.entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaDaoJDBC implements MusicaDao {

    private Connection conn;

    public MusicaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserirMusica(Musica m) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("insert into musica(titulo,letra,duracao,fk_id_album) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, m.getTitulo());
            st.setString(2, m.getLetra());
            st.setTime(3, m.getDuracao());
            st.setInt(4, m.getFk_id_album());
            int linha = st.executeUpdate();
            if (linha > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    m.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarTitulo(Musica m) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update musica set titulo=? where id_musica=?");
            st.setString(1, m.getTitulo());
            st.setInt(2, m.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarLetra(Musica m) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update musica set letra=? where id_musica=?");
            st.setString(1, m.getLetra());
            st.setInt(2, m.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarPorId(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("delete from musica where id_musica=?");
            st.setInt(1, id);
            int c = st.executeUpdate();

            if (c > 0) {
                System.out.println("Música deletada!");
            } else {
                System.out.println("Música inexistente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Musica procurarPorIdMusica(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select id_musica,titulo,letra,duracao,fk_id_album from musica where id_musica=?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Musica m = new Musica();
                m.setId(rs.getInt("id_musica"));
                m.setTitulo(rs.getString("titulo"));
                m.setLetra(rs.getString("letra"));
                m.setDuracao(rs.getTime("duracao"));
                m.setFk_id_album(rs.getInt("fk_id_album"));
                return m;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }

    @Override
    public List<Musica> procurarTodasMusica() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select id_musica,titulo,letra,duracao,fk_id_album from musica");
            rs = st.executeQuery();
            List<Musica> lista = new ArrayList<>();
            while (rs.next()) {
                Musica m = new Musica();
                m.setTitulo(rs.getString("titulo"));
                m.setId(rs.getInt("id_musica"));
                m.setLetra(rs.getString("letra"));
                m.setDuracao(rs.getTime("duracao"));
                m.setFk_id_album(rs.getInt("fk_id_album"));
                lista.add(m);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public Album procurarAlbumPorFk(int fk_id_album) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT id_album, nome, capa, fk_id_artista FROM album WHERE id_album = ?");
            st.setInt(1, fk_id_album);
            rs = st.executeQuery();

            if (rs.next()) {
                Album album = new Album();
                album.setId(rs.getInt("id_album"));
                album.setNome(rs.getString("nome"));
                album.setCapa(rs.getBytes("capa"));
                album.setFk_id_artista(rs.getInt("fk_id_artista"));
                return album;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null; // Retorna null se o álbum não for encontrado
    }

    public Artista procurarArtistaPorFk(int fkIdArtista) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT id_artista, nome FROM artista WHERE id_artista = ?");
            st.setInt(1, fkIdArtista);
            rs = st.executeQuery();
            if (rs.next()) {
                Artista artista = new Artista();
                artista.setId(rs.getInt("id_artista"));
                artista.setNome(rs.getString("nome"));
                return artista;
            }
            return null; // Se não encontrar o artista
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}

