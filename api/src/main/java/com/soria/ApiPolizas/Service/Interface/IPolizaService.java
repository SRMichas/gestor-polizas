package com.soria.ApiPolizas.Service.Interface;

import com.soria.ApiPolizas.Model.Poliza;

import java.util.List;

public interface IPolizaService {

    public List<Poliza> ObtenerActivos() throws Exception;
    public List<Poliza> ObtenerPorId(int polizaID) throws Exception;
    public List<Poliza> ObtenerPaginado(Poliza poliza) throws Exception;
    public Poliza Registrar(Poliza poliza) throws Exception;
    public Poliza Actualizar(Poliza poliza) throws Exception;
    public Poliza Eliminar(Poliza poliza) throws Exception;
}
