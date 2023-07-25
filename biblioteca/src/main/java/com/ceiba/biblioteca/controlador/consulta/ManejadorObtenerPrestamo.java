package com.ceiba.biblioteca.controlador.consulta;

import com.ceiba.biblioteca.controlador.comando.FabricaPrestamo;
import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.entidad.Prestamo;
import com.ceiba.biblioteca.servicio.ServicioCrearPrestamo;
import com.ceiba.biblioteca.servicio.ServicioObtenerPrestamo;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class ManejadorObtenerPrestamo {

    ServicioObtenerPrestamo servicioObtenerPrestamo;
    FabricaPrestamo fabricaPrestamo;

    public ManejadorObtenerPrestamo( ServicioObtenerPrestamo servicioObtenerPrestamo, FabricaPrestamo fabricaPrestamo){
        this.servicioObtenerPrestamo=servicioObtenerPrestamo;
        this.fabricaPrestamo=fabricaPrestamo;
    }

    public DtoPrestamo ejecutar(Integer id){
        System.out.println(id);
        return servicioObtenerPrestamo.obtener(id);

    }


}
