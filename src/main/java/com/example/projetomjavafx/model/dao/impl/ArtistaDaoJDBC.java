package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.ArtistaDao;
import com.example.projetomjavafx.model.entities.Artista;
import com.example.projetomjavafx.model.entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArtistaDaoJDBC implements ArtistaDao {
    private Connection c;

    public ArtistaDaoJDBC(Connection c) {
        this.c = c;

    }

    public void inserirArtista(Artista a) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("insert into artista(nome, senha, genero, foto) values (?,?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getNome());
            st.setString(2, a.getSenha());
            st.setString(3, a.getGenero());
            st.setBytes(4, a.getFoto());
            int l = st.executeUpdate();

            if (l > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    a.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }


    }

    public void atualizarNomeArtista(Artista a) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("update artista set nome = ? where id_artista = ?");
            st.setString(1, a.getNome());
            st.setInt(2, a.getId());
            int c = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }

    }

    public void atualizarSenhaArtista(Artista a) {
        PreparedStatement st = null;

        try {
            st = c.prepareStatement("update artista set senha=? where id_artista=?");
            st.setString(1, a.getSenha());
            st.setInt(2, a.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    public void atualizarGeneroArtista(Artista a) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("update artista set genero = ? where id_artista = ?");
            st.setString(1, a.getGenero());
            st.setInt(2, a.getId());
            int c = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }

    }

    public void deletarPorIdArtista(int id) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("delete from artista where id_artista = ?");
            st.setInt(1, id);
            int c = st.executeUpdate();

            if (c > 0) {
                System.out.println("Artista deletado!");
            } else {
                System.out.println("Artista inexistente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void atualizarFotoArtista(Artista a) {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("update artista set foto=? where id_artista=?");
            st.setBytes(1, a.getFoto());
            st.setInt(2, a.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    public Artista procurarPorIdArtista(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = c.prepareStatement("select id_artista, nome, genero, foto from artista where id_artista = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Artista a = new Artista();
                a.setId(rs.getInt("id_artista"));
                a.setNome(rs.getString("nome"));
                a.setGenero(rs.getString("genero"));
                a.setFoto(rs.getBytes("foto"));
                return a;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;

    }

    public List<Artista> procurarTodosArtista() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = c.prepareStatement("select id_artista, nome from artista");
            rs = st.executeQuery();
            List<Artista> lista = new ArrayList<>();

            while (rs.next()) {
                Artista a = new Artista();
                a.setNome(rs.getString("nome"));
                a.setId(rs.getInt("id_artista"));
                lista.add(a);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    public Artista login(String nomeLogin, String senhaLogin) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = c.prepareStatement("select id_artista, nome, senha, genero, foto from artista where nome = ? and senha = ?");
            st.setString(1, nomeLogin);
            st.setString(2, senhaLogin);
            rs = st.executeQuery();
            if(rs.next()){
                Artista a = new Artista();
                a.setId(rs.getInt("id_artista"));
                a.setNome(rs.getString("nome"));
                a.setSenha(rs.getString("senha"));
                a.setGenero(rs.getString("genero"));
                a.setFoto(rs.getBytes("foto"));

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

}