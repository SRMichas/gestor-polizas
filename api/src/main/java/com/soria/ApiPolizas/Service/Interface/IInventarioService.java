package com.soria.ApiPolizas.Service.Interface;

import com.soria.ApiPolizas.Model.Inventario;

import java.util.List;

public interface IInventarioService {

    public List<Inventario> obtenerActivos() throws Exception;
    public Inventario ajusteInventario(Inventario inventario) throws Exception;
    public Inventario restaurarInventario(Inventario inventario) throws Exception;
}
