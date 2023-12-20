package com.example.peliculon.modelos;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String sinopsis;

    //localdate para las fechas, y localtipo para fecha y hora, para una aplicación mundial usamos: zonedtime
    //@Temporal(TemporalType.DATE)  almacena año,mes,día (sin hora)
    //
    @DateTimeFormat(pattern = "dd/MM/yyyy") //esto formatea la fecha
    private LocalDate fecha;

    private String nacionalidad;

    private String imagen;

    private String trailer;

//aquí funciona si pongo List en lugar de ArrayList
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL) //si borras la pelicula se borran sus comentarios
    private List<Comentario> comentarios;

}
