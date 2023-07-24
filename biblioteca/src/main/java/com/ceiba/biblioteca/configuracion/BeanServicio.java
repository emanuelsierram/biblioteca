package com.ceiba.biblioteca.configuracion;

import com.ceiba.biblioteca.modelo.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.servicio.ServicioCrearPrestamo;
import com.ceiba.biblioteca.servicio.ServicioObtenerPrestamo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearPrestamo servicioCrearPrestamo(RepositorioPrestamo repositorioPrestamo){
        return new ServicioCrearPrestamo(repositorioPrestamo);
    }

    @Bean
    public ServicioObtenerPrestamo servicioObtenerPrestamo(RepositorioPrestamo repositorioPrestamo){
        return new ServicioObtenerPrestamo(repositorioPrestamo);
    }
}
