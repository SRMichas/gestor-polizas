package com.soria.ApiPolizas.Repository.Interface;

import com.soria.ApiPolizas.Model.Puesto;

import java.util.List;

public interface IPuestoRepository {

    public List<Puesto> obtenerActivos();

    public List<Puesto> ObtenerPorId(Puesto empleado) throws Exception;

    public List<Puesto> ObtenerPaginado(Puesto empleado) throws Exception;

    public Puesto Registrar(Puesto empleado) throws Exception;

    public Puesto Actualizar(Puesto empleado) throws Exception;

    public Puesto Eliminar(Puesto empleado) throws Exception;
}
