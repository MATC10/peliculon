package com.example.peliculon.repositorios;

import com.example.peliculon.modelos.Comentario;
import com.example.peliculon.modelos.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioComentarios extends JpaRepository<Comentario, Long> {
    public ArrayList<Comentario> findAll();
    public Comentario findById(long id);
    public Comentario save(Comentario comentario);

    public ArrayList<Comentario> findByPelicula(Pelicula pelicula);
}
