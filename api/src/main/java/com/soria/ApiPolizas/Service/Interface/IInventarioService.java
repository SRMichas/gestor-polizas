package com.soria.ApiPolizas.Service.Interface;

import com.soria.ApiPolizas.Model.Inventario;

import java.util.List;

public interface IInventarioService {

    public List<Inventario> obtenerActivos() throws Exception;
}
