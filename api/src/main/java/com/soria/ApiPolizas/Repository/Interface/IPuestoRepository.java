package com.soria.ApiPolizas.Repository.Interface;

import com.soria.ApiPolizas.Model.Puesto;

import java.util.List;

public interface IPuestoRepository {

    public List<Puesto> obtenerActivos();
}
