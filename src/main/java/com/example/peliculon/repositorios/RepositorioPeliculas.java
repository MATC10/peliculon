package com.example.peliculon.repositorios;


import com.example.peliculon.modelos.Comentario;
import com.example.peliculon.modelos.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Repository
public interface RepositorioPeliculas extends JpaRepository<Pelicula, Long> {
    public ArrayList<Pelicula> findAll();
    public Pelicula findById(long id);
    public Pelicula save(Pelicula pelicula);
    public ArrayList findByNacionalidad(String nacionalidad);
}

