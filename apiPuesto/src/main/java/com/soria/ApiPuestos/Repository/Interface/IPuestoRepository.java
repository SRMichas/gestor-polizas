package com.soria.ApiPuestos.Repository.Interface;

import com.soria.ApiPuestos.Model.Puesto;

import java.util.List;

public interface IPuestoRepository {

    public List<Puesto> obtenerActivos();
}
