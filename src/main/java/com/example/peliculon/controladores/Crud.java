package com.example.peliculon.controladores;

import com.example.peliculon.modelos.Pelicula;
import com.example.peliculon.repositorios.RepositorioPeliculas;
import com.example.peliculon.servicios.ServicioPeliculas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Crud {
    //Para modificar películas necesito el repositorio de películas
    @Autowired
    ServicioPeliculas servicioPeliculas;

    //Este endpoint/url nos muestra la lista con todas las peliculas
    @GetMapping("/crud")
    public String listadoPeliculas(Model model) {

        //El nombre de "peliculas" es el que voy a usar en la plantilla
        model.addAttribute("peliculas", servicioPeliculas.findAll());
        return "crud";
    }

    //Muestra el formulario par añadir películas, AÚN NO LAS AÑADE
    @GetMapping("/crud/add")
    public String addPelicula(Model model){
        //formPelicula es el objeto que voy a usar en la plantilla
        model.addAttribute("formPelicula", new Pelicula());
        return "form_add";
    }

    //Esta es la URL que aparece en el th:action del formulario par añadir películas
    //con el ModelAttribute estoy recibiendo la información del formulario
    //Lo que aparece en el ModelAttribute es lo mismo del th:object del formulario
    @PostMapping("/crud/save")
    public String guardarPelicula(@ModelAttribute("formPregunta") Pelicula nuevaPelicula){
        servicioPeliculas.save(nuevaPelicula);
        return "redirect:/crud/add";
    }

    @GetMapping("/crud/update/{id}")
    public String mostrarPelicula(@PathVariable long id, Model model){
        Pelicula p = servicioPeliculas.findById(id);
        //El nombre del objeto debe ser el mismo que en el GetMappin de añadir
        //Y el mismo que en el th:object del formulario
        model.addAttribute("formPelicula", p);
        return "form_add";
    }

    @PostMapping("/crud/modificar")
    public String modificarPelicula(@ModelAttribute("formPelicula") Pelicula p){
        servicioPeliculas.save(p);
        return "redirect:/crud";
    }

    @GetMapping("/crud/delete/{id}")
    public String borrarPelicula(@PathVariable long id, Model model){
        servicioPeliculas.deleteById(id);
        return "redirect:/crud";
    }

}
