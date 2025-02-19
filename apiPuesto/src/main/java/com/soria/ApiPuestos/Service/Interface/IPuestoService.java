package com.soria.ApiPuestos.Service.Interface;

import com.soria.ApiPuestos.Model.Puesto;
import java.util.List;

public interface IPuestoService {

    public List<Puesto> ObtenerActivos() throws Exception;
}
