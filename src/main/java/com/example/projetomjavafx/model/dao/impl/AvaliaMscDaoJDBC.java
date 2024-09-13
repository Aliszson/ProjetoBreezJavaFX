package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.AvaliaMscDao;
import com.example.projetomjavafx.model.entities.AvaliaMsc;

import java.sql.*;

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
            st.setFloat(1, am.getNota());
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
            st.setString(1, am.getComentario());
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
}
