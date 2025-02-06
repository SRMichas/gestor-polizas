package com.soria.ApiPolizas.Repository.Interface;

import com.soria.ApiPolizas.Model.Inventario;
import java.util.List;

public interface IInventarioRepository {

    public List<Inventario> obtenerActivos();
}
