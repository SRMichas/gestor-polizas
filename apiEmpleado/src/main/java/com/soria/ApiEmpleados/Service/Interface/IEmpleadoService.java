package com.soria.ApiEmpleados.Service.Interface;

import com.soria.ApiEmpleados.Model.Empleado;
import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> obtenerActivos() throws Exception;
    public List<Empleado> obtenerPorId(int empleadoID) throws Exception;
    public Empleado actualizar(Empleado empleado) throws Exception;
}
