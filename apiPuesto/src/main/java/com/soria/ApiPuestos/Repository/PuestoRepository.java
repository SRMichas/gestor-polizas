package com.soria.ApiPuestos.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soria.ApiPuestos.Model.Puesto;
import com.soria.ApiPuestos.Repository.Interface.IPuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Types;
import java.util.List;

@Repository
public class PuestoRepository implements IPuestoRepository {
    private String Procedure = "{ CALL ssp_puestos(?, ?) }";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Puesto> obtenerActivos() {
        return jdbcTemplate.query(Procedure, new Object[]{2,"{}"},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Puesto.class));
    }

    
}
