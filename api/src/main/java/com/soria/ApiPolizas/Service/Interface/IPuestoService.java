package com.soria.ApiPolizas.Service.Interface;

import com.soria.ApiPolizas.Model.Puesto;
import java.util.List;

public interface IPuestoService {

    public List<Puesto> ObtenerActivos() throws Exception;
    public List<Puesto> ObtenerPorId(int empleadoID) throws Exception;
    public List<Puesto> ObtenerPaginado(Puesto puesto) throws Exception;
    public Puesto Registrar(Puesto puesto) throws Exception;
    public Puesto Actualizar(Puesto puesto) throws Exception;
    public Puesto Eliminar(Puesto puesto) throws Exception;
}
