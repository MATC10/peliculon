package com.example.peliculon.controladores;

import com.example.peliculon.modelos.Pelicula;
import com.example.peliculon.repositorios.RepositorioPeliculas;
import com.example.peliculon.servicios.ServicioComentarios;
import com.example.peliculon.servicios.ServicioPeliculas;
import com.example.peliculon.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class Crud {
    //Para modificar películas necesito el Repositorio de películas
    @Autowired
    ServicioPeliculas servicioPeliculas;
    @Autowired
    ServicioComentarios servicioComentarios;

    @Autowired
    public StorageService storageService;

    //Este endpoint/url nos muestra la lista con todas las películas
    @GetMapping("/crud")
    public String listadoPeliculas(Model model){
        //El nombre de "peliculas" es el que voy a utilizar en la plantilla
        model.addAttribute("peliculas", servicioPeliculas.findAll());
        return "crud";
    }
    //Muestra el formulario para añadir películas, AÚN NO LA AÑADE
    @GetMapping("/crud/add")
    public String addPelicula(Model model){
        //formPelicula es el objeto que voy a usar en la plantilla
        model.addAttribute("formPelicula", new Pelicula());
        return "form_add";
    }
    //Esta es la URL que aparece en el th:action del formulario para añadir películas
    //Lo que aparece en el ModelAttribute es lo mismo del th:object del formulario
    @PostMapping("/crud/save")
    public String guardarPelicula(@ModelAttribute("formPelicula") Pelicula nuevaPelicula,
                                  @RequestParam("file")MultipartFile file) {

        if (!file.isEmpty()) {
            String imagen = storageService.store(file, nuevaPelicula.getTitulo());
            System.out.println("La imagen a guardar es : " + imagen);
            nuevaPelicula.setImagen(imagen);
            /*ES LO MISMO QUE HACER ESTO en lugar de el setImagen
            nuevaPelicula.setImagen(MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());*/
        }

        servicioPeliculas.save(nuevaPelicula);
        return "redirect:/crud/add";
    }

    @GetMapping("/crud/update/{id}")
    public String muestraPelicula(@PathVariable long id, Model model){
        Pelicula p= servicioPeliculas.findById(id);
        //El nombre del objeto debe ser el mismo que en el GetMapping de añadir
        //Y el mismo que en el th:object del formulario
        model.addAttribute("formPelicula", p);
        return "form_add";
    }

    @PostMapping("/crud/modificar")
    public String modificarPelicula(@ModelAttribute("formPelicula") Pelicula p,
                                    @RequestParam("file")MultipartFile file){


        if (!file.isEmpty()) {
            String imagen = storageService.store(file, p.getTitulo());
            System.out.println("La imagen a guardar es : " + imagen);
            p.setImagen(MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());
        }


        servicioPeliculas.save(p);
        return "redirect:/crud";
    }

    @GetMapping("/crud/delete/{id}")
    public String borrarPelicula(@PathVariable long id, Model model){
        //SE PUEDE HACER AQUÍ UN BUCLE PARA BORRAR TODOS LOS COMENTARIOS (MARCARLOS COMO BORRADOS)
        servicioPeliculas.deleteById(id);
        return "redirect:/crud";
    }


}
