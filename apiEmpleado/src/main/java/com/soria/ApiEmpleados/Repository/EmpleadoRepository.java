package com.soria.ApiEmpleados.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soria.ApiEmpleados.Model.Empleado;
import com.soria.ApiEmpleados.Repository.Interface.IEmpleadoRepository;
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
        return jdbcTemplate.query("{ CALL obtenerEmpleadosActivos }", new Object[]{},
                new int[]{},
                BeanPropertyRowMapper.newInstance(Empleado.class));
    }

    @Override
    public List<Empleado> ObtenerPorId(int empleadoID) throws JsonProcessingException {

        return jdbcTemplate.query("{ CALL obtenerEmpleado(?) }", new Object[]{empleadoID},
                new int[]{Types.INTEGER},
                BeanPropertyRowMapper.newInstance(Empleado.class));
    }

    @Override
    public Empleado Actualizar(Empleado empleado) throws Exception{

        Empleado result = jdbcTemplate.queryForObject("{ CALL actualizarEmpleado(?, ?, ?) }"
                , new Object[]{empleado.getIdEmpleado(),empleado.getNombre(), empleado.getApellido()},
                new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return result;
    }

}
