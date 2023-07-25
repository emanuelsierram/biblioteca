package com.ceiba.biblioteca.calificador.unittest.testdatabuilder;

import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.entidad.Prestamo;

public class PrestamoTestDataBuilder {
    private int id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private String fechaMaxima;

    public PrestamoTestDataBuilder(){
        isbn="AH123";
        identificacionUsuario="123565465";
        tipoUsuario=1;
        fechaMaxima="02/07/2023";

    }


    public PrestamoTestDataBuilder conTipoUsuario(Integer tipoUsuario){
        this.tipoUsuario=tipoUsuario;
        return this;
    }

    public Prestamo build(){return new Prestamo(id,isbn,identificacionUsuario,tipoUsuario);}

}
