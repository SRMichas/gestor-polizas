package com.soria.ApiEmpleados.Repository.Interface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soria.ApiEmpleados.Model.Empleado;
import java.util.List;

public interface IEmpleadoRepository {

    public List<Empleado> obtenerActivos();
    public List<Empleado> ObtenerPorId(int empleadoID) throws JsonProcessingException;
    public Empleado Actualizar(Empleado empleado) throws Exception;
}
