package com.example.peliculon;

import com.example.peliculon.modelos.Comentario;
import com.example.peliculon.modelos.Pelicula;
import com.example.peliculon.repositorios.RepositorioComentarios;
import com.example.peliculon.repositorios.RepositorioPeliculas;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

@SpringBootApplication
public class PeliculonApplication {
	@Autowired
	RepositorioPeliculas repositorioPeliculas;
	@Autowired
	RepositorioComentarios repositorioComentarios;

	public static void main(String[] args) {

/* Abrimos el XAMP con el proyecto
		String commnad = "c:\\xamp\\mysql\\bin\\mysql.exe";
		try {
			Process process = Runtime.getRuntime().exec(commnad);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

 */

		SpringApplication.run(PeliculonApplication.class, args);
	}

//clase para el Faker (Faker es para meter datos de relleno en la bbdd)
	public class FilmafiinityApplication{


//se puede hacer en otra clase aparte y pondremos otra notación en lugar de @Bean ?
		@Bean
		CommandLineRunner ponPeliculas(){
			return args -> {
//buscamos dependencias de faker y las ponemos en el pom (¿reiniciar el intellij?)
				Faker faker = new Faker(new Locale("es-ES"));
				if(repositorioPeliculas.findAll().size()<5) {
					for (int i = 0; i < 5; i++) {
						Pelicula p = new Pelicula();
						p.setTitulo(faker.leagueOfLegends().champion());
						p.setSinopsis(faker.leagueOfLegends().summonerSpell());
						p.setFecha(LocalDate.now());
						p.setNacionalidad(faker.leagueOfLegends().location());
						p.setTrailer("https://www.youtube.com/embed/f9W1l7E5bHg?si=pWfYzzLUJbvo6hJ6");
						repositorioPeliculas.save(p);

						for(int ii = 0; ii < 3; ii++){
							Comentario c = new Comentario();
							c.setTitulo(faker.backToTheFuture().character());
							c.setContenido(faker.backToTheFuture().quote());
							c.setFecha(LocalDate.now());
							c.setPelicula(p);

							repositorioComentarios.save(c);
						}
					}
				}
			};
		}


	}

}
