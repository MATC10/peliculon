package com.example.peliculon.controladores;

import com.example.peliculon.modelos.Comentario;
import com.example.peliculon.modelos.Pelicula;
import com.example.peliculon.repositorios.RepositorioComentarios;
import com.example.peliculon.repositorios.RepositorioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Principal {
    @Autowired
    RepositorioPeliculas repositorioPeliculas;

    @Autowired
    RepositorioComentarios repositorioComentarios;

    @GetMapping("/")
    public String inicio(Model model){

        ArrayList<Pelicula> cartelera = repositorioPeliculas.findAll(); //es mejor así porque podemos por ejemplo sacar la recaudación medio de la lista y luego pasarla en otro model
        model.addAttribute("cartelera", cartelera);

        //model.addAttribute("cartelera", cartelera = repositorioPeliculas.findAll());
        return "index";
    }

    //Esta es la dirección que tengo que escribir en el navegador
    //Esta es la dirección que tengo que escribir en el enlace del index.html
    //Lo que sale en el <a th:href="/pelicula/{id}>
    @GetMapping("/pelicula/{id}")
    public String pelicula (@PathVariable long id, Model model){
        Pelicula pelicula = repositorioPeliculas.findById(id);
        //El nombre de "pelicula" es el que voy a usar en la vista de detalle.html
        model.addAttribute("pelicula", pelicula);

        model.addAttribute("posteo", new Comentario());

        List<Comentario> comentarios = repositorioComentarios.findByPelicula(pelicula);

        model.addAttribute("comentarios", comentarios);

        //El nombre que pongo en el return es el que tendrá el archivo html
        return "detalle";
    }


    @PostMapping("/pelicula/{id}/submit")
    public String crearNuevoComentario(@PathVariable long id, @ModelAttribute("posteo") Comentario comentario){

        Pelicula pelicula = repositorioPeliculas.findById(id);
        comentario.setPelicula(pelicula);
        comentario.setFecha(LocalDate.now());
        repositorioComentarios.save(comentario);
        return "redirect:/pelicula/" + id;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }

}
