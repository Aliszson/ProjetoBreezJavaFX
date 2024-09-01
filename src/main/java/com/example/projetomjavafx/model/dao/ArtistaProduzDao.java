package com.example.projetomjavafx.model.dao;

import com.example.projetomjavafx.model.entities.ArtistaProduz;
import java.util.List;


public interface ArtistaProduzDao {

    void inserirProducao(ArtistaProduz a);
    List <ArtistaProduz> listar();

}
