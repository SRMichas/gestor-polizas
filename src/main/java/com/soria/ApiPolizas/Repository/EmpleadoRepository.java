package com.soria.ApiPolizas.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soria.ApiPolizas.Model.Empleado;
import com.soria.ApiPolizas.Repository.Interface.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class EmpleadoRepository implements IEmpleadoRepository {
    private String Procedure = "{ CALL ssp_empleado(?, ?) }";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Empleado> obtenerActivos() {
        return jdbcTemplate.query(Procedure, new Object[]{2,"{}"},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Empleado.class));
    }

    @Override
    public List<Empleado> ObtenerPorId(Empleado empleado) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(empleado);

        return jdbcTemplate.query(Procedure, new Object[]{2,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Empleado.class));
    }

    @Override
    public List<Empleado> ObtenerPaginado(Empleado empleado) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(empleado);

        return jdbcTemplate.query(Procedure, new Object[]{5,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Empleado.class));
    }

    @Override
    public Empleado Registrar(Empleado empleado) throws Exception{
        String json = objectMapper.writeValueAsString(empleado);

        Empleado result = jdbcTemplate.queryForObject(Procedure, new Object[]{1,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return result;
    }

    @Override
    public Empleado Actualizar(Empleado empleado) throws Exception{
        String json = objectMapper.writeValueAsString(empleado);

        Empleado result = jdbcTemplate.queryForObject(Procedure, new Object[]{3,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return result;
    }

    @Override
    public Empleado Eliminar(Empleado empleado) throws Exception{
        String json = objectMapper.writeValueAsString(empleado);

        Empleado result = jdbcTemplate.queryForObject(Procedure, new Object[]{4,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return result;
    }
}
