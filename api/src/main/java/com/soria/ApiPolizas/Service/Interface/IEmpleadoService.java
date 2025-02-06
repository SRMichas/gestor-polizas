package com.soria.ApiPolizas.Service.Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soria.ApiPolizas.Model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> ObtenerActivos() throws Exception;
    public List<Empleado> ObtenerPorId(int empleadoID) throws Exception;
    public Empleado Actualizar(Empleado empleado) throws Exception;
}
