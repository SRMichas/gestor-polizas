package com.soria.ApiPolizas.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soria.ApiPolizas.Model.Inventario;
import com.soria.ApiPolizas.Repository.Interface.IInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Types;
import java.util.List;

@Repository
public class InventarioRepository implements IInventarioRepository {
    private String Procedure = "{ CALL ssp_mov_inventario(?, ?) }";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Inventario> obtenerActivos() {
        return jdbcTemplate.query(Procedure, new Object[]{2,"{}"},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Inventario.class));
    }

    @Override
    public List<Inventario> ObtenerPorSKU(Inventario inventario) throws Exception {
        String json = objectMapper.writeValueAsString(inventario);

        return jdbcTemplate.query(Procedure, new Object[]{2,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Inventario.class));
    }

    @Override
    public List<Inventario> ObtenerPaginado(Inventario inventario) throws Exception {
        String json = objectMapper.writeValueAsString(inventario);

        return jdbcTemplate.query(Procedure, new Object[]{5,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Inventario.class));
    }

    @Override
    public Inventario Registrar(Inventario inventario) throws Exception{
        String json = objectMapper.writeValueAsString(inventario);

        Inventario result = jdbcTemplate.queryForObject(Procedure, new Object[]{1,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Inventario.class));

        return result;
    }

    @Override
    public Inventario Actualizar(Inventario inventario) throws Exception{
        String json = objectMapper.writeValueAsString(inventario);

        Inventario result = jdbcTemplate.queryForObject(Procedure, new Object[]{3,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Inventario.class));

        return result;
    }

    @Override
    public Inventario Eliminar(Inventario inventario) throws Exception{
        String json = objectMapper.writeValueAsString(inventario);

        Inventario result = jdbcTemplate.queryForObject(Procedure, new Object[]{4,json},
                new int[]{Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Inventario.class));

        return result;
    }
}
