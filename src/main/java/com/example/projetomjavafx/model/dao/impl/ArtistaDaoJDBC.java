package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.ArtistaDao;
import com.example.projetomjavafx.model.entities.Artista;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArtistaDaoJDBC implements ArtistaDao
{
    private Connection c;

    public ArtistaDaoJDBC(Connection c) {
        this.c = c;

    }

    public void inserirArtista(Artista a)
    {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("insert into Artista(nome, genero) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getNome());
            st.setString(2, a.getGenero());
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
    public void atualizarArtista(Artista a){
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("update Artista set nome = ? where id_artista = ?");
            st.setString(1, a.getNome());
            st.setInt(2, a.getId());
            int c = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }

    }

    public void atualizarGeneroArtista(Artista a){
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("update Artista set genero = ? where id_artista = ?");
            st.setString(1, a.getGenero());
            st.setInt(2, a.getId());
            int c = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(st);
        }

    }
    public void deletarPorIdArtista(int id){
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("delete from Artista where id_artista = ?");
            st.setInt(1, id);
            int c = st.executeUpdate();

            if (c > 0){
                System.out.println("Artista deletado!");
            }else {
                System.out.println("Artista inexistente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(st);
        }

    }

    public Artista procurarPorIdArtista(int id){
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = c.prepareStatement("select id_artista, nome from Artista where id_artista = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){
                Artista a = new Artista();
                a.setId(rs.getInt("id_artista"));
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
    public List<Artista> procurarTodosArtista(){
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = c.prepareStatement("select id_artista, nome from Artista");
            rs = st.executeQuery();
            List<Artista> lista = new ArrayList<>();

            while(rs.next()){
                Artista a = new Artista();
                a.setNome(rs.getString("nome"));
                a.setId(rs.getInt("id_artista"));
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