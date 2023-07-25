package com.ceiba.biblioteca.modelo.entidad;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Prestamo {

    private static final String TIPO_USUARIO_NO_PERMITIDO = "Tipo de usuario no permitido en la biblioteca";
    private static final String FORMATO_FECHA_MAXIMA_DEVOLUCION="dd/MM/yyyy";

    private static final int USUARIO_AFILIADO = 1;
    private static final int USUARIO_EMPLEADO = 2;
    private static final int USUARIO_INVITADO = 3;

    private int id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private String fechaMaxima;


    public Prestamo(int id, String isbn, String identificacionUsuario, int tipoUsuario) {
        validarTipoUsuario(tipoUsuario, TIPO_USUARIO_NO_PERMITIDO);
        this.id=id;
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
        this.fechaMaxima=CalculoFechaMaximaDePrestamo();
    }

    private void validarTipoUsuario(int valor, String mensaje) {
        if (valor < 0 || valor > 3) {
            throw new RuntimeException(mensaje);
        }

    }


    private String CalculoFechaMaximaDePrestamo() {
        String fechaMaximaCalculada="";
        if (tipoUsuario == USUARIO_AFILIADO)
            fechaMaximaCalculada=sumarDias(10);
        else if (tipoUsuario == USUARIO_EMPLEADO)
            fechaMaximaCalculada=sumarDias(8);
        else if (tipoUsuario == USUARIO_INVITADO)
            fechaMaximaCalculada=sumarDias(7);

        return fechaMaximaCalculada;
    }


    private String sumarDias(int diasASumar) {
        LocalDate fechaActual = LocalDate.now();

        for (int dia = 0; dia < diasASumar; dia++) {
            fechaActual = fechaActual.plusDays(1);

            if (fechaActual.getDayOfWeek() == DayOfWeek.SATURDAY)
                fechaActual = fechaActual.plusDays(2);
            else if (fechaActual.getDayOfWeek() == DayOfWeek.SUNDAY)
                fechaActual = fechaActual.plusDays(1);

        }

        DateTimeFormatter formato=DateTimeFormatter.ofPattern(FORMATO_FECHA_MAXIMA_DEVOLUCION);
        String fechaFormateada=fechaActual.format(formato);
        return fechaFormateada;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(String fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }
}
