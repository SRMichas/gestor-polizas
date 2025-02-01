package com.soria.ApiPolizas.Repository;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Empleado> obtenerActivos() {
        String callProcedure = "{ CALL ssp_empleado(?, ?) }";
        return jdbcTemplate.query(callProcedure, new Object[]{2,"{}"},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Empleado.class));
        //return List.of();
    }
}
