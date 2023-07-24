package com.ceiba.biblioteca.controlador;


import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.dto.MensajeRespuesta;
import com.ceiba.biblioteca.servicio.ServicioCrearPrestamo;
import com.ceiba.biblioteca.servicio.ServicioObtenerPrestamo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    ServicioCrearPrestamo servicioCrearPrestamo;
    ServicioObtenerPrestamo servicioObtenerPrestamo;

    public PrestamoControlador(ServicioCrearPrestamo servicioCrearPrestamo, ServicioObtenerPrestamo servicioObtenerPrestamo){
        this.servicioCrearPrestamo=servicioCrearPrestamo;
        this.servicioObtenerPrestamo=servicioObtenerPrestamo;
    }


    @PostMapping
    public ResponseEntity<String> crear(@RequestBody DtoPrestamo dtoPrestamo){
        ObjectMapper objectMapper = new ObjectMapper();
        String json= null;
        ResponseEntity mensaje;
        try {
            json = objectMapper.writeValueAsString(servicioCrearPrestamo.crear(dtoPrestamo));
            mensaje=ResponseEntity.ok(json);
        } catch (Exception e) {
            MensajeRespuesta mensajeRespuesta=new MensajeRespuesta(e.getMessage().toString());
           mensaje= ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeRespuesta);
        }

        return mensaje;
    }


    @GetMapping("/{id}")
    public DtoPrestamo obtener(@PathVariable Integer id){
        return servicioObtenerPrestamo.obtener(id);
    }










}

