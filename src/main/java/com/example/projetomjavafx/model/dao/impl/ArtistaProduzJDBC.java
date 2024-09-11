package com.example.projetomjavafx.model.dao.impl;

import com.example.projetomjavafx.db.DB;
import com.example.projetomjavafx.model.dao.ArtistaProduzDao;
import com.example.projetomjavafx.model.entities.Artista;
import com.example.projetomjavafx.model.entities.ArtistaProduz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaProduzJDBC implements ArtistaProduzDao {


    private Connection c;

    public ArtistaProduzJDBC(Connection c) {
        this.c = c;
    }

    @Override
    public void inserirProducao(ArtistaProduz a) {

        PreparedStatement st = null;
        try {
            st = c.prepareStatement("insert into artistaProduz(fk_id_artista, fk_id_album, fk_id_musica) values (?,?,?)");
            st.setInt(1, a.getFk_id_artista());
            st.setInt(2, a.getFk_id_album());
            st.setInt(3, a.getFk_id_musica());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public List<ArtistaProduz> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = c.prepareStatement("select fk_id_artista, fk_id_album, fk_id_musica from artistaProduz");
            rs = st.executeQuery();
            List<ArtistaProduz> lista = new ArrayList<>();

            while(rs.next()){
                ArtistaProduz a = new ArtistaProduz();
                a.setFk_id_artista(rs.getInt("fk_id_artista"));
                a.setFk_id_album(rs.getInt("fk_id_album"));
                a.setFk_id_musica(rs.getInt("fk_id_musica"));

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
