package com.soria.ApiPolizas.Service.Interface;

import com.soria.ApiPolizas.Model.Puesto;
import java.util.List;

public interface IPuestoService {

    public List<Puesto> ObtenerActivos() throws Exception;
}
