package com.example.peliculon.servicios;

import com.example.peliculon.modelos.Pelicula;
import com.example.peliculon.repositorios.RepositorioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioPeliculas {
    @Autowired
    RepositorioPeliculas repositorioPeliculas;

    public ArrayList<Pelicula> findAll(){
        return repositorioPeliculas.findAll();
    }

    public Pelicula findById(long id){
        return repositorioPeliculas.findById(id);
    }

    public Pelicula save(Pelicula pelicula){
        //if(pelicula.getNacionalidad().equals(null))
        return repositorioPeliculas.save(pelicula);
    }

    public ArrayList<Pelicula> findByNacionalidad(String nacionalidad){
        return repositorioPeliculas.findByNacionalidad(nacionalidad);
    }

    public void deleteById(long id){
       repositorioPeliculas.deleteById(id);
    }

}
