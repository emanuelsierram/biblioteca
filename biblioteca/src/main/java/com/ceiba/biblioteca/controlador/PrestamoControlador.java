package com.ceiba.biblioteca.controlador;



import com.ceiba.biblioteca.controlador.comando.ManejadorCrearPrestamo;
import com.ceiba.biblioteca.controlador.consulta.ManejadorObtenerPrestamo;
import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.dto.MensajeRespuesta;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    ManejadorCrearPrestamo manejadorCrearPrestamo;
    ManejadorObtenerPrestamo manejadorObtenerPrestamo;

    @Autowired
    public PrestamoControlador(ManejadorCrearPrestamo manejadorCrearPrestamo, ManejadorObtenerPrestamo manejadorObtenerPrestamo){
        this.manejadorCrearPrestamo=manejadorCrearPrestamo;
        this.manejadorObtenerPrestamo=manejadorObtenerPrestamo;
    }



    @PostMapping
    public ResponseEntity<String> crear(@RequestBody DtoPrestamo dtoPrestamo){
        ObjectMapper objectMapper = new ObjectMapper();
        String json= null;
        ResponseEntity mensaje;
        try {
            json = objectMapper.writeValueAsString(manejadorCrearPrestamo.ejecutar(dtoPrestamo));
            mensaje=ResponseEntity.ok(json);
        } catch (Exception e) {
            MensajeRespuesta mensajeRespuesta=new MensajeRespuesta(e.getMessage().toString());
           mensaje= ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeRespuesta);
        }

        return mensaje;
    }


    @GetMapping("/{id}")
    public DtoPrestamo obtener(@PathVariable Integer id){
        return this.manejadorObtenerPrestamo.ejecutar(id);
    }










}

