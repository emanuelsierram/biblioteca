package com.ceiba.biblioteca.servicio;

import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.entidad.Prestamo;
import com.ceiba.biblioteca.modelo.repositorio.RepositorioPrestamo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


public class ServicioCrearPrestamo {

    private static final int USUARIO_INVITADO = 3;
    private static final String USUARIO_CON_IDENTIFICACION = "El usuario con identificación ";
    private static final String TIENE_UN_LIBRO_PRESTADO_=" ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";


    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioCrearPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    @Transactional
    public Map<String, Object> crear(Prestamo prestamo) {
        if(prestamo.getTipoUsuario()==USUARIO_INVITADO){
            validarSiTieneLibrosPrestados(prestamo);
        }
        return repositorioPrestamo.crear(prestamo);
    }

   public void validarSiTieneLibrosPrestados(Prestamo prestamo) {
       boolean existe = this.repositorioPrestamo.existe(prestamo.getIdentificacionUsuario());
       if(existe)
            throw new RuntimeException(USUARIO_CON_IDENTIFICACION +prestamo.getIdentificacionUsuario()  + TIENE_UN_LIBRO_PRESTADO_);
   }



}