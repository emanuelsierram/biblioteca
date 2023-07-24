package com.ceiba.biblioteca.modelo.repositorio;

import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.entidad.Prestamo;

import java.util.Map;

public interface RepositorioPrestamo {

    Map<String, Object> crear(Prestamo prestamo);

    DtoPrestamo obtenerPrestamo(Integer id);

    boolean existe(String identificacion);
}
