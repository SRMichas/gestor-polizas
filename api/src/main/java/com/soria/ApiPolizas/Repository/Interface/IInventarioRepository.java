package com.soria.ApiPolizas.Repository.Interface;

import com.soria.ApiPolizas.Model.Inventario;
import java.util.List;

public interface IInventarioRepository {

    public List<Inventario> obtenerActivos();

    public List<Inventario> ObtenerPorSKU(Inventario inventario) throws Exception;

    public List<Inventario> ObtenerPaginado(Inventario inventario) throws Exception;

    public Inventario Registrar(Inventario inventario) throws Exception;

    public Inventario Actualizar(Inventario inventario) throws Exception;

    public Inventario Eliminar(Inventario inventario) throws Exception;
}
