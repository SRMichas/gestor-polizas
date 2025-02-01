package com.soria.ApiPolizas.Service.Interface;

import com.soria.ApiPolizas.Model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> ObtenerActivos() throws Exception;
}
