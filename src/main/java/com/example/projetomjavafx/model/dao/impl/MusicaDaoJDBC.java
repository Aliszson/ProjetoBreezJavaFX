package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.MusicaDao;
import com.example.projetomjavafx.model.entities.Musica;
import com.example.projetomjavafx.model.entities.Usuario;

import java.sql.*;

public class MusicaDaoJDBC implements MusicaDao {

    private Connection conn;

    public MusicaDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserirMusica(Musica m) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("insert into musica(titulo,letra,duracao,fk_id_album) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, m.getTitulo());
            st.setString(2, m.getLetra());
            st.setFloat(3, m.getDuracao());
            st.setInt(4, m.getFk_id_album());
            int linha = st.executeUpdate();
            if (linha>0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    m.setId(rs.getInt(1));
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
    public void atualizarTitulo(Musica m) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update musica set titulo=? where id_musica=?");
            st.setString(1, m.getTitulo());
            st.setInt(2,m.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarLetra(Musica m) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update musica set letra=? where id_musica=?");
            st.setString(1, m.getLetra());
            st.setInt(2,m.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarPorId(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("delete from musica where id_musica=?");
            st.setInt(1,id);
            int c = st.executeUpdate();

            if (c > 0){
                System.out.println("Música deletada!");
            }else {
                System.out.println("Música inexistente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Musica procurarPorIdMusica(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select id_musica,titulo,letra,duracao,fk_id_album from musica where id_musica=?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if (rs.next()){
                Musica m = new Musica();
                m.setId(rs.getInt("id_musica"));
                m.setTitulo(rs.getString("titulo"));
                m.setLetra(rs.getString("letra"));
                m.setDuracao(rs.getFloat("duracao"));
                m.setFk_id_album(rs.getInt("fk_id_album"));
                return m;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }
}
