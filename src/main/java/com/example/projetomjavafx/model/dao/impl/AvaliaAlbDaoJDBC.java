package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.AvaliaAlbDao;
import com.example.projetomjavafx.model.entities.AvaliaAlb;

import java.sql.*;

public class AvaliaAlbDaoJDBC implements AvaliaAlbDao {
    private Connection conn;

    public AvaliaAlbDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void inserirAvaliacaoAlbum(AvaliaAlb ab) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("insert into avaliaAlb(nota, fk_id_usuario, fk_id_album) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setFloat(1, ab.getNota());
            st.setInt(2, ab.getFk_id_usuario());
            st.setInt(3, ab.getFk_id_album());
            int linha = st.executeUpdate();
            if (linha>0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    ab.setId(rs.getInt(1));
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
    public void atualizarNotaAlbum(AvaliaAlb ab) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update avaliaAlb set nota=? where id_avaliacao=?");
            st.setString(1, ab.getComentario());
            st.setInt(2, ab.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarComentarioAlbum(AvaliaAlb ab) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update avaliaAlb set comentario=? where id_avaliacao=?");
            st.setString(1, ab.getComentario());
            st.setInt(2,ab.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }
}
