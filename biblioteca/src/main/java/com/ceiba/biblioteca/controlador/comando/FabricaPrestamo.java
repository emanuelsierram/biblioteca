package com.ceiba.biblioteca.controlador.comando;

import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.entidad.Prestamo;
import org.springframework.stereotype.Component;

@Component
public class FabricaPrestamo {



    public Prestamo crear(DtoPrestamo dtoPrestamo){
        return new Prestamo(
                dtoPrestamo.getId(),
                dtoPrestamo.getIsbn(),
                dtoPrestamo.getIdentificacionUsuario(),
                dtoPrestamo.getTipoUsuario()
        );
    }
}
