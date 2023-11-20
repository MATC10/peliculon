package com.example.peliculon.controladores;

import com.example.peliculon.modelos.Pelicula;
import com.example.peliculon.repositorios.RepositorioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Crud {
    @Autowired
    RepositorioPeliculas repositorioPeliculas;

    @GetMapping("/crud")
    public String listadoPeliculas(Model model) {

        //El nombre de "peliculas" es el que voy a usar en la plantilla
        model.addAttribute("peliculas", repositorioPeliculas.findAll());
        return "crud";
    }

    @GetMapping("/add")
        public String addPelicula(Model model){
        model.addAttribute("formPelicula", new Pelicula());
            return "form_add";
        }
    @PostMapping("/crud/save")
    public String guardarPelicula(@ModelAttribute("formPregunta") Pelicula nuevaPelicula){
        repositorioPeliculas.save(nuevaPelicula);
        return "redirect:/add";
    }


}
