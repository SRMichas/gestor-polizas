package com.soria.ApiPolizas.Repository.Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soria.ApiPolizas.Model.Empleado;
import java.util.List;

public interface IEmpleadoRepository {

    public List<Empleado> obtenerActivos();
    public List<Empleado> ObtenerPorId(Empleado empleado) throws JsonProcessingException;
    public Empleado Actualizar(Empleado empleado) throws Exception;
}
