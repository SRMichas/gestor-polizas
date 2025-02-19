package com.soria.ApiPolizas.Repository.Interface;

import com.soria.ApiPolizas.Model.Inventario;
import java.util.List;

public interface IInventarioRepository {

    public List<Inventario> obtenerActivos();
    public List<Inventario> ajusteInventario(Inventario inventario) throws Exception;
    public List<Inventario> restaurarInventario(Inventario inventario) throws Exception;
}
