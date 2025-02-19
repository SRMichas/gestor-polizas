package com.soria.ApiPolizas.Service;

import com.soria.ApiPolizas.Model.Poliza;
import com.soria.ApiPolizas.Repository.Interface.IPolizaRepository;
import com.soria.ApiPolizas.Repository.Interface.IPuestoRepository;
import com.soria.ApiPolizas.Service.Interface.IPolizaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolizaService extends ServiceBase implements IPolizaService {

    @Autowired
    private IPolizaRepository polizaRepository;

    @Override
    public List<Poliza> obtenerPaginado(Poliza poliza) throws Exception {
        List<Poliza> polizas = List.of();
        try
        {
            polizas = polizaRepository.obtenerPaginado(poliza);
            if(!polizas.isEmpty())
            {
                Poliza pol = polizas.getFirst();

                boolean success = (pol != null) ? pol.Estatus : true;
                if( !success )
                    throw new Exception(pol.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }
        

        return polizas;
    }

    @Override
    public Poliza registrar(Poliza poliza) throws Exception {
        Poliza pol = null;
        try
        {
            pol = polizaRepository.registrar(poliza);
            if(!pol.Estatus)
            {
                throw new Exception(pol.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }
        

        return pol;
    }

    @Override
    public Poliza cambiarEmpleado(Poliza poliza) throws Exception {
        Poliza pol = null;
        try
        {
            pol = polizaRepository.cambiarEmpleado(poliza);
            if(!pol.Estatus)
            {
                throw new Exception(pol.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }


        return pol;
    }


    @Override
    public Poliza eliminar(Poliza poliza) throws Exception {
        Poliza pol = null;
        try
        {
            pol = polizaRepository.eliminar(poliza);
            if(!pol.Estatus)
            {
                throw new Exception(pol.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return pol;
    }
}
