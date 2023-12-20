package com.example.peliculon.controladores;

import com.example.peliculon.modelos.Comentario;
import com.example.peliculon.modelos.Pelicula;
import com.example.peliculon.repositorios.RepositorioComentarios;
import com.example.peliculon.servicios.ServicioComentarios;
import com.example.peliculon.servicios.ServicioPeliculas;
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
    ServicioPeliculas servicioPeliculas;
    @Autowired
    ServicioComentarios servicioComentarios;

    @GetMapping("/")
    public String inicio(Model model){
        //recupera todas las peliculas
        ArrayList<Pelicula> cartelera=servicioPeliculas.findAll();
        model.addAttribute("cartelera", cartelera);

        //recuperar ultimos 3 comentarios
        model.addAttribute("ultimoscomentarios", servicioComentarios.find3());

        return "index";
    }

    //Esta es la dirección que tengo que escribir en el navegador
    //Esta es la dirección que tengo que escribir en el enlace de index.html
    //Lo que sale en el <a th:href="/pelicula/{id}>
    @GetMapping("/pelicula/{id}")
    public String pelicula(@PathVariable long id, Model model){

        Pelicula p=servicioPeliculas.findById(id);
        //El nombre de "pelicula" es el que voy a usar en la vista detalle.html
        model.addAttribute("p", p);

        model.addAttribute("comentarios", servicioComentarios.findByPelicula(p));
        model.addAttribute("nuevoComentario", new Comentario());


        //El nombre que pongo en el return es el que tendrá el archivo .html
        return "detalle";
    }

    @PostMapping("/comentario/add")
    public String guardarComentario(@ModelAttribute("nuevoComentario") Comentario comentario,
                                    @RequestParam long idPelicula){
        comentario.setFecha(LocalDate.now());
        //"Localizo" la película que tiene el id que me han pasado en el campo hidden del formulario
        Pelicula pelicula=servicioPeliculas.findById(idPelicula);
        //Al comentario le asigno la película
        comentario.setPelicula(pelicula);
        servicioComentarios.save(comentario);
        return "redirect:/pelicula/" + comentario.getPelicula().getId();
    }
}