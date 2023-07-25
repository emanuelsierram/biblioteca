package com.ceiba.biblioteca.testunitarios;

import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.entidad.Prestamo;
import com.ceiba.biblioteca.modelo.repositorio.RepositorioPrestamo;
import com.ceiba.biblioteca.servicio.ServicioCrearPrestamo;
import com.ceiba.biblioteca.testunitarios.testdatabuilder.PrestamoTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


public class SerivicoCrearPrestamoTest {

    RepositorioPrestamo repositorioPrestamo;
    ServicioCrearPrestamo servicioCrearPrestamo;


    @Before
    public void setup() {
        repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        servicioCrearPrestamo = new ServicioCrearPrestamo(repositorioPrestamo);
    }


    @Test
    public void tieneLibrosPrestadosUsuarioInvitadoTest() {
        Prestamo prestamo = new PrestamoTestDataBuilder().conTipoUsuario(3).build();
        Mockito.when(repositorioPrestamo.existe(Mockito.anyString())).thenReturn(true);


        try {
            servicioCrearPrestamo.crear(prestamo);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "El usuario con identificación " + prestamo.getIdentificacionUsuario() + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
        }

    }

    @Test
    public void CrearPrestamoParaUsuarioInvitado() {
        Prestamo prestamo = new PrestamoTestDataBuilder().conTipoUsuario(3).build();
        final ArgumentCaptor<Prestamo> prestamoArgumentCaptor = ArgumentCaptor.forClass(Prestamo.class);
        Mockito.when(repositorioPrestamo.obtenerPrestamo(prestamo.getId())).thenReturn(new DtoPrestamo(
                1,
                "AXSLD",
                "11213232132",
                1,
                "02/07/2023"
        ));
        servicioCrearPrestamo.crear(prestamo);
        Mockito.verify(repositorioPrestamo).crear(prestamoArgumentCaptor.capture());

        Assert.assertEquals(prestamoArgumentCaptor.getValue().getId(), prestamo.getId());
    }

    @Test
    public void validarTipoDeUsuarioNoExistenteTest() {
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder().conTipoUsuario(5);
        try {
            prestamoTestDataBuilder.build();
            Assert.fail("Se esperaba una excepción");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Tipo de usuario no permitido en la biblioteca");

        }

    }

    @Test
    public void validarTipoDeUsuarioExistenteTest() {
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder().conTipoUsuario(1);
        try {
            prestamoTestDataBuilder.build();
        } catch (Exception e) {
            Assert.fail("El tipo de usuario no se encuentra entre los valores esperados");


        }

    }


}
