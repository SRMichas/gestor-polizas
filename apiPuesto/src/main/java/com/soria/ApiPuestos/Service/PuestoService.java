package com.soria.ApiPuestos.Service;

import com.soria.ApiPuestos.Model.Puesto;
import com.soria.ApiPuestos.Repository.Interface.IPuestoRepository;
import com.soria.ApiPuestos.Service.Interface.IPuestoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PuestoService extends ServiceBase implements IPuestoService {

    private static final Logger logger = LoggerFactory.getLogger(PuestoService.class);
    @Autowired
    private IPuestoRepository puestoRepository;

    @Override
    public List<Puesto> ObtenerActivos() throws Exception {
        List<Puesto> puestos = List.of();
        try
        {
            
            puestos = puestoRepository.obtenerActivos();
            if(!puestos.isEmpty())
            {
                Puesto puesto = puestos.getFirst();

                boolean success = (puesto != null) ? puesto.Estatus : true;
                if( !success )
                    throw new Exception(puesto.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return puestos;
    }

    
}
