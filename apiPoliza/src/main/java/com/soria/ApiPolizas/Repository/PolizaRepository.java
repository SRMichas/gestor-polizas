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
    public List<Poliza> obtenerPaginado(Poliza poliza) throws Exception {
        return jdbcTemplate.query("{ CALL obtenerPolizasPaginadas(?, ?, ?) }",
                new Object[]{poliza.Pagina,poliza.Paginado, poliza.Busqueda},
                new int[]{Types.INTEGER, Types.INTEGER, Types.VARCHAR},
                BeanPropertyRowMapper.newInstance(Poliza.class));
    }

    @Override
    public Poliza registrar(Poliza poliza) throws Exception{

        Poliza result = jdbcTemplate.queryForObject("{ CALL crearPoliza(?, ?, ?) }"
                , new Object[]{poliza.getIdEmpleado(), poliza.getSKU(), poliza.getCantidad()},
                new int[]{Types.INTEGER, Types.VARCHAR, Types.INTEGER},
                BeanPropertyRowMapper.newInstance(Poliza.class));

        return result;
    }

    public Poliza cambiarEmpleado(Poliza poliza) throws Exception{

        Poliza result = jdbcTemplate.queryForObject("{ CALL actualizarEmpleadoPoliza(?, ?) }"
                , new Object[]{poliza.getIdPoliza() ,poliza.getIdEmpleado()},
                new int[]{Types.INTEGER, Types.INTEGER},
                BeanPropertyRowMapper.newInstance(Poliza.class));

        return result;
    }

    @Override
    public Poliza eliminar(Poliza poliza) throws Exception{

        Poliza result = jdbcTemplate.queryForObject("{ CALL eliminarPoliza(?) }"
                , new Object[]{poliza.getIdPoliza()},
                new int[]{Types.INTEGER},
                BeanPropertyRowMapper.newInstance(Poliza.class));

        return result;
    }
}
