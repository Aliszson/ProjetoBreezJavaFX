package com.example.projetomjavafx.model.dao;

import db.DB;
import model.dao.impl.*;
import com.example.projetomjavafx.model.entities.AvaliaMsc;

public class DaoFactory {
    public static AlbumDao createAlbumDao(){
        return new AlbumDaoJDBC(DB.getConnection());
    }
    public static ArtistaDao createArtistaDao(){
        return new ArtistaDaoJDBC(DB.getConnection());
    }
    public static ArtistaProduzDao createArtistaProduzDao(){
        return new ArtistaProduzJDBC(DB.getConnection());
    }
    public static AvaliaAlbDao createAvaliaAlbDao(){
        return new AvaliaAlbDaoJDBC(DB.getConnection());
    }
    public static AvaliaMscDao createAvaliaMscDao(){
        return new AvaliaMscDaoJDBC(DB.getConnection());
    }
    public static MusicaDao createMusicaDao(){
        return new MusicaDaoJDBC(DB.getConnection());
    }
    public static UsuarioDao createUsuarioDao(){
        return new UsuarioDaoJDBC(DB.getConnection());
    }
}
