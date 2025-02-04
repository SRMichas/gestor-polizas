package com.soria.ApiPolizas.Repository.Interface;

import com.soria.ApiPolizas.Model.Poliza;

import java.util.List;

public interface IPolizaRepository {

    public List<Poliza> obtenerActivos();

    public List<Poliza> ObtenerPorId(Poliza poliza) throws Exception;

    public List<Poliza> ObtenerPaginado(Poliza poliza) throws Exception;

    public Poliza Registrar(Poliza poliza) throws Exception;

    public Poliza Actualizar(Poliza poliza) throws Exception;

    public Poliza Eliminar(Poliza poliza) throws Exception;
}
