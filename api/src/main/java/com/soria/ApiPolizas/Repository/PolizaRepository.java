package com.soria.ApiPolizas.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soria.ApiPolizas.Model.Poliza;
import com.soria.ApiPolizas.Repository.Interface.IPolizaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Types;
import java.util.List;

@Repository
public class PolizaRepository implements IPolizaRepository {
    private String Procedure = "{ CALL ssp_mov_polizas(?, ?) }";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Poliza> obtenerActivos() {
        return jdbcTemplate.query(Procedure, new Object[]{2,"{}"},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Poliza.class));
    }

    @Override
    public List<Poliza> ObtenerPorId(Poliza poliza) throws Exception {
        String json = objectMapper.writeValueAsString(poliza);

        return jdbcTemplate.query(Procedure, new Object[]{2,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Poliza.class));
    }

    @Override
    public List<Poliza> ObtenerPaginado(Poliza poliza) throws Exception {
        String json = objectMapper.writeValueAsString(poliza);

        return jdbcTemplate.query(Procedure, new Object[]{5,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Poliza.class));
    }

    @Override
    public Poliza Registrar(Poliza poliza) throws Exception{
        String json = objectMapper.writeValueAsString(poliza);

        Poliza result = jdbcTemplate.queryForObject(Procedure, new Object[]{1,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Poliza.class));

        return result;
    }

    @Override
    public Poliza Actualizar(Poliza poliza) throws Exception{
        String json = objectMapper.writeValueAsString(poliza);

        Poliza result = jdbcTemplate.queryForObject(Procedure, new Object[]{3,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Poliza.class));

        return result;
    }

    @Override
    public Poliza Eliminar(Poliza poliza) throws Exception{
        String json = objectMapper.writeValueAsString(poliza);

        Poliza result = jdbcTemplate.queryForObject(Procedure, new Object[]{4,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Poliza.class));

        return result;
    }
}
