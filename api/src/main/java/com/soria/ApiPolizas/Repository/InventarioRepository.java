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
}
