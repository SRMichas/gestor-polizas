package com.soria.ApiPolizas.Repository.Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soria.ApiPolizas.Model.Empleado;
import java.util.List;

public interface IEmpleadoRepository {

    public List<Empleado> obtenerActivos();

    public List<Empleado> ObtenerPorId(Empleado empleado) throws JsonProcessingException;

    public List<Empleado> ObtenerPaginado(Empleado empleado) throws JsonProcessingException;

    public Empleado Registrar(Empleado empleado) throws Exception;

    public Empleado Actualizar(Empleado empleado) throws Exception;

    public Empleado Eliminar(Empleado empleado) throws Exception;
}
