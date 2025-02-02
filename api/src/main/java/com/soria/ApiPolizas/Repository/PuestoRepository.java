package com.soria.ApiPolizas.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soria.ApiPolizas.Model.Puesto;
import com.soria.ApiPolizas.Repository.Interface.IPuestoRepository;
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

    @Override
    public List<Puesto> ObtenerPorId(Puesto puesto) throws Exception {
        String json = objectMapper.writeValueAsString(puesto);

        return jdbcTemplate.query(Procedure, new Object[]{2,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Puesto.class));
    }

    @Override
    public List<Puesto> ObtenerPaginado(Puesto puesto) throws Exception {
        String json = objectMapper.writeValueAsString(puesto);

        return jdbcTemplate.query(Procedure, new Object[]{5,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Puesto.class));
    }

    @Override
    public Puesto Registrar(Puesto puesto) throws Exception{
        String json = objectMapper.writeValueAsString(puesto);

        Puesto result = jdbcTemplate.queryForObject(Procedure, new Object[]{1,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Puesto.class));

        return result;
    }

    @Override
    public Puesto Actualizar(Puesto puesto) throws Exception{
        String json = objectMapper.writeValueAsString(puesto);

        Puesto result = jdbcTemplate.queryForObject(Procedure, new Object[]{3,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Puesto.class));

        return result;
    }

    @Override
    public Puesto Eliminar(Puesto puesto) throws Exception{
        String json = objectMapper.writeValueAsString(puesto);

        Puesto result = jdbcTemplate.queryForObject(Procedure, new Object[]{4,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Puesto.class));

        return result;
    }
}
