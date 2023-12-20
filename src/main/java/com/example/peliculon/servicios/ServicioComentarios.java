package com.example.peliculon.servicios;

import com.example.peliculon.modelos.Comentario;
import com.example.peliculon.modelos.Pelicula;
import com.example.peliculon.repositorios.RepositorioComentarios;
import com.example.peliculon.repositorios.RepositorioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioComentarios {

    @Autowired
    RepositorioComentarios repositorioComentarios;

    public ArrayList<Comentario> findAll(){
        return repositorioComentarios.findAll();
    }

    public Comentario findById(long id){
        return repositorioComentarios.findById(id);
    }

    public Comentario save(Comentario comentario){
        //if(comentario.getContenido().equals(null))
        return repositorioComentarios.save(comentario);
    }

    public ArrayList<Comentario> findByPelicula(Pelicula pelicula){
        return repositorioComentarios.findByPelicula(pelicula);
    }

    public void delete (Comentario comentario){
        repositorioComentarios.delete(comentario);
    }

    public void deleteById(long id){
        repositorioComentarios.deleteById(id);
    }

    public ArrayList<Comentario> find3() {
        return repositorioComentarios.find3();
    }
}
