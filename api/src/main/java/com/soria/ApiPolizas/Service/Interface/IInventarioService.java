package com.soria.ApiPolizas.Service.Interface;

import com.soria.ApiPolizas.Model.Inventario;

import java.util.List;

public interface IInventarioService {

    public List<Inventario> ObtenerActivos() throws Exception;
    public List<Inventario> ObtenerPorSKU(String sku) throws Exception;
    public List<Inventario> ObtenerPaginado(Inventario inventario) throws Exception;
    public Inventario Registrar(Inventario inventario) throws Exception;
    public Inventario Actualizar(Inventario inventario) throws Exception;
    public Inventario Eliminar(Inventario inventario) throws Exception;
}
