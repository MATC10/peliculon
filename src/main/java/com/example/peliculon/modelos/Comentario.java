package com.example.peliculon.modelos;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    //con esta notación me aseguro que siempre sepa que es una fecha
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @ManyToOne
    private Pelicula pelicula;


}
