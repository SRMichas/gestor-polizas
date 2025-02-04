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

    private static final Logger logger = LoggerFactory.getLogger(PolizaService.class);
    @Autowired
    private IPolizaRepository polizaRepository;

    @Override
    public List<Poliza> ObtenerActivos() throws Exception {
        List<Poliza> polizas = List.of();
        try
        {
            
            polizas = polizaRepository.obtenerActivos();
            if(!polizas.isEmpty())
            {
                Poliza poliza = polizas.getFirst();

                boolean success = (poliza != null) ? poliza.Estatus : true;
                if( !success )
                    throw new Exception(poliza.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return polizas;
    }

    @Override
    public List<Poliza> ObtenerPorId(int polizaID) throws Exception {
        List<Poliza> polizas = List.of();
        try
        {
            Poliza poliza = new Poliza();
            poliza.setId(polizaID);

            polizas = polizaRepository.ObtenerPorId(poliza);
            if(!polizas.isEmpty())
            {
                poliza = polizas.getFirst();

                boolean success = (poliza != null) ? poliza.Estatus : true;
                if( !success )
                    throw new Exception(poliza.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return polizas;
    }

    @Override
    public List<Poliza> ObtenerPaginado(Poliza poliza) throws Exception {
        List<Poliza> polizas = List.of();
        try
        {
            polizas = polizaRepository.ObtenerPaginado(poliza);
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
    public Poliza Registrar(Poliza poliza) throws Exception {
        Poliza pol = null;
        try
        {
            pol = polizaRepository.Registrar(poliza);
            if(!pol.Estatus)
            {
                throw new Exception(poliza.Mensaje);
            }
        }
        catch (Exception ex){
            logger.error("ERROR en Registrar",ex);
            throw ex;
        }
        

        return pol;
    }

    @Override
    public Poliza Actualizar(Poliza poliza) throws Exception {
        Poliza pol = null;
        try
        {
            pol = polizaRepository.Actualizar(poliza);
            if(!pol.Estatus)
            {
                throw new Exception(poliza.Mensaje);
            }
        }
        catch (Exception ex){
            logger.error("ERROR en Actualizar",ex);
            throw ex;
        }
        

        return pol;
    }

    @Override
    public Poliza Eliminar(Poliza poliza) throws Exception {
        Poliza pol = null;
        try
        {
            pol = polizaRepository.Eliminar(poliza);
            if(!pol.Estatus)
            {
                throw new Exception(poliza.Mensaje);
            }
        }
        catch (Exception ex){
            throw ex;
        }

        return pol;
    }
}
