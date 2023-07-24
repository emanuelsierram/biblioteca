package com.ceiba.biblioteca.modelo.repositorio;

import com.ceiba.biblioteca.modelo.dto.DtoPrestamo;
import com.ceiba.biblioteca.modelo.entidad.Prestamo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class RepositorioPrestamoH2 implements RepositorioPrestamo {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static String sqlCrear = "INSERT INTO prestamo (isbn, identificacion_usuario, tipo_usuario, fecha_maxima_devolucion) VALUES (:isbn, :identificacionUsuario, :tipoUsuario, :fechaMaximaDevolucion)";
    private static String sqlObtenerPorId = "SELECT id,isbn,identificacion_usuario,tipo_usuario,fecha_maxima_devolucion from prestamo where id=:id";
    private static  String sqlExiste= "SELECT COUNT(1) from prestamo where identificacion_usuario = :identificacion_usuario";
    public RepositorioPrestamoH2(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Map<String, Object> crear(Prestamo prestamo) {

        MapSqlParameterSource parametros = new MapSqlParameterSource();
        parametros.addValue("isbn", prestamo.getIsbn());
        parametros.addValue("identificacionUsuario", prestamo.getIdentificacionUsuario());
        parametros.addValue("tipoUsuario", prestamo.getTipoUsuario());
        parametros.addValue("fechaMaximaDevolucion", prestamo.getFechaMaxima());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String[] columnasClaves = {"id", "fecha_maxima_devolucion"};

        this.namedParameterJdbcTemplate.update(sqlCrear, parametros, keyHolder, columnasClaves);

        Map<String, Object> MapResponse = keyHolder.getKeys();
        Object valorId = MapResponse.get("ID");
        Object valorFechaMaximaDevolucion = MapResponse.get("FECHA_MAXIMA_DEVOLUCION");
        MapResponse.remove("FECHA_MAXIMA_DEVOLUCION");
        MapResponse.put("id", valorId);
        MapResponse.put("fechaMaximaDevolucion", valorFechaMaximaDevolucion);
        return MapResponse;

    }

    @Override
    public DtoPrestamo obtenerPrestamo(Integer id) {
        MapSqlParameterSource parametro = new MapSqlParameterSource();
        parametro.addValue("id", id);

        return this.namedParameterJdbcTemplate.queryForObject(sqlObtenerPorId, parametro, new RowMapper<DtoPrestamo>() {
            @Override
            public DtoPrestamo mapRow(ResultSet rs, int rowNum) throws SQLException {
                int idPrestamo = rs.getInt("id");
                String isbnPrestamo = rs.getString("isbn");
                String identificacionUsuarioPrestamo = rs.getString("identificacion_usuario");
                int tipoUsuarioPrestamo = rs.getInt("tipo_usuario");
                String fechaMaximaDevolucionPrestamo = rs.getString("fecha_maxima_devolucion");

                return new DtoPrestamo(
                        idPrestamo,
                        isbnPrestamo,
                        identificacionUsuarioPrestamo,
                        tipoUsuarioPrestamo,
                        fechaMaximaDevolucionPrestamo);
            }
        });

    }

    @Override
    public boolean existe(String identificacionUsuario) {
        MapSqlParameterSource parametro = new MapSqlParameterSource();
        parametro.addValue("identificacion_usuario", identificacionUsuario);
        return this.namedParameterJdbcTemplate.queryForObject(sqlExiste,parametro,Boolean.class);
    }

}
