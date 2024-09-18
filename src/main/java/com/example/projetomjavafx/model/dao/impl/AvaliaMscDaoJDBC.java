package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.AvaliaMscDao;
import com.example.projetomjavafx.model.entities.AvaliaMsc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliaMscDaoJDBC implements AvaliaMscDao {
    private Connection conn;

    public AvaliaMscDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserirAvaliacaoMusica(AvaliaMsc am) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("insert into avaliaMsc(nota, comentario, fk_id_usuario, fk_id_musica) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, am.getNota());
            st.setString(2,am.getComentario());
            st.setInt(3, am.getFk_id_usuario());
            st.setInt(4, am.getFk_id_musica());
            int linha = st.executeUpdate();
            if (linha>0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    am.setId(rs.getInt(1));
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
    public void atualizarNotaMusica(AvaliaMsc am) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update avaliaMsc set nota=? where id_avaliacao=?");
            st.setInt(1, am.getNota());
            st.setInt(2, am.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarComentarioMusica(AvaliaMsc am) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update avaliaMsc set comentario=? where id_avaliacao=?");
            st.setString(1, am.getComentario());
            st.setInt(2,am.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public float calcularMediaPorMusica(int idMusica) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT AVG(nota) AS media FROM avaliamsc WHERE fk_id_musica = ?"
            );
            st.setInt(1, idMusica);
            rs = st.executeQuery();

            if (rs.next()) {
                return rs.getFloat("media");
            }

            return 0; // Se não houver avaliações
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public boolean avaliacaoExiste(int idUsuario, int idMusica) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select * from avaliaMsc where fk_id_usuario = ? and fk_id_musica = ?");
            st.setInt(1, idUsuario);
            st.setInt(2, idMusica);
            rs = st.executeQuery();

            // verifica se há resultados
            return rs.next();  // retorna true se a avaliação já existir
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
    @Override
    public List<List<Object>> carregarAvaliacoes(int usuarioId) {
        String sql = "SELECT " +
                "msc.titulo AS nomeMusica, " +
                "alb.nome AS nomeAlbum, " +
                "art.nome AS nomeArtista, " +
                "am.nota, " +
                "am.comentario " +
                "FROM avaliaMsc am " +
                "JOIN musica msc ON am.fk_id_musica = msc.id_musica " +
                "JOIN album alb ON msc.fk_id_album = alb.id_album " +
                "JOIN artista art ON alb.fk_id_artista = art.id_artista " +
                "WHERE am.fk_id_usuario = ?";

        List<List<Object>> avaliacoesData = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, usuarioId);
            rs = st.executeQuery();

            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                row.add(rs.getString("nomeMusica"));
                row.add(rs.getString("nomeAlbum"));
                row.add(rs.getString("nomeArtista"));
                row.add(rs.getString("comentario"));
                row.add(rs.getInt("nota"));
                avaliacoesData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return avaliacoesData;
    }
}

