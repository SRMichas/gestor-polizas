package com.soria.ApiPolizas.Repository.Interface;

import com.soria.ApiPolizas.Model.Poliza;

import java.util.List;

public interface IPolizaRepository {

    public List<Poliza> obtenerPaginado(Poliza poliza) throws Exception;
    public Poliza registrar(Poliza poliza) throws Exception;
    public Poliza cambiarEmpleado(Poliza poliza) throws Exception;
    public Poliza eliminar(Poliza poliza) throws Exception;
}
