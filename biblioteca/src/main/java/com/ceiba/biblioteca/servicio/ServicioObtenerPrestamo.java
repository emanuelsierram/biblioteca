package com.ceiba.biblioteca.servicio;

import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.repositorio.RepositorioPrestamo;

public class ServicioObtenerPrestamo {

    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioObtenerPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public DtoPrestamo obtener(Integer id){
        return repositorioPrestamo.obtenerPrestamo(id);
    }
}
