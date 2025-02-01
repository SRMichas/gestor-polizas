package com.soria.ApiPolizas.Repository.Interface;

import com.soria.ApiPolizas.Model.Empleado;
import java.util.List;

public interface IEmpleadoRepository {

    public List<Empleado> obtenerActivos();
}
