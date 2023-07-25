package com.ceiba.biblioteca.controlador.comando;

import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.entidad.Prestamo;
import com.ceiba.biblioteca.servicio.ServicioCrearPrestamo;
import com.ceiba.biblioteca.servicio.ServicioObtenerPrestamo;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class ManejadorCrearPrestamo {

    ServicioCrearPrestamo servicioCrearPrestamo;
    FabricaPrestamo fabricaPrestamo;

    public ManejadorCrearPrestamo(ServicioCrearPrestamo servicioCrearPrestamo,FabricaPrestamo fabricaPrestamo){
        this.servicioCrearPrestamo=servicioCrearPrestamo;
        this.fabricaPrestamo=fabricaPrestamo;
    }

    public Map<String, Object> ejecutar(DtoPrestamo dtoPrestamo){
        Prestamo prestamo=this.fabricaPrestamo.crear(dtoPrestamo);
        return servicioCrearPrestamo.crear(prestamo);

    }


}
