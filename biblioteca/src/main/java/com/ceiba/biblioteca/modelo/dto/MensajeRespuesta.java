package com.ceiba.biblioteca.modelo.dto;

public class MensajeRespuesta {

    private String mensaje;

    public MensajeRespuesta(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
